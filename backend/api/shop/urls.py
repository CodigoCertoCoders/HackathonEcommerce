from django.urls import path
from . import views

urlpatterns = [
    path('', views.getData),
]

produtos = [

]

usuarios = [

]

pedidos = [

]

urlpatterns.extend(produtos)
urlpatterns.extend(usuarios)
urlpatterns.extend(pedidos)