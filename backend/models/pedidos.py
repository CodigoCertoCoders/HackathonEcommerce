from datetime import datetime
from typing import List, Optional
from pydantic import BaseModel

class Adicional(BaseModel):
    nome: str
    preco: float

class ProdutoPedido(BaseModel):
    id_produto: int
    quantidade: int
    adicionais: List[Adicional]

class RealizarPedido(BaseModel):
    id_usuario: int
    produtos: Optional[List[ProdutoPedido]]

class ProdutoPedidoResponse(BaseModel):
    id_produto: int
    nome_produto: str
    quantidade: int
    adicionais: Optional[List[Adicional]]

class PedidoResponse(BaseModel):
    id: int
    id_usuario: int
    nome_usuario: str
    produtos: List[ProdutoPedidoResponse]
    horario_pedido: datetime
    total: float