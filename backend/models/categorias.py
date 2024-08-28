from typing import List
from pydantic import BaseModel

class CadastrarCategoria(BaseModel):
    nome: str

class CategoriaResponse(BaseModel):
    id: int
    nome: str