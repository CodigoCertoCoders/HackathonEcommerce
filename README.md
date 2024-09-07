# Hackaton Grupo 7

Hackaton Grupo 7

## Configurando o ambiente de desenvolvimento

### Instale Python 3.12.3

Você pode baixar python diretamente do site oficial mas é altamente recomendado usar um gerenciador de versão como o [pyenv](https://github.com/pyenv/pyenv) e o [pyenv-win](https://https://github.com/pyenv-win/pyenv-win).

Com o pyenv instalado você poderá instalar a versão do python adequada da seguinte forma:

#### Linux e Windows

```bash

# Instalando o pyenv
curl https://pyenv.run | bash

# Configurando o shell
echo 'export PYENV_ROOT="$HOME/.pyenv"' >> ~/.bashrc
echo 'command -v pyenv >/dev/null || export PATH="$PYENV_ROOT/bin:$PATH"' >> ~/.bashrc
echo 'eval "$(pyenv init -)"' >> ~/.bashrc
exec "$SHELL"

pyenv version

# Instalando python 3.12.3

pyenv install 3.12.3
pyenv local 3.12.3


#######################################
#                                     #
###### UTILIZE PIP OU O POETRY ########
#   Instalando venv e dependências    #
#######################################

# PIP
python3 -m venv hackenv

. hackenv/bin/activate

pip install --upgrade pip
pip install .

# POETRY
sudo apt update
sudo apt install pipx

pipx ensurepath
pipx install poetry

poetry shell
poetry install


´´´


```powershell
Invoke-WebRequest -UseBasicParsing -Uri "https://raw.githubusercontent.com/pyenv-win/pyenv-win/master/pyenv-win/install-pyenv-win.ps1" -OutFile "./install-pyenv-win.ps1"; &"./install-pyenv-win.ps1"

pyenv --version

# Instalando python 3.12.3

pyenv install 3.12.3
pyenv local 3.12.3

#######################################
#                                     #
###### UTILIZE PIP OU O POETRY ########
#   Instalando venv e dependências    #
#######################################

# PIP

pip install --upgrade pip
pip install .

# POETRY
py -m pip install --user -U pipx
.\pipx.exe ensurepath

pipx install poetry
poetry shell
poetry install


### Clone o projeto diretamente do Github

link para o [Repositório](https://github.com/bentoluizv/HackathonEcommercePizza)!!

### O arquivo pyproject.toml

[tool.poetry.dependencies]
python = ""
pydantic = ""
fastapi = ""
sqlalchemy = ""

[tool.poetry.group.dev.dependencies]
pytest = ""
pytest-cov = ""
taskipy = ""
ruff = ""
httpx = ""

#### Boas práticas na escrita do código com ruff

```sh
[tool.ruff]
line-length = 79
extend-exclude = ['migrations']

[tool.ruff.lint]
preview = true
select = ['I', 'F', 'E', 'W', 'PL', 'PT']

[tool.ruff.format]
preview = true
quote-style = 'single'
```

#### Automatizando comandos com taskipy

```sh
[tool.taskipy.tasks]
lint = 'ruff check . && ruff check . --diff'
format = 'ruff check . --fix && ruff format .'
dev = 'fastapi dev app/api/main.py'
pre_test = 'task lint'
test = 'pytest -s -x --cov=app -vv'
post_test = 'coverage html'
run = 'fastapi run app/api/main.py'
```

Para rodar qualquer comando não esqueça de ativar o ambiente virutal e apenas digite `task <command>`.

### Testes com pytest

O projeto utiliza pytest para rodar testes de integração que cobrem operações com o banco de dado e endpoints da api.

Os testes podem ser executados de forma automatizada gerando coverage report e também checando o linter através do comando `task test`

### Rodando o projeto

Com o projeto configurado e testes passando digite o comando `task dev` para executar o servidor no modo desenvolvimento e `task run` para o modo de produção.
