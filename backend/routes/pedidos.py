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

from app.pix import Payload

# Definição chave de pix
CHAVE_PIX = "jaimeodairbassojuniorjaime@gmail.com"
# Verificação da validade da chave PIX
# CHAVE_PIX = os.environ.get("CHAVE_PIX")

if not CHAVE_PIX:
    raise ValueError("A chave PIX não foi configurada. Verifique o arquivo.env.")


# Verificação da validade da chave PIX

# Criar pasta de logs se não existir


logger = logging.getLogger(__name__)

router = APIRouter()



def send_whatsapp_notification(phone_number: str, message: str, background_tasks: BackgroundTasks) -> None:
    background_tasks.add_task(pywhatkit.sendwhatmsg_instantly, phone_number, message, 7, True, 2)


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
            total=pedido.total
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

    payload_pix = Payload(chave_pix, pedido.total).gerarPayload()


    return {"payload": payload_pix}
