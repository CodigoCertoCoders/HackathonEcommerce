from typing import List
from pydantic import BaseModel

from models.produtos import Adicional

class ProdutoCategoria(BaseModel):
    id: int
    nome: str
    descricao: str
    preco: float
    url_imagens: List[str]
    adicionais: List[Adicional]


class CadastrarCategoria(BaseModel):
    nome: str

class CategoriaResponse(BaseModel):
    id: int
    nome: str
    produtos: List[ProdutoCategoria]