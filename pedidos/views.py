from django.shortcuts import render, get_object_or_404
from django.contrib.auth.decorators import login_required
from .models import Pedido



@login_required
def lista_pedidos(request):
    pedidos = Pedido.objects.filter(cliente=request.user)
    return render(request, 'pedidos/lista_pedidos.html', {'pedidos': pedidos})

@login_required
def detalhe_pedido(request, id):
    pedido = get_object_or_404(Pedido, id=id, cliente=request.user)
    return render(request, 'pedidos/detalhe_pedido.html', {'pedido': pedido})
