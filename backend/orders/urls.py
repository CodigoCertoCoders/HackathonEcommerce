from django.urls import path
from orders.viewsets import views

urlpatterns = [
    path('', views.OrdersList.as_view()),
    path('new/', views.OrdersCreate.as_view()),
    path('<int:order_id>/delete/', views.OrdersDelete.as_view()),
]