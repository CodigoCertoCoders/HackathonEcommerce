FROM python:3-alpine AS builder

WORKDIR /app

RUN python3 -m venv hackenv

RUN . hackenv/bin/activate

ENV VIRTUAL_ENV=/app/hackenv

ENV PATH="$VIRTUAL_ENV/bin:$PATH"

ENV PORT=8000

COPY . .

RUN python -m pip install --upgrade pip

RUN pip install -r requirements.txt

RUN python manage.py migrate

EXPOSE ${PORT}

CMD gunicorn --bind :${PORT} --workers 2 pizzaria.wsgi