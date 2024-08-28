from fastapi import APIRouter, Depends
from sqlalchemy import insert

from models.pedidos import PedidoResponse, ProdutoPedido, RealizarPedido
from database.schema import Pedido, Produto, pedido_produto, get_session, Session


router = APIRouter()


@router.get("/")
async def get_pedidos(session: Session = Depends(get_session)):
    pedidos = session.query(Pedido).all()
    return [
        PedidoResponse(
            id=pedido.id,
            id_usuario=pedido.id_usuario,
            nome_usuario=pedido.usuario.nome,
            produtos=[
                ProdutoPedido(id_produto=produto.id, quantidade=produto.quantidade)
                for produto in pedido.produtos
            ],
            horario_pedido=pedido.horario_pedido,
            total=pedido.total,
        )
        for pedido in pedidos
    ]


@router.post("/cadastrar")
async def cadastrar_pedido(
    pedido_input: RealizarPedido, session: Session = Depends(get_session)
):
    produtos = []
    total = 0
    
    for produto_input in pedido_input.produtos:
        produto = session.query(Produto).get(produto_input.id_produto)
        total += produto.preco * produto_input.quantidade
        produtos.append(produto)
    
    pedido = Pedido(id_usuario=pedido_input.id_usuario, total=total, produtos=produtos)
    session.add(pedido)
    session.commit()
    
    return pedido
