from django.core.serializers import serialize
from django.http import JsonResponse

from .models import Bebida, Pizza

# Create your views here.


def product_list(request):
    pizzas = serialize('json', Pizza.objects.all())
    bebidas = serialize('json', Bebida.objects.all())
    data = {'products': [{'pizzas': pizzas}, {'bebidas': bebidas}]}

    return JsonResponse(data)
