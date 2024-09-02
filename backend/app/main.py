import logging
import os

from fastapi import FastAPI
from fastapi.middleware.cors import CORSMiddleware
from routes import (
    produtos_router,
    auth_router,
    pedidos_router,
    usuarios_router,
    categorias_router,
    admin_router,
)
from starlette.responses import JSONResponse

# Criar pasta de logs se não existir
os.makedirs("logs", exist_ok=True)

# Configuração do logging
logging.basicConfig(
    level=logging.INFO,
    format='%(asctime)s - %(name)s - %(levelname)s - %(message)s',
    handlers=[
        logging.FileHandler("logs/app.log"),
        logging.StreamHandler()  # Para visualizar os logs no console
    ]
)

app = FastAPI(title="Pizzaria Hackaton")

# Configuração de CORS
app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],  # podemos adicicionar a rota do front dps
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)


# Incluir os routers com prefixos e tags
routers = [
    (usuarios_router, '/usuarios', ['Usuários']),
    (categorias_router, '/categorias', ['Categorias']),
    (produtos_router, '/produtos', ['Produtos']),
    (pedidos_router, '/pedidos', ['Pedidos']),
    (auth_router, '/auth', ['Auth']),
    (admin_router, '/admin', ['Admin']),
]

for router, prefix, tags in routers:
    app.include_router(router, prefix=prefix, tags=tags)

@app.get('/')
async def index() -> dict:
    """Endpoint principal da aplicação."""
    return {"Pizzaria": "Hackaton"}

# Middleware de tratamento de erros
@app.exception_handler(Exception)
async def global_exception_handler(request, exc):
    logging.error(f"Erro inesperado: {exc}")
    return JSONResponse(
        status_code=500,
        content={"detail": "Ocorreu um erro inesperado. Tente novamente mais tarde."},
    )
