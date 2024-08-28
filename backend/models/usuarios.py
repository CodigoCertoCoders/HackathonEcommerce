from pydantic import BaseModel

class CadastroUsuario(BaseModel):
    nome: str
    telefone: str
    endereco: str
    email: str
    senha: str

class CadastroUsuarioResponse(BaseModel):
    id: int
    nome: str
    email: str