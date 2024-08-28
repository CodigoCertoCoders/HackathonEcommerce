from typing import List
from pydantic import BaseModel

class ProdutoPedido(BaseModel):
    id_produto: int
    quantidade: int

class RealizarPedido(BaseModel):
    id_usuario: int
    produtos: List[ProdutoPedido]
