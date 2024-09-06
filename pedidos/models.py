from django.db import models
from django.contrib.auth.models import User
from produtos.models import Produto, Variacao

class Pedido(models.Model):
    STATUS_PEDIDO_CHOICES = [
        ('novo', 'Novo'),
        ('preparando', 'Em Preparo'),
        ('finalizado', 'Finalizado'),
        ('cancelado', 'Cancelado'),
    ]

    cliente = models.ForeignKey(User, on_delete=models.CASCADE)
    data_criacao = models.DateTimeField(auto_now_add=True)
    status = models.CharField(max_length=12, choices=STATUS_PEDIDO_CHOICES, default='novo')
    total = models.DecimalField(max_digits=8, decimal_places=2, default=0)

    def __str__(self):
        return f'Pedido {self.id} - {self.cliente.username}'

class ItemPedido(models.Model):
    pedido = models.ForeignKey(Pedido, related_name='itens', on_delete=models.CASCADE)
    produto = models.ForeignKey(Produto, on_delete=models.CASCADE, related_name='pedidos_itempedido')
    variacao = models.ForeignKey(Variacao, on_delete=models.SET_NULL, null=True, blank=True)
    quantidade = models.PositiveIntegerField(default=1)
    preco = models.DecimalField(max_digits=6, decimal_places=2)

    def __str__(self):
        return f'{self.quantidade}x {self.produto.nome}'

    def get_total(self):
        return self.quantidade * self.preco


 
