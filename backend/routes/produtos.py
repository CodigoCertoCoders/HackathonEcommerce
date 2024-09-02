from typing import Annotated
from fastapi import APIRouter, Depends, HTTPException
from sqlalchemy.exc import IntegrityError
from models.produtos import CadastrarProduto, ProdutoResponse
from database.schema import Produto, get_session, Session, Categoria
from routes.auth import get_current_usuario

router = APIRouter()

from fastapi import Depends, HTTPException, status



import logging

logger = logging.getLogger(__name__)



@router.get("/", response_model=list[ProdutoResponse])
async def get_produtos(session: Session = Depends(get_session)) -> list[ProdutoResponse]:
    """
    Recupera todos os produtos cadastrados.

    - **Retorna** uma lista de produtos com suas informações (id, nome, descrição, preço, imagem, categoria).
    """
    produtos = session.query(Produto).all()
    return [
        ProdutoResponse(
            id=produto.id,
            nome=produto.nome,
            descricao=produto.descricao,
            preco=produto.preco,
            url_imagens=produto.url_imagens,
            adicionais=produto.adicionais,
            id_categoria=produto.id_categoria,
            categoria=produto.categoria.nome,
        )
        for produto in produtos
    ]


@router.post("/cadastrar", response_model=ProdutoResponse)
async def cadastrar_produto(
        produto_input: CadastrarProduto,
        user: dict = Depends(is_adm),
        session: Session = Depends(get_session)
) -> ProdutoResponse:
    """
    Cadastra um novo produto.

    - **Parâmetros**:
        - `produto_input`: Objeto contendo os dados do produto a ser cadastrado (nome, descrição, preço, url_imagem, id_categoria).
        - `user`: O usuário que está fazendo a requisição. Deve ser um administrador.

    - **Retorna** o produto cadastrado com suas informações (id, nome, descrição, preço, imagem, categoria).

    - **Lança**:
        - `HTTPException` 403 se o usuário não tiver permissão.
        - `HTTPException` 400 se a categoria não existir.
        - `HTTPException` 409 se o produto já estiver cadastrado.
        - `HTTPException` 500 em caso de erro inesperado ao cadastrar.
    """
    if not user or not user.get("is_adm"):
        print(not user.get("is_adm"))
        raise HTTPException(status_code=403, detail="Usuário sem permissão.")

    if not session.query(Categoria).filter(Categoria.id == produto_input.id_categoria).first():
        raise HTTPException(status_code=400, detail="Categoria não existe.")

    try:
        # Criar o produto
        produto = Produto(
            nome=produto_input.nome,
            descricao=produto_input.descricao,
            preco=produto_input.preco,
            url_imagens=produto_input.url_imagens,
            adicionais=[adicional.model_dump() for adicional in produto_input.adicionais],
            id_categoria=produto_input.id_categoria,
        )

        session.add(produto)
        session.commit()
        return ProdutoResponse(
            id=produto.id,
            nome=produto.nome,
            descricao=produto.descricao,
            preco=produto.preco,
            url_imagens=produto.url_imagens,
            adicionais=produto.adicionais,
            id_categoria=produto.id_categoria,
            categoria=produto.categoria.nome,
        )


    except IntegrityError:
        session.rollback()
        raise HTTPException(status_code=409, detail="Produto já cadastrado.")
    except Exception as e:
        session.rollback()
        logger.error(f"Erro ao cadastrar produto: {str(e)}")
        raise HTTPException(status_code=500, detail="Erro ao cadastrar produto.")


@router.put("/editar", response_model=ProdutoResponse)
async def editar_produto(
        produto_input: CadastrarProduto,
        user: Annotated[dict, Depends(get_current_usuario)],
        session: Session = Depends(get_session)
) -> ProdutoResponse:
    """
    Edita um produto existente.

    - **Parâmetros**:
        - `produto_input`: Objeto contendo os dados do produto a ser editado (id, nome, descrição, preço, url_imagem, id_categoria).
        - `user`: O usuário que está fazendo a requisição. Deve ser um administrador.

    - **Retorna** o produto atualizado com suas informações (nome, descrição, preço, imagem, categoria).

    - **Lança**:
        - `HTTPException` 403 se o usuário não tiver permissão.
        - `HTTPException` 400 se a categoria não existir.
        - `HTTPException` 404 se o produto não for encontrado.
        - `HTTPException` 409 se o produto já estiver cadastrado.
        - `HTTPException` 500 em caso de erro inesperado ao editar.
    """
    if not user or not user.get("is_adm"):
        raise HTTPException(status_code=403, detail="Usuário sem permissão.")

    if not session.query(Categoria).filter(Categoria.id == produto_input.id_categoria).first():
        raise HTTPException(status_code=400, detail="Categoria não existe.")

    try:
        # Busca o produto pelo ID
        produto = session.query(Produto).get(produto_input.id)

        if not produto:
            raise HTTPException(status_code=404, detail="Produto não encontrado.")

        # Atualiza os campos do produto
        produto.nome = produto_input.nome
        produto.descricao = produto_input.descricao
        produto.preco = produto_input.preco
        produto.url_imagem = produto_input.url_imagem
        produto.id_categoria = produto_input.id_categoria

        session.commit()

        return ProdutoResponse(
            id=produto.id,
            nome=produto.nome,
            descricao=produto.descricao,
            preco=produto.preco,
            url_imagem=produto.url_imagem,
            id_categoria=produto.id_categoria,
            categoria=produto.categoria.nome,
        )

    except IntegrityError:
        session.rollback()
        raise HTTPException(status_code=409, detail="Produto já cadastrado.")
    except Exception as e:
        session.rollback()
        raise HTTPException(status_code=500, detail=f"Erro ao editar produto: {str(e)}")
