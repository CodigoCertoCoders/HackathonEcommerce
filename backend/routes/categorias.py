from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.exc import IntegrityError
from models.categorias import CadastrarCategoria, CategoriaResponse
from database.schema import Categoria, get_session, Session


router = APIRouter()


@router.get("/")
async def get_categorias(session: Session = Depends(get_session)):
    categorias = session.query(Categoria).all()
    return [
        CategoriaResponse(
            id=categoria.id,
            nome=categoria.nome,
        )
        for categoria in categorias
    ]


@router.post("/cadastrar")
async def cadastrar_categoria(
    categoria_input: CadastrarCategoria, session: Session = Depends(get_session)
):
    try:
        categoria = Categoria(nome=categoria_input.nome)
        session.add(categoria)
        session.commit()
    except IntegrityError:
        raise HTTPException(status_code=409, detail="Categoria já cadastrada")

    return CategoriaResponse(id=categoria.id, nome=categoria.nome)
