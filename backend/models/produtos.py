from typing import List
from pydantic import BaseModel

class Adicional(BaseModel):
    nome: str
    preco: float

class CadastrarProduto(BaseModel):
    nome: str
    descricao: str
    preco: float
    url_imagens: List[str]
    adicionais: List[Adicional]
    id_categoria: int

class ProdutoResponse(BaseModel):
    id: int
    nome: str
    descricao: str
    preco: float
    url_imagens: List[str]
    adicionais: List[Adicional]
    id_categoria: int
    categoria: str