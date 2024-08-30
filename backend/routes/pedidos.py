import logging
import os

import pywhatkit
from fastapi import APIRouter, Depends, HTTPException, BackgroundTasks
import crcmod

from database.schema import (
    Pedido,
    PedidoProduto,
    Produto,
    get_session,
    Session,
    Usuario,
)
from models import PedidoResponse, ProdutoPedidoResponse, RealizarPedido

# Criar pasta de logs se não existir
if not os.path.exists("logs"):
    os.makedirs("logs")

# Configuração do logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
    handlers=[
        logging.FileHandler("logs/app.log"),
        logging.StreamHandler()  # Se quiser que os logs apareçam também no console
    ]
)

logger = logging.getLogger(__name__)

router = APIRouter()


def send_whatsapp_notification(phone_number: str, message: str, background_tasks: BackgroundTasks) -> None:
    background_tasks.add_task(pywhatkit.sendwhatmsg_instantly, phone_number, message, 7, True, 2)


def gerar_crc(payload: str) -> str:
    crc16 = crcmod.mkCrcFun(0x11021, initCrc=0xFFFF, rev=False, xorOut=0x0000)
    crc16_code = crc16(payload.encode('utf-8'))
    crc16_formatado = str(crc16_code).replace('0x', "").upper()
    return crc16_formatado


def gerar_payload_pix(chave: str, valor: float, nome_recebedor: str) -> str:
    # Estrutura básica do payload PIX
    payload = (
        f"000201"  # Payload Format Indicator
        f"26580014BR.GOV.BCB.PIX"  # Merchant Account Information
        f"01{len(chave):02d}{chave}"  # Chave PIX
        f"52040000"  # Merchant Category Code
        f"5303986"  # Transaction Currency (986 = BRL)
        f"5405{len(valor)}{valor:.2f}"  # Transaction Amount
        f"5802BR"  # Country Code
        f"5901{len(nome_recebedor):02d}{nome_recebedor}"  # Merchant Name
        f"6008SAOPAULO"  # Merchant City
        f"62070503***"  # Additional Data Field Template
        f"6304"  # CRC16 placeholder
    )

    crc = gerar_crc(payload)  # Calcular o CRC-16
    payload += crc  # Adicionar o CRC ao final do payload

    return payload


@router.get("/")
async def get_pedidos(session: Session = Depends(get_session)):
    try:
        pedidos = session.query(Pedido).all()
        return [
            PedidoResponse(
                id=pedido.id,
                id_usuario=pedido.id_usuario,
                nome_usuario=pedido.usuario.nome,
                produtos=[
                    ProdutoPedidoResponse(
                        id_produto=produto.id,
                        nome_produto=produto.nome,
                        quantidade=session.query(PedidoProduto)
                        .filter(PedidoProduto.pedido_id == pedido.id)
                        .filter(PedidoProduto.produto_id == produto.id)
                        .first()
                        .quantidade,
                    )
                    for produto in pedido.produtos
                ],
                horario_pedido=pedido.horario_pedido,
                total=pedido.total,
            )
            for pedido in pedidos
        ]
    except Exception as e:
        logger.error(f"Erro buscando pedidos: {e}")
        raise HTTPException(status_code=500, detail="Internal Server Error")
    finally:
        session.close()


@router.post("/cadastrar")
async def cadastrar_pedido(
        pedido_input: RealizarPedido,
        background_tasks: BackgroundTasks,
        session: Session = Depends(get_session)
):
    try:
        pedido = Pedido(id_usuario=pedido_input.id_usuario, total=0)
        session.add(pedido)
        session.flush()
        produtos = []
        total = 0

        for produto_input in pedido_input.produtos:
            produto = session.query(Produto).get(produto_input.id_produto)

            if not produto:
                raise HTTPException(status_code=404, detail="Produto não encontrado")

            produtos.append(produto)
            total += produto.preco * produto_input.quantidade
            session.add(
                PedidoProduto(
                    pedido_id=pedido.id,
                    produto_id=produto.id,
                    quantidade=produto_input.quantidade,
                    preco_total=produto.preco * produto_input.quantidade,
                )
            )

        pedido.total = total
        session.add(pedido)
        session.commit()

        usuario = session.query(Usuario).get(pedido.id_usuario)

        # Enviar notificações por e-mail e WhatsApp em background
        send_whatsapp_notification(usuario.telefone, "Seu pedido foi cadastrado com sucesso!", background_tasks)

        logger.info(f"Pedido {pedido.id} cadastrado com sucesso para o usuário {pedido.id_usuario}.")

        return PedidoResponse(
            id=pedido.id,
            id_usuario=pedido.id_usuario,
            nome_usuario=pedido.usuario.nome,
            produtos=[
                ProdutoPedidoResponse(
                    id_produto=produto.id,
                    nome_produto=produto.nome,
                    quantidade=session.query(PedidoProduto)
                    .filter(PedidoProduto.pedido_id == pedido.id)
                    .filter(PedidoProduto.produto_id == produto.id)
                    .first()
                    .quantidade,
                )
                for produto in pedido.produtos
            ],
            horario_pedido=pedido.horario_pedido,
            total=pedido.total,
        )
    except Exception as e:
        logger.error(f"Erro cadastrando ordem: {e}")
        raise HTTPException(status_code=500, detail="Internal Server Error")
    finally:
        session.close()


@router.post("/pix/{pedido_id}")
async def gerar_pix(pedido_id: int, session: Session = Depends(get_session)):
    pedido = session.query(Pedido).get(pedido_id)

    if not pedido:
        raise HTTPException(status_code=404, detail="Pedido não encontrado")

    usuario = session.query(Usuario).get(pedido.id_usuario)
    chave_pix = "jaimeodairbassojuniorjaime@gmail.com"  # Chave estática para simulação
    nome_recebedor = "Jaime Odair Basso Junior"

    payload_pix = gerar_payload_pix(chave_pix, pedido.total, nome_recebedor)

    return {"payload": payload_pix}
