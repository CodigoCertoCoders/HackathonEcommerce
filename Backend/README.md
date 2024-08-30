
### comando em sql para consultar uuid no banco
``SELECT CONCAT(
SUBSTRING(HEX(id), 1, 8), '-',
SUBSTRING(HEX(id), 9, 4), '-',
SUBSTRING(HEX(id), 13, 4), '-',
SUBSTRING(HEX(id), 17, 4), '-',
SUBSTRING(HEX(id), 21, 12)
) AS id,name,phone,email,cep FROM tb_client;``