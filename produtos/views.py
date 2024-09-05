from django.shortcuts import render

from .models import Bebida, Pizza


def render_index(request):
    pizzas = Pizza.objects.all()
    bebidas = Bebida.objects.all()

    return render(request, 'index/home.html', {'pizzas': pizzas, 'bebidas': bebidas})
