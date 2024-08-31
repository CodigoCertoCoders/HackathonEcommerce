import logging
from datetime import timedelta
from typing import Annotated

from app.security import autenticar_usuario, criar_token_acesso
from database.schema import Session, Usuario, get_session
from fastapi import APIRouter, Depends, HTTPException
from fastapi.security import OAuth2PasswordBearer, OAuth2PasswordRequestForm
from models.auth import Token

from app.security import verifica_token_acesso

router = APIRouter()

oauth2_scheme = OAuth2PasswordBearer(tokenUrl="auth/token")

# Configuração de logging
logging.basicConfig(level=logging.INFO)
logger = logging.getLogger(__name__)


@router.post("/token", response_model=Token)
async def login_for_acess_token(form_data: Annotated[OAuth2PasswordRequestForm, Depends()],
                                session: Session = Depends(get_session)):
    
    
    usuario = session.query(Usuario).filter(Usuario.email == form_data.username).first()
    if not usuario:
        logger.warning(f"Tentativa de login com email inexistente: {form_data.username}")
        raise HTTPException(status_code=401, detail="Não foi possível validar o usuário")

    # Verifica a senha antes de autentificar
    if not autenticar_usuario(usuario, form_data.password):
        logger.warning(f"Tentativa de login com senha incorreta para usuário: {form_data.username}")
        raise HTTPException(
            status_code=status.HTTP_401_UNAUTHORIZED,
            detail="Usuário ou senha incorretos",
            headers={"WWW-Authenticate": "Bearer"},
        )

    usuario = autenticar_usuario(usuario, form_data.password)
    token = criar_token_acesso(usuario.email, usuario.id, usuario.adm, timedelta(minutes=20))

    logger.info(f"Usuário {usuario.email} autenticado com sucesso.")
    return {"access_token": token, "token_type": "bearer"}



async def get_current_usuario(token: Annotated[str, Depends(oauth2_scheme)]):
    try:
        payload = verifica_token_acesso(token)
        print(payload)
        username: str = payload.get('sub')
        user_id: int = payload.get('id')
        is_adm: bool = payload.get('is_adm', False)
        if not username or not user_id:
            raise HTTPException(status_code=401, detail="Não foi possível validar o usuário")

        return {'username': username, 'id': user_id, 'is_adm': is_adm }

    except jwt.ExpiredSignatureError:
        raise HTTPException(status_code=401, detail="Token expirado")