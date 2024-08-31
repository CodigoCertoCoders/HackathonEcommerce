from datetime import datetime
from typing import Dict, List
from pydantic import BaseModel

class ProdutoPedido(BaseModel):
    id_produto: int
    quantidade: int
    adicionais: Dict[str, str]

class RealizarPedido(BaseModel):
    id_usuario: int
    produtos: List[ProdutoPedido]

class ProdutoPedidoResponse(BaseModel):
    id_produto: int
    nome_produto: str
    quantidade: int

class PedidoResponse(BaseModel):
    id: int
    id_usuario: int
    nome_usuario: str
    produtos: List[ProdutoPedidoResponse]
    horario_pedido: datetime
    total: float