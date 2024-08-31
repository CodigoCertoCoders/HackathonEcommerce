from typing import Dict, List
from pydantic import BaseModel

class CadastrarProduto(BaseModel):
    nome: str
    descricao: str
    preco: float
    url_imagens: List[str]
    adicionais: Dict[str, str]
    id_categoria: int

class ProdutoResponse(BaseModel):
    id: int
    nome: str
    descricao: str
    preco: float
    url_imagens: List[str]
    adicionais: Dict[str, str]
    id_categoria: int
    categoria: str