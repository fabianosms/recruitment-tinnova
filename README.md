<h1 align="center">
    <br>Tinnova - avaliação prática<br/>
</h1>
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


## Endpoints

Os endpoints são baseados em localhost, porta 8080, e foram devidamente testados com o Postman, juntamente com o MySQL 5.5

*GET*
- http://localhost:8080/veiculos - retorna todos os veículos
- http://localhost:8080/veiculos?marca={marca}&ano={ano} - retorna os veículos conforme os parâmetros de marca e ano inseridos
- http://localhost:8080/veiculos/{id} - retorna os detalhes de um veículo
- http://localhost:8080/veiculos/ultimasemana - retorna os veículos cadastrados nos últimos 7 dias
- http://localhost:8080/veiculos/naovendidos - retorna a contagem dos veículos não vendidos
- http://localhost:8080/veiculos/decada/{decada} - retorna os veículos conforme o ano de fabricação na década inserida (ex.: "1980", "2010")
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
