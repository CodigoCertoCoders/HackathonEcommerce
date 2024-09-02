from datetime import timedelta
from app.security import get_hashed_senha, criar_token_acesso
from database.schema import Usuario, get_session, Session
from fastapi import APIRouter, Depends, HTTPException
from models import CadastroUsuario, UsuarioResponse, UsuarioResponseToken
from sqlalchemy.exc import IntegrityError, SQLAlchemyError

router = APIRouter()


@router.get("/")
async def get_administradores(session: Session = Depends(get_session)):
    administradores = session.query(Usuario).filter(Usuario.adm == True).all()
    return [
        UsuarioResponse(
            id=adm.id,
            nome=adm.nome,
            telefone=adm.telefone,
            endereco=adm.endereco,
            email=adm.email,
        )
        for adm in administradores
    ]


@router.post("/cadastrar")
async def cadastrar_administrador(
        administrador_input: CadastroUsuario, session: Session = Depends(get_session)
):
    if session.query(Usuario).filter(Usuario.email == administrador_input.email).first():
        raise HTTPException(status_code=409, detail="Email já cadastrado")

    try:
        adm = Usuario(
            nome=administrador_input.nome,
            telefone=administrador_input.telefone,
            endereco=administrador_input.endereco,
            email=administrador_input.email,
            hashed_senha=get_hashed_senha(administrador_input.senha),
            adm=True
        )
        session.add(adm)
        session.commit()

        # Gerar o token após o usuário ser salvo com sucesso
        token = criar_token_acesso(adm.email, adm.id, adm.adm, timedelta(minutes=20))

    except IntegrityError:
        session.rollback()
        raise HTTPException(status_code=409, detail="Erro ao cadastrar: Email já cadastrado")
    except SQLAlchemyError as e:
        session.rollback()
        raise HTTPException(status_code=500, detail="Erro ao cadastrar usuário: " + str(e))

    return UsuarioResponseToken(
        id=adm.id,
        nome=adm.nome,
        telefone=adm.telefone,
        endereco=adm.endereco,
        email=adm.email,
        token={"access_token": token, "token_type": "bearer"}
    )
