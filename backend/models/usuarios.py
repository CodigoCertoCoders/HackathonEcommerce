from typing import Dict
from pydantic import BaseModel

class CadastroUsuario(BaseModel):
    nome: str
    telefone: str
    endereco: Dict[str, str]
    email: str
    senha: str


class UsuarioResponse(BaseModel):
    id: int
    nome: str
    telefone: str
    endereco: Dict[str, str]
    email: str


class UsuarioResponseToken(BaseModel):
    id: int
    nome: str
    telefone: str
    endereco: Dict[str, str]
    email: str
    token: dict
