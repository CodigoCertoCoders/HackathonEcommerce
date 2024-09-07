### Pré-requisitos para rodar o programa 🔗
#### JDK - 17 (Java Developer Kit)
#### IDE Intellij (recomendado)

-----------------------------------------------
### Como rodar o projeto 🚀
#### 1. Crie um arquivo application.properties ou application.yml e configure sua conexão com o banco de dados
#### 2. Crie uma pasta assets dentro de ``resources>static``,e depois adicione ``spring.web.resources.static-locations=classpath:/static/`` ao application.properties
#### 3. Clique no botão de run na barra do topo próximo ao nome "BackendApplication"

-----------------------------------------------
### Comando em SQL para consultar uuid no banco 📍
#### (Exemplo para tb_client)

``SELECT CONCAT(
SUBSTRING(HEX(id), 1, 8), '-',
SUBSTRING(HEX(id), 9, 4), '-',
SUBSTRING(HEX(id), 13, 4), '-',
SUBSTRING(HEX(id), 17, 4), '-',
SUBSTRING(HEX(id), 21, 12)
) AS id,name,email,uf,city,neighborhood,road,number_house,complement,password,token FROM tb_client;``


-------------------------------------------------

### Swagger 📍
#### [Link Swagger](https://maltex-back-production.up.railway.app/swagger-ui/index.html)

-----------------------------------------------
### Link API em produção 📍
#### https://maltex-back-production.up.railway.app
