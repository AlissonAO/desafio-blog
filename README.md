# desafio-blog

# Desafio Blog Framework
Instruções de uso para execução do sistema Back-End (O front é incorporado no back-end na pasta static ou pode ser executado no VSCODE)

1 - O banco de dados ja esta sendo apontado para um VPS onde roda um docker PostGress 

2 - Abrir o back-end do blog no IntelliJ ou na sua IDE de preferência

3 - Criar uma pasta de nome "teste" no diretorio c:/teste ou alterar em uploadController (UPLOADED_FOLDER)

4 - Compilar e executar

5 - Executar a Query contida no data.sql do projeto (resources) em um ambinte de banco de dados de preferência (pgAdmin 4 por exemplo) para popular o banco

6 - Executar no navegador a aplicação pelo endereço htt://localhost:8084 (padrão usado no sistema ou a porta que for configurada no application.properties)

7 - Agora basta criar um login e senha.


## Instruções de uso para execução do Front-End no VSCODE (necessário ter NODE versão 10 em diante já configurado)
1 - Abrir o VSCODE

2 - Abrir a aplicação front-end

3 - executar na pasta do projeto front para poder rodar a aplicação npm install -g angular/cli

4 - executar o projeto: ng serve

5 - abrir o navegador no endereço: http://localhost:4200

6 - no back-end tem que descometar a linha (// .antMatchers("/**").permitAll() //para testar pelo servidor http://localhost:4200 do angular tem que habilitar em produção desabilitar) na classe SecurityConfigurations


# Swagger UI
> Caso queira testar a api favor entra no http://localhost:8084/swagger-ui.html#/ 

![image](https://user-images.githubusercontent.com/457048/140665915-4e72d6e7-42d1-4177-8adf-28bd39a274ce.png)
