from datetime import timedelta

from app.security import get_hashed_senha, criar_token_acesso
from database.schema import Usuario, get_session, Session
from fastapi import APIRouter, Depends, HTTPException
from models import CadastroUsuario, UsuarioResponse, UsuarioResponseToken
from sqlalchemy.exc import IntegrityError, SQLAlchemyError


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
    if session.query(Usuario).filter(Usuario.email == usuario_input.email).first():
        raise HTTPException(status_code=409, detail="Email já cadastrado")
    try:
        usuario = Usuario(
            nome=usuario_input.nome,
            telefone=usuario_input.telefone,
            endereco=usuario_input.endereco,
            email=usuario_input.email,
            hashed_senha=get_hashed_senha(usuario_input.senha),
        )
        session.add(usuario)
        session.commit()

        # Gerar o token após o usuário ser salvo com sucesso
        token = criar_token_acesso(usuario.email, usuario.id, usuario.adm, timedelta(minutes=20))
    except IntegrityError:
        raise HTTPException(status_code=409, detail="Email já cadastrado")
    except SQLAlchemyError as e:
        session.rollback()
        raise HTTPException(status_code=500, detail="Erro ao cadastrar usuário")
    finally:
        session.close()

    return UsuarioResponseToken(
        id=usuario.id,
        nome=usuario.nome,
        telefone=usuario.telefone,
        endereco=usuario.endereco,
        email=usuario.email,
        token={"access_token": token, "token_type": "bearer"}
    )
