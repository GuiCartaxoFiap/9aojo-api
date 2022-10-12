# 9AOJO API ABC TECH API

A ABC TechService é uma empresa que presta serviços para diferentes empresas dos ramos de telecomunicações, elétrica ou manutenções de linha branca, como geladeiras, fogões e máquinas de lavar. 

## Desafio

No desafio da nossa fase 3, trabalharemos na criação de uma POC (Proof of Concept) para colocar em prática diversos conhecimentos como a criação de um aplicativo multiplataforma que será integrado ao nosso microsserviço.

Durante essa fase, com o apoio do mentor, será possível aprender de forma prática sobre o desenvolvimento de cada etapa do projeto, back-end, front-end mobile e web, testes e a publicação em uma cloud pública. Para isso, teremos como base um case proposto na forma de uma solicitação de desenvolvimento de software por parte de uma empresa. Vamos analisar?

Alguns problemas reportados por clientes finais e pelas empresas parceiras estão gerando prejuízos e riscos operacionais que podem levar a quebra de contratos.

Com isso, as áreas de TI e CS (sucesso do cliente ou customer success) levantaram os possíveis causadores dos problemas, e pontos abaixo foram os que mais apareceram nas pesquisas realizadas:

- Aplicativo não captura informação de geolocalização ao início e término de serviço, possibilitando que o prestador não compareça para prestação do serviço.
- Reclamação dos prestadores, aplicativo lento e que descarrega o celular rapidamente. Com isso, muitos ainda acabam utilizando fichas e recibos físicos.
- É complexo para adicionar os serviços realizados e a ordem.
- O sistema não gera relatório do tempo gasto para realização do serviço.
- Ao finalizar, o serviço não exige nenhuma confirmação do cliente. 

### Prerequisitos

Com esses pontos levantados e urgência para a resolução, a diretoria da ABC TechService optou e liberou orçamento para a criação de um novo sistema e aplicativo que ficará em posse do prestador durante os atendimentos. E, com isso, foi solicitado para sua empresa um novo sistema MVP que será testado durante quatro semanas, com as seguintes funcionalidades:

- Sistema deve ser rápido e escalável.
- O aplicativo deve funcionar em Android, iOS e Web. 
- O aplicativo identificará o prestador por um código numérico.
- O aplicativo deverá contar com uma lista de serviços prestados.
- Cada ordem deverá ter ao menos 1 serviço prestado e poderá ter até 15 serviços realizados.
- O aplicativo deverá gravar a hora e geolocalização ao iniciar o atendimento.
- O aplicativo deverá gravar a hora e geolocalização ao finalizar o atendimento.
- Ao finalizar, todas essas informações devem ser enviadas para o servidor e gravadas em um banco de dados.

### Como executar este projeto

Projeto depende de uma instancia de MySQL local para isso tanto poderá utilizar uma standalone como um container docker local com esta imagem, para auxiliar utilize.

*Docker Exemplo*
```sh
docker run -it --rm --name local-mysql -p 3306:3306 -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=homestead mysql
```

Com migrations que estão definidas na aplicação não existe necessidade para interação com a base inicial para inicio da  aplicação.

Também é necessário que as seguintes variaveis de ambiente para que aplicação funcione perfeitamente.

```sh
$ DB_HOSTNAME=localhost
$ DB_PORT=3306
$ DB_NAME=homestead
$ DB_USERNAME=root
$ DB_PASSWORD=root
```

Para facilitar utilize o _Profiles_ do Spring, utilizando o _application-dev_ para configurações locais

```yml
server:
  port: 8080

build.name: @project.name@
build.version: @project.version@

spring:
  datasource:
    url: jdbc:mysql://localhost:3306/homestead
    username: root
    password: root
  jpa:
    hibernate:
      ddl-auto: none

```
Para rodar o projeto após toda a configuração utilize o comando abaixo

**Utilizando profile default**
```sh
$ ./mvnw spring-boot:run
```

**Utilizando profile dev**
```sh
$ ./mvnw spring-boot:run -Dspring-boot.run.profiles=dev
```

## Para acesso de documentação da API

Acesse a documentação da API utilizando este [link para acesso](http://abctechapi-env-1.eba-qf59pajz.us-east-1.elasticbeanstalk.com/swagger-ui.html), documentação estruturada utilizando **Swagger2**.

Para acesso local http://localhost:8080/swagger-ui.html


## API

### GET /assistance
Enpoint GET de listagem das assistências. Cara Ordem de serviço criada, terá uma ou mais assistências vinculadas

```json
[
  {
    "description": "string",
    "id": 0,
    "name": "string"
  }
]
```

### POST /order
Endpoint POST referente a criação de uma ordem de serviço. Enviados o identicados único do operador que irá executar o serviço, as assistências relacionadas, data de início e término e dados da localização. Segue um payload de exemplo:

```json
{
  "assists": [
    0
  ],
  "end": {
    "dateTime": "2022-10-12T17:57:25.822Z",
    "latitude": 0,
    "longitude": 0
  },
  "operatorId": 0,
  "start": {
    "dateTime": "2022-10-12T17:57:25.822Z",
    "latitude": 0,
    "longitude": 0
  }
}
```

### GET /order/{operator-id}
Endpoint GET que lista todas as ordens de serviço relacionadas a determinado operador

```json
[
  {
    "assists": [
      {
        "description": "string",
        "id": 0,
        "name": "string"
      }
    ],
    "end": {
      "dateTime": "2022-10-12T17:57:29.725Z",
      "latitude": 0,
      "longitude": 0
    },
    "id": 0,
    "operatorId": 0,
    "start": {
      "dateTime": "2022-10-12T17:57:29.725Z",
      "latitude": 0,
      "longitude": 0
    }
  }
]
```
