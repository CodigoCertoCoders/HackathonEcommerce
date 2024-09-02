from django.urls import path
from products.viewsets import views

urlpatterns = [
    path('', views.ProductsList.as_view()),
    path('<int:product_id>/', views.ProductsDetails.as_view())
]