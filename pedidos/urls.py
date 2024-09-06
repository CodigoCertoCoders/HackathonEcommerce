from django.urls import path
from . import views import lista_pedidos, detalhe_pedido





urlpatterns = [
    path('pedidos/', lista_pedidos, name='lista_pedidos'),
    path('pedidos/<int:id>/', detalhe_pedido, name='detalhe_pedido'),
]
