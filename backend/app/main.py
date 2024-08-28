from fastapi import FastAPI
from routes.usuarios import router as usuarios_router

app = FastAPI(title="Pizzaria Hackaton")

app.include_router(usuarios_router, prefix='/usuarios', tags=['Usuários'])

@app.get('/')
async def index():
    return {
        "Pizzaria": "Hackaton"
    }

