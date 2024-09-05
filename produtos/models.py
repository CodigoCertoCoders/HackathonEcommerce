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
