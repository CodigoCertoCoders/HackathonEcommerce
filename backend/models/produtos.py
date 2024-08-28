from pydantic import BaseModel

class CadastrarProduto(BaseModel):
    nome: str
    descricao: str
    preco: float
    url_imagem: str
    id_categoria: int

class ProdutoResponse(BaseModel):
    id: int
    nome: str
    descricao: str
    preco: float
    url_imagem: str
    id_categoria: int
    categoria: str