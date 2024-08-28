from fastapi import FastAPI
from routes.usuarios import router as usuarios_router
from routes.categorias import router as categorias_router
from routes.produtos import router as produtos_router

app = FastAPI(title="Pizzaria Hackaton")

app.include_router(usuarios_router, prefix='/usuarios', tags=['Usuários'])
app.include_router(categorias_router, prefix='/categorias', tags=['Categorias'])
app.include_router(produtos_router, prefix='/produtos', tags=['Produtos'])

@app.get('/')
async def index():
    return {
        "Pizzaria": "Hackaton"
    }

