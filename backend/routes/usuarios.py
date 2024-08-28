from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.exc import IntegrityError
from models.usuarios import CadastroUsuario, CadastroUsuarioResponse
from database.schema import Usuario, get_session, Session


router = APIRouter()

@router.post('/cadastrar')
async def cadastrar_usuario(usuario_input: CadastroUsuario, session: Session = Depends(get_session)):
    try:
        usuario = Usuario(
            nome=usuario_input.nome,
            telefone=usuario_input.telefone,
            endereco=usuario_input.endereco,
            email=usuario_input.email,
            hashed_senha=usuario_input.senha,
        )
        session.add(usuario)
        session.commit()
    except IntegrityError:
        raise HTTPException(status_code=409, detail='Email já cadastrado')

    return CadastroUsuarioResponse(
        id=usuario.id,
        nome=usuario.nome,
        email=usuario.email
    )