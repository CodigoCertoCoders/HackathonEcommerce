from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.exc import IntegrityError
from models.produtos import CadastrarProduto, ProdutoResponse
from database.schema import Produto, get_session, Session


router = APIRouter()


@router.get("/")
async def get_produtos(session: Session = Depends(get_session)):
    produtos = session.query(Produto).all()
    return [
        ProdutoResponse(
            id=produto.id,
            nome=produto.nome,
            descricao=produto.descricao,
            preco=produto.preco,
            url_imagem=produto.url_imagem,
            id_categoria=produto.id_categoria,
            categoria=produto.categoria.nome,
        )
        for produto in produtos
    ]


@router.post("/cadastrar")
async def cadastrar_produto(
    produto_input: CadastrarProduto, session: Session = Depends(get_session)
):
    try:
        produto = Produto(
            nome=produto_input.nome,
            descricao=produto_input.descricao,
            preco=produto_input.preco,
            url_imagem=produto_input.url_imagem,
            id_categoria=produto_input.id_categoria,
        )
        session.add(produto)
        session.commit()
    except IntegrityError:
        raise HTTPException(status_code=409, detail="Categoria já cadastrada")

    return ProdutoResponse(
        id=produto.id,
        nome=produto.nome,
        descricao=produto.descricao,
        preco=produto.preco,
        url_imagem=produto.url_imagem,
        id_categoria=produto.id_categoria,
        categoria=produto.categoria.nome,
    )
