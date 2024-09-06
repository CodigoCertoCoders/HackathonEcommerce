from django.contrib import admin

from produtos.models import Bebida, Pizza

# Register your models here.
admin.site.register(Pizza)
admin.site.register(Bebida)
