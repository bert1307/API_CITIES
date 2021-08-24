# Everis Bootcamp: Quality Assurance Beginner #2 - DIO
## Cities API Rest
### Projeto Final: "Construindo uma API Rest de consulta de cidades do Brasil do zero até a produção"
### Descrição: 
Neste projeto você terá o desafio de desenvolver uma API Rest de 
consulta de cidades do Brasil com dados comparativos. Iremos navegar pelas boas práticas de Java e do Spring, 
popular o bando de dados Postgres e criar um serviço para o cálculo de distância entre cidades.
## Desenvolvimento:
### Links:
    André Gomes (DIO): https://github.com/andrelugomes/digital-innovation-one/tree/master/cities-api
    Chinnon Santos (DB): https://github.com/chinnonsantos/sql-paises-estados-cidades
    
    Extensão formatação DB Chrome: JSON Formatter
### Requisitos:
* #### SO: Windows 10
* #### IDE: IntelliJ Community
* #### Linguagem: Java 8 (suporte Heroku)
* #### DataBase: Postgres
* #### Spring Boot
* #### Docker
* #### Postman
* #### Heroku CLI
* #### Git/Github
* #### PowerShell
### Spring Boot: 
Framework para gerar a estrutura completa e configurada do projeto
* #### link: https://start.spring.io/
* #### Gradle: Gerenciador de dependências 
* #### Dependências: Spring Web, Spring Data JPA, PostgresSQL Driver 

![](https://github.com/bert1307/API_CITIES/blob/master/img/springboot_img.png)

#### 🐘 build.gradle: 

    mudar runtimeOnly 'org.postgresql:postgresql'
    para  implementation 'org.postgresql:postgresql'    


### Mapeamento Json (tipo de dado tabela DDD) pelo Hibernate:

    Dependência build.gradle: implementation 'com.vladmihalcea:hibernate-types-52:2.9.8'
### Docker:
Programa que gera um container para a aplicação desenvolvida usando a imagem do Postgres
* #### Configuração do Spring para o Docker no IntelliJ 
Conectar com o DB local criado no container Docker:
#### link: https://github.com/bert1307/API_CITIES/blob/master/src/main/resources/application.properties
    Procfile: arquivo default para configuração para o Heroku
* #### PowerShell comandos:
Criar um container:

    docker run --name cities-db -d -p 5432:5432 -e POSTGRES_USER=user_api -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=cities-api postgres
    
Criar outro container, mapeando os arquivos do diretório /Postgres/

    docker run -it --rm --net=host -v ${PWD}:/tmp postgres /bin/bash

Rodar arquivos e DB do docker da app do Heroku :
    
    DB:
    psql -h localhost -U user_api cities-api 
    Arquivos:
    psql -h localhost -U user_api cities-api -f /tmp/pais.sql
    psql -h localhost -U user_api cities-api -f /tmp/estado.sql
    psql -h localhost -U user_api cities-api -f /tmp/cidade.sql
Extensões implementadas no Postgres que habilitam o cálculo da distância usando somente o DB (query):
    
    //habilita o operador lógico <@>
    CREATE EXTENSION cube;

    //habilita os métodos da extensão ll_to_earth()
    CREATE EXTENSION earthdistance;
Query Earth Distance:
    
    Point: devolve a distância em milhas
    select ((select lat_lon from cidade where id = 4929) <@> (select lat_lon from cidade where id=5254)) as distance;
    
    Cube: devolve a distância em metros
    select earth_distance(ll_to_earth(-21.95840072631836,-47.98820114135742),ll_to_earth(-22.01740074157715,-47.88600158691406)) as distance;
    
![](https://github.com/bert1307/API_CITIES/blob/master/img/DOCKER.gif)

### Heroku
Plataforma em nuvem como serviço que irá dar suporte para colocar a API em produção
* #### Configuração do Spring para o Heroku no IntelliJ
Conectar com o DB criado na app no Heroku:
#### link: https://github.com/bert1307/API_CITIES/blob/master/src/main/resources/application-heroku.properties
    Procfile: Arquivo default de configuração para rodar a app no Heroku
    web: java -jar -Dspring.profiles.active=heroku build/libs/cities-api-0.0.1-SNAPSHOT.jar
* #### PowerShell comandos:
Dentro do diretório do projeto, enviar projeto para um repositório existente no github

    //git status
    git init
    git add .
    git commit -m "primeiro commit projeto"
    git remote add origin https://github.com/...
    git push -u origin master
    
Criar uma app no Heroku:
    
        heroku create
        git push heroku master
Criar o DB (Postgres) na app (Heroku):

        heroku addons:create heroku-postgresql
Rodar arquivos e DB na app no Heroku:

    No diretótio /Postgres/
    docker run -it --rm --net=host -v ${PWD}:/tmp postgres /bin/bash

    DB:
    psql -h ec2-34-234-12-149.compute-1.amazonaws.com -U noyobvttgsduzj dek7ks1urgnd
    Arquivos:
    psql -h ec2-34-234-12-149.compute-1.amazonaws.com -U noyobvttgsduzj dek7ks1urgnd -f /tmp/pais.sql
    psql -h ec2-34-234-12-149.compute-1.amazonaws.com -U noyobvttgsduzj dek7ks1urgnd -f /tmp/estado.sql
    psql -h ec2-34-234-12-149.compute-1.amazonaws.com -U noyobvttgsduzj dek7ks1urgnd -f /tmp/cidade.sql
    
![](https://github.com/bert1307/API_CITIES/blob/master/img/HEROKU.gif)

### Postman
Programa API client que possui uma interface que permite realizar requisições de diferentes métodos HTTTP

#### Docker

    http://localhost:8080/countries
    http://localhost:8080/state
    http://localhost:8080/cities
    * milhas
    http://localhost:8080/distances/by-points?from=4929&to=5254 
    * metros
    http://localhost:8080/distances/by-cube?from=4929&to=5254 
#### Heroku  
    https://agile-beyond-95122.herokuapp.com/countries
    https://agile-beyond-95122.herokuapp.com/state
    https://agile-beyond-95122.herokuapp.com/cities
    * milhas
    https://agile-beyond-95122.herokuapp.com/distances/by-points?from=4929&to=5254
    * metros
    https://agile-beyond-95122.herokuapp.com/distances/by-cube?from=4929&to=5254 
    
## Resultado:
![](https://github.com/bert1307/API_CITIES/blob/master/img/API.gif)

    







