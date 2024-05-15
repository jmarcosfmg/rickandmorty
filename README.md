# Rick and Morty API

A aplicação simula uma API que permite o CRUD de personagens e suas respectivas localidades de nascença.

<b>Regras do negócio:</b>

* Um personagem pode ser criado sem associação com alguma localidade;

* Um personagem pode ter todos os seus campos alterados, menos a data de criação e id;

* Uma localidade pode ser alterada para remover os personagens associados a ela;

* Uma localidade só poderá ser removida quando não houver personagens associados a ela;

### Endpoints:

#### Localização: 

Recurso: <i>/location</i>

Descrição: Endpoints do recurso de Localização;

Método | Caminho      | Descrição
------------- |--------------| -------------
POST  | " "          | Cria uma localização
GET  | "?id={ id }" | Busca uma ou mais localizações filtradas por id (opcional)
PUT  | " "          | Atualiza uma ou mais localizações
DELETE  | "/ { id }"     | Remove uma ou mais localizações


#### Personagem:

Recurso: <i>/character</i>

Descrição: Endpoints do recurso de Personagem;

Método | Caminho      | Descrição
------------- |--------------| -------------
POST  | " "          | Cria uma personagem
GET  | "?id={ id }" | Busca uma ou mais personagens filtradas por id (opcional)
PUT  | " "          | Atualiza uma ou mais personagens
DELETE  | "/ { id }"     | Remove uma ou mais personagens


### Como rodar a aplicação:

1. Baixar o repositório no dispositivo desejado;
2. Compilar o projeto usando o comando: |mvn clean install|
3. Utilizar o postman para realizar as chamadas dos endpoints

### Exemplos de requisições:

Utilizar a collection do Postman em [resources](https://github.com/jmarcosfmg/rickandmorty/tree/develop/resources)


