import os
from datetime import datetime, timedelta, timezone

import bcrypt
import jwt
from database.schema import Usuario

print("oi", jwt.__file__)

ALGORITHM = "HS256"
JWT_SECRET_KEY = os.environ.get('JWT_SECRET_KEY')
JWT_SECRET_KEY = "aa9dba0d98b3c19aac1b6ec1724c3516d2bcd19f9d43d7798fa669f6c980db0d"


def get_hashed_senha(senha: str) -> str:
    """Hash a senha utilizando bcrypt"""
    senha_bytes = senha.encode("utf-8")
    return bcrypt.hashpw(senha_bytes, bcrypt.gensalt()).decode('utf8')


def verify_senha(senha: str, hashed_senha: str) -> bool:
    """Verifica se a senha corresponde ao hash armazenado"""
    if type(senha) == str:
        senha = senha.encode("utf-8")
    if type(hashed_senha) == str:
       hashed_senha = hashed_senha.encode("utf-8")
    return bcrypt.checkpw(senha, hashed_senha)


def criar_token_acesso(username: str, user_id: int, is_adm: bool, expires_delta: timedelta) -> str:
    """Cria um token JWT para o usuário autenticado"""
    to_encode = {
        'sub': username,
        'id': user_id,
        'exp': datetime.now(timezone.utc) + expires_delta,
        'is_adm': is_adm
        }
    
    encoded_jwt = jwt.encode(to_encode, JWT_SECRET_KEY, algorithm=ALGORITHM)
    return encoded_jwt


def verifica_token_acesso(token: str):
    return jwt.decode(token, JWT_SECRET_KEY, algorithms=ALGORITHM)

def autenticar_usuario(usuario: Usuario, password: str) -> Usuario | bool:
    """Autentica o usuário verificando a senha"""
    if verify_senha(password, usuario.hashed_senha):
        return usuario
    return False
