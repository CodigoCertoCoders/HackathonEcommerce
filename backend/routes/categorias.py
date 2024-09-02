from typing import Annotated
from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.exc import IntegrityError
from models.categorias import CadastrarCategoria, CategoriaResponse, ProdutoCategoria
from database.schema import Categoria, get_session, Session
from routes.auth import get_current_usuario
from routes.pedidos import logger

router = APIRouter()


@router.get("/", response_model=list[CategoriaResponse])
async def get_categorias(session: Session = Depends(get_session)):
    try:
        categorias = session.query(Categoria).all()
        return [
            CategoriaResponse(
                id=categoria.id,
                nome=categoria.nome,
                produtos=[
                    ProdutoCategoria(
                        id=produto.id,
                        nome=produto.nome,
                        descricao=produto.descricao,
                        preco=produto.preco,
                        url_imagens=produto.url_imagens,
                        adicionais=produto.adicionais,
                    )
                    for produto in categoria.produtos
                ],
            )
            for categoria in categorias
        ]
    except Exception as e:
        logger.error(f"Erro ao buscar categorias: {e}")
        raise HTTPException(status_code=500, detail="Erro ao buscar categorias.")


@router.post("/cadastrar", response_model=CategoriaResponse)
async def cadastrar_categoria(
    categoria_input: CadastrarCategoria,
    user: Annotated[dict, Depends(get_current_usuario)],
    session: Session = Depends(get_session),
):
    if not user:
        raise HTTPException(status_code=403, detail="Cabeçalho de autorização ausente.")

    try:
        categoria = Categoria(nome=categoria_input.nome)
        session.add(categoria)
        session.commit()
        return CategoriaResponse(id=categoria.id, nome=categoria.nome)

    except IntegrityError:
        session.rollback()
        raise HTTPException(status_code=409, detail="Categoria já cadastrada.")
    except Exception as e:
        session.rollback()
        logger.error(f"Erro ao cadastrar categoria: {e}")
        raise HTTPException(status_code=500, detail="Erro ao cadastrar categoria.")
