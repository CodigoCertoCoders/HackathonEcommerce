from pydantic import BaseModel

class CadastrarProduto(BaseModel):
    nome: str
    descricao: str
    preco: float
    url_imagem: str
    id_categoria: int