## Instalação

### Crie um virtual-environment utilizando o módulo venv
Por exemplo: `python -m venv .venv`
Irá criar um venv chamado ".venv"
### Ative o venv
No Windows:
`.venv/Scripts/activate` 
Linux/MacOS:
`source .venv/bin/activate`
Para desativar o venv, quando necessário:
`deactivate`

Activating the virtual environment will change your shell’s prompt to show what virtual environment you’re using, and modify the environment so that running `python` will get you that particular version and installation of Python.
### Instale as dependências
`pip install -r requirements.txt`

### Configure as variáveis de ambiente
Crie um arquivo '.env' no diretório raiz (este aqui) e cole o seguinte:
```
SECRET_KEY=a$a@!@o(e4gs8^^y#8cxr8tq@tk170%u7usi$yt)1b#w@2de%5
ALLOWED_HOSTS=*
DEBUG=True
```

### Comece a trabalhar
Para rodar o servidor de desenvolvimento:
`python manage.py runserver`

## Stack

[![Stack back-end](https://skillicons.dev/icons?i=django,python,sqlite)](https://skillicons.dev)