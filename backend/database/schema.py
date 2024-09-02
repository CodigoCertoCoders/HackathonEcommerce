from sqlalchemy import JSON, Boolean, Column, DateTime, Float, ForeignKey, Integer, String, Table, create_engine, func
from sqlalchemy.orm import Session, declarative_base, relationship

URL = "sqlite:///database/database.db"

engine = create_engine(URL)

def get_session():
    session = Session(bind=engine)
    try:
        yield session
    finally:
        session.close()


Base = declarative_base()

class PedidoProduto(Base):
    __tablename__ = 'pedido_produto'
    pedido_id = Column(Integer, ForeignKey('pedido.id'), primary_key=True)
    adicionais_pedido = Column(JSON, nullable=True)
    observacoes = Column(String, nullable=True)
    produto_id = Column(Integer, ForeignKey('produto.id'), primary_key=True)
    quantidade = Column(Integer, nullable=False)
    preco_total = Column(Float, nullable=False)

class Usuario(Base):
    __tablename__ = 'usuario'
    
    id = Column(Integer, primary_key=True)
    nome = Column(String, nullable=False)
    telefone = Column(String, nullable=False)
    endereco = Column(JSON, nullable=False)
    email = Column(String, nullable=False, unique=True)
    hashed_senha = Column(String, nullable=False)
    adm = Column(Boolean, default=False)
    
    pedidos = relationship('Pedido', back_populates='usuario')

class Categoria(Base):
    __tablename__ = 'categoria'
    
    id = Column(Integer, primary_key=True)
    nome = Column(String, nullable=False, unique=True)

    produtos = relationship('Produto', back_populates='categoria')


class Produto(Base):
    __tablename__ = 'produto'
    
    id = Column(Integer, primary_key=True)
    nome = Column(String, nullable=False)
    descricao = Column(String, nullable=False)
    preco = Column(Float, nullable=False)
    url_imagens = Column(JSON, nullable=False)
    adicionais = Column(JSON, nullable=True)
    id_categoria = Column(Integer, ForeignKey('categoria.id'), nullable=True)
    
    categoria = relationship('Categoria', back_populates='produtos')
    pedidos = relationship('Pedido', secondary='pedido_produto', back_populates='produtos')

class Pedido(Base):
    __tablename__ = 'pedido'
    
    id = Column(Integer, primary_key=True)
    id_usuario = Column(Integer, ForeignKey('usuario.id'), nullable=False)
    horario_pedido = Column(DateTime, nullable=False, default=func.now())
    total = Column(Float, nullable=False)
    
    usuario = relationship('Usuario', back_populates='pedidos')
    produtos = relationship('Produto', secondary='pedido_produto', back_populates='pedidos')

Base.metadata.create_all(engine)