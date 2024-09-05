from django.shortcuts import render


def render_index(request):
    return render(request, 'index/home.html', {})
