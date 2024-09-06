### Pré-requisitos para rodar o programa
#### JDK (Java Developer Kit) 17
#### IDE Intellij (recomendado)

-----------------------------------------------
### Como rodar o projeto
#### Clique no botão de run na barra do topo próximo ao nome "BackendApplication"

-----------------------------------------------
### Endpoint's
#### Método GET | Buscar cliente pelo id | "http://localhost:8080/clients/{uuid}"

#### Método POST | Criar cliente | "http://localhost:8080/clients"
##### Corpo da requisição JSON
``{
    "name":"Pedro",
    "cep":"44090000",
    "email":"pedro@gmail.com",
    "phone":"75990000000",
    "password":"12345678"
}``


#### Método PUT | Atualizar cliente | "http://localhost:8080/clients/{uuid}"
##### Corpo da requisição JSON
``{
"name":"Joao",
"cep":"44091000",
"phone":"75991000000"
}``

#### Método DELETE | Deletar cliente pelo id | "http://localhost:8080/clients/{uuid}"

-----------------------------------------------
### Comando em sql para consultar uuid no banco
``SELECT CONCAT(
SUBSTRING(HEX(id), 1, 8), '-',
SUBSTRING(HEX(id), 9, 4), '-',
SUBSTRING(HEX(id), 13, 4), '-',
SUBSTRING(HEX(id), 17, 4), '-',
SUBSTRING(HEX(id), 21, 12)
) AS id,name,phone,email,cep FROM tb_client;``


-------------------------------------------------

### Swagger
#### [Link Swagger](https://maltex-back-production.up.railway.app/swagger-ui/index.html)

-------------------------------------------------
### Link da produção
#### https://maltex-back-production.up.railway.app