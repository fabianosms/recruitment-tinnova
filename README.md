<h1 align="center">
    <br>Tinnova - avaliação prática<br/>
</h1>

<h2 align="center">
    <br>Questões 1 a 4<br/>
</h2>

- As quatro primeiras questões foram alocadas em um [repositório específico](https://github.com/fabianosms/recruitment-tinnova-questoes1a4)

<h2 align="center">
    <br>Questão 5<br/>
</h2>

## O projeto

Aplicação back-end Java API RESTful, utilizando JSON, com a manipulação de informações referentes a veículos no banco de dados


## Tecnologias utilizadas

- Java 11
- Spring Boot 2.7.3
- Spring Data JPA
- Hibernate
- Maven
- MySQL 5.5
- Git
- Postman (testes API)
- IntelliJ IDEA 2022.2.1 Ultimate Edition


## Arquitetura

- [Model](https://github.com/fabianosms/recruitment-tinnova/tree/master/src/main/java/com/machado/fabiano/recruitmenttinnova/model) contém as entidades elementares do banco de dados
- [Controller](https://github.com/fabianosms/recruitment-tinnova/tree/master/src/main/java/com/machado/fabiano/recruitmenttinnova/controller) recebe as requisições HTTP e invoca os métodos da Service
- [Service](https://github.com/fabianosms/recruitment-tinnova/tree/master/src/main/java/com/machado/fabiano/recruitmenttinnova/service) contém a lógica e as regras de negócio, assim como o acesso aos Repositories
- [Repository](https://github.com/fabianosms/recruitment-tinnova/tree/master/src/main/java/com/machado/fabiano/recruitmenttinnova/repository) contém os métodos de busca ao banco de dados
- [DTO](https://github.com/fabianosms/recruitment-tinnova/tree/master/src/main/java/com/machado/fabiano/recruitmenttinnova/dto) contém os modelos de transferência de dados de entrada (Form) e saída (DTO)
- [Exceptions](https://github.com/fabianosms/recruitment-tinnova/tree/master/src/main/java/com/machado/fabiano/recruitmenttinnova/exceptions) contém os handlers das eventuais exceções


## Outros elementos relevantes

- [pom.xml](https://github.com/fabianosms/recruitment-tinnova/blob/master/pom.xml) - contém principalmente as dependências utilizadas no projeto
- [application.properties](https://github.com/fabianosms/recruitment-tinnova/blob/master/src/main/resources/application.properties) - contém as configurações de acesso e persistência no banco de dados
- [application.properties](https://github.com/fabianosms/recruitment-tinnova/blob/master/src/main/resources/application.properties) - contém as configurações de acesso e persistência no banco de dados
- [schema.sql](https://github.com/fabianosms/recruitment-tinnova/blob/master/src/main/resources/schema.sql) - cria e configura as tabelas do banco de dados (a criação automática pelo Hibernate foi desabilitada)
- [data.sql](https://github.com/fabianosms/recruitment-tinnova/blob/master/src/main/resources/data.sql) - popula automaticamente todas as marcas no banco de dados, o que garante a validação da grafia das marcas e a distribuição de veículos por marca


## Endpoints

Os endpoints são baseados em localhost, porta 8080, e foram devidamente testados com o Postman, juntamente com o MySQL 5.5

*GET*
- http://localhost:8080/veiculos - retorna todos os veículos
- http://localhost:8080/veiculos?marca={marca}&ano={ano} - retorna os veículos conforme os parâmetros de marca e ano inseridos
- http://localhost:8080/veiculos/{id} - retorna os detalhes de um veículo
- http://localhost:8080/veiculos/ultimasemana - retorna os veículos cadastrados nos últimos 7 dias
- http://localhost:8080/veiculos/naovendidos - retorna a contagem dos veículos não vendidos
- http://localhost:8080/veiculos/decada - retorna a distribuição de veículos, de acordo com a década de fabricação
- http://localhost:8080/veiculos/marcas - retorna a distribuição de veículos, de acordo com cada fabricante

*POST*
- http://localhost:8080/veiculos - cadastra um veículo, de acordo com os dados contidos no JSON; não aceita marcas com erro de ortografia

*PUT*
- http://localhost:8080/veiculos/{id} - atualiza os dados do veículo com o id correspondente, de acordo com os dados contidos no JSON; não aceita marcas com erro de ortografia

*PATCH*
- http://localhost:8080/veiculos/{id} - atualiza alguns dados de o veículo com o id correspondente, de acordo com os dados contidos no JSON; não aceita marcas com erro de ortografia

*REMOVE*
- http://localhost:8080/veiculos/{id} - remove do banco de dados o veículo com o id correspondente


## Futuras melhorias

- Implementar testes unitários com JUnit
- Finalizar a documentação em Javadoc
- Documentar a API no Swagger
