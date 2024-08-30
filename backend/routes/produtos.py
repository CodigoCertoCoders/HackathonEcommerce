from fastapi import APIRouter, Depends, HTTPException, Header
from sqlalchemy.exc import IntegrityError
from models.produtos import CadastrarProduto, ProdutoResponse
from database.schema import Produto, get_session, Session, Categoria
from app.security import verifica_token_acesso

router = APIRouter()

@router.get("/")
async def get_produtos(session: Session = Depends(get_session)):
    produtos = session.query(Produto).all()
    return [
        ProdutoResponse(
            id=produto.id,
            nome=produto.nome,
            descricao=produto.descricao,
            preco=produto.preco,
            url_imagem=produto.url_imagem,
            id_categoria=produto.id_categoria,
            categoria=produto.categoria.nome,
        )
        for produto in produtos
    ]

@router.post("/cadastrar")
async def cadastrar_produto(
    produto_input: CadastrarProduto,
    authorization: str = Header(None),
    session: Session = Depends(get_session)
):

    if not session.query(Categoria).filter(Categoria.id == produto_input.id_categoria).first():
        raise HTTPException(status_code=400, detail="Categoria não existe.")

    token = authorization.split(" ")[1]

    try:
        # Decodificar o token e verificar permissões
        data = verifica_token_acesso(token)
        is_adm = data.get("is_adm", False)
        if not is_adm:
            raise HTTPException(status_code=403, detail="Usuário sem permissão.")

        id_administrador = data.get("id")

        # Criar o produto
        produto = Produto(
            nome=produto_input.nome,
            descricao=produto_input.descricao,
            preco=produto_input.preco,
            url_imagem=produto_input.url_imagem,
            id_categoria=produto_input.id_categoria,
            id_administrador=id_administrador
        )
        session.add(produto)
        session.commit()
    except IntegrityError:
        session.rollback()
        raise HTTPException(status_code=409, detail="Produto já cadastrado.")
    except Exception as e:
        session.rollback()
        raise HTTPException(status_code=500, detail="Erro ao cadastrar produto: " + str(e))

    return ProdutoResponse(
        id=produto.id,
        nome=produto.nome,
        descricao=produto.descricao,
        preco=produto.preco,
        url_imagem=produto.url_imagem,
        id_categoria=produto.id_categoria,
        categoria=produto.categoria.nome,
    )
