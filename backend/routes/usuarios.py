from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.exc import IntegrityError
from models.usuarios import CadastroUsuario, UsuarioResponse
from database.schema import Usuario, get_session, Session


router = APIRouter()


@router.get("/")
async def get_usuarios(session: Session = Depends(get_session)):
    usuarios = session.query(Usuario).all()
    return [
        UsuarioResponse(
            id=usuario.id,
            nome=usuario.nome,
            telefone=usuario.telefone,
            endereco=usuario.endereco,
            email=usuario.email,
        )
        for usuario in usuarios
    ]


@router.post("/cadastrar")
async def cadastrar_usuario(
    usuario_input: CadastroUsuario, session: Session = Depends(get_session)
):
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
        raise HTTPException(status_code=409, detail="Email já cadastrado")

    return UsuarioResponse(
        id=usuario.id,
        nome=usuario.nome,
        telefone=usuario.telefone,
        endereco=usuario.endereco,
        email=usuario.email,
    )
