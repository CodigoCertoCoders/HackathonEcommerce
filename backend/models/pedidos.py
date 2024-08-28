from datetime import datetime
from typing import List
from pydantic import BaseModel

class ProdutoPedido(BaseModel):
    id_produto: int
    quantidade: int

class RealizarPedido(BaseModel):
    id_usuario: int
    produtos: List[ProdutoPedido]

class PedidoResponse(BaseModel):
    id: int
    id_usuario: int
    nome_usuario: str
    produtos: List[ProdutoPedido]
    horario_pedido: datetime
    total: float