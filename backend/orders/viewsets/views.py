from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status

class OrdersList(APIView):
    def get(self, request):
        data = {'Message': 'Hello There! OrdersList'}
        return Response(data)

class OrdersCreate(APIView):
    def post(self, request):
        data = {'Message': 'Hello There! OrdersCreate'}
        return Response(data)

class OrdersDelete(APIView):
    def delete(self, request, order_id):
        data = {'Message': 'Hello There! OrdersDelete', 'Order': order_id}
        return Response(data)