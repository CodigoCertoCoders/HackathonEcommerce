# HackathonEcommerce Código Certo - Pizzaria

## Configurações de ambiente

Crie o ambiente virutual (Só precisa ser feito uma vez)

```bash

python3 -m venv hackenv


```

Ative o ambiente virutal para isolar os pacotes python do projeto:

```bash

python3 -m venv hackenv

. hackenv/bin/activate

```

Atualize o PIP e installe as dependências do projeto.

```bash

python -m pip install --upgrade pip

pip install -r requirements.txt


```

Rode as migrações das tabelas do banco de dados

```bash

python manage.py migrate


```

Crie seu usuário no Django Admin!

```bash

python manage.py createsuperuser


```

Execute o servidor de desenvolvimento Django!

```bash

python manage.py runserver


```
