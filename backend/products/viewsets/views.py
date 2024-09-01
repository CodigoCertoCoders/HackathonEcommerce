from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status

class ProductsList(APIView):
    def get(self, request):
        data = {'Message': "Hello There! Products List"}
        return Response(data)

class ProductsDetails(APIView):
    def get(self, request, product_id):
        data = {'Message': 'Hello There! Products Details', 'Product': product_id}
        return Response(data)