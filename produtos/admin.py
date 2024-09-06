from django.contrib import admin
from produtos.models import Bebida, Pizza, Pedido, ItemPedido
 



# Register your models here.
admin.site.register(Pizza)
admin.site.register(Bebida)


class ItemPedidoInline(admin.TabularInline):
    model = ItemPedido
    extra = 1
    
@admin.register(Pedido)
class PedidoAdmin(admin.ModelAdmin):
    list_display = ['id', 'cliente', 'status', 'total', 'data_criacao']
    inlines = [ItemPedidoInline]