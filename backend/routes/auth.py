from datetime import timedelta
from typing import Annotated
from fastapi import APIRouter, Depends, HTTPException
from fastapi.security import OAuth2PasswordBearer, OAuth2PasswordRequestForm
from app.security import autenticar_usuario, criar_token_acesso
from models.auth import Token
from database.schema import Session, Usuario, get_session

router = APIRouter()

oauth2_scheme = OAuth2PasswordBearer(tokenUrl="auth/token")

@router.post("/token", response_model=Token)
async def login_for_acess_token(form_data: Annotated[OAuth2PasswordRequestForm, Depends()],
                                session: Session = Depends(get_session)):
    
    
    usuario = session.query(Usuario).filter(Usuario.email == form_data.username).first()
    if not usuario:
        raise HTTPException(status_code=401, detail="Não foi possível validar o usuário")
    
    usuario = autenticar_usuario(usuario, form_data.password)
    token = criar_token_acesso(usuario.email, usuario.id, usuario.adm, timedelta(minutes=20))

    return {'access_token': token, 'token_type': 'bearer'}