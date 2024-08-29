from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy import insert

from models.pedidos import PedidoResponse, ProdutoPedidoResponse, RealizarPedido
from database.schema import (
    Pedido,
    PedidoProduto,
    Produto,
    get_session,
    Session,
)


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


@router.post("/cadastrar")
async def cadastrar_pedido(
    pedido_input: RealizarPedido, session: Session = Depends(get_session)
):
    pedido = Pedido(id_usuario=pedido_input.id_usuario, total=0)
    session.add(pedido)
    session.flush()
    produtos = []
    total = 0

    for produto_input in pedido_input.produtos:
        produto = session.query(Produto).get(produto_input.id_produto)
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
