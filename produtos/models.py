from django.db import models


class Pizza(models.Model):
    PizzaType = models.TextChoices('PizzaType', 'DOCE SALGADA')
    category = models.CharField(choices=PizzaType, max_length=10)  # type: ignore
    name = models.CharField(max_length=20)
    price = models.FloatField()
    description = models.TextField(default='')

    def __str__(self):
        return self.name


class Bebida(models.Model):
    name = models.CharField(max_length=20)
    price = models.FloatField()

    def __str__(self):
        return self.name
    
class Produto(models.Model):
    nome = models.CharField(max_length=255)
    preco = models.DecimalField(max_digits=10, decimal_places=2)


class Pedido(models.Model):
    cliente = models.CharField(max_length=255)
    data = models.DateTimeField(auto_now_add=True)
    status = models.CharField(max_length=50)  # Verifique se este campo existe
    total = models.DecimalField(max_digits=10, decimal_places=2)
class ItemPedido(models.Model):
    pedido = models.ForeignKey(Pedido, on_delete=models.CASCADE)
    produto = models.ForeignKey(Produto, on_delete=models.CASCADE, related_name='produtos_itempedido')
    quantidade = models.PositiveIntegerField()



class Variacao(models.Model):
    nome = models.CharField(max_length=100)
    preco_adicional = models.DecimalField(max_digits=6, decimal_places=2, default=0)
    produto = models.ForeignKey(Produto, on_delete=models.CASCADE, related_name='variacoes')

    def __str__(self):
        return self.nome

