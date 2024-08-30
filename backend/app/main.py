from fastapi import FastAPI
from routes import produtos_router, auth_router, pedidos_router, usuarios_router, categorias_router, admin_router

import jwt

app = FastAPI(title="Pizzaria Hackaton")

app.include_router(usuarios_router, prefix='/usuarios', tags=['Usuários'])
app.include_router(categorias_router, prefix='/categorias', tags=['Categorias'])
app.include_router(produtos_router, prefix='/produtos', tags=['Produtos'])
app.include_router(pedidos_router, prefix='/pedidos', tags=['Pedidos'])
app.include_router(auth_router, prefix='/auth', tags=['Auth'])
app.include_router(admin_router, prefix='/admin', tags=['Admin'])

@app.get('/')
async def index():
    return {
        "Pizzaria": "Hackaton"
    }

