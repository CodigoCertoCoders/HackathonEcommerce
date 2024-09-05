from django.db import models

# Create your models here.


class Pizza(models.Model):
    PizzaType = models.TextChoices('PizzaType', 'DOCE SALGADA')
    category = models.CharField(choices=PizzaType, max_length=10)  # type: ignore
    name = models.CharField(max_length=10)
    price = models.FloatField(max_length=10)

    def __str__(self):
        return self.name


class Bebida(models.Model):
    name = models.CharField(max_length=10)
    price = models.FloatField(max_length=10)

    def __str__(self):
        return self.name
