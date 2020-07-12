# XY-Inc
O xy-inc é um serviço de gerenciamento de sistema de GPS onde é possível cadastrar Pontos de Interesses (POI).

Nesse sistema é possível:

- Cadastrar pontos de interesses (POIs)
- Listar POIs cadastrados
- Listar POIs por proximidade

# Tecnologias
- `Java 11 (Open JDK)` - Principal linguagem de programação
- `Spring boot` - Framework base
- `Lombok` - Biblioteca focada em produtividade e redução de código
- `PostgreSql` - Sistema gerenciador de banco de dados objeto relacional
- `Swagger` - Estrutura para documentação da API
- `Docker` - Plataforma para executar e criar containers
- `Shell Script` - Arquivo executável, com instruções definidas para gerenciamento de versão

# Endpoints
A API do XY-Inc possui os seguintes Endpoints:

## 1 - Cadastro de POI
Endpoint para realizar cadastro de uma lista de POIs
- Path: POST/v2/poi/cadastrar
- Headers:
  - Content-Type: `application/json`
- Body:
```text
[
  {
    "coordenadaX": 99.99,
    "coordenadaY": 99.99,
    "nome": "Minha Casa"
  }
]
```
- Resposta esperada: Code 201 (CREATED)

## 2 - Listar todos os POIs
Endpoint para listar todos os POIs cadastrados
- Path: GET/v2/poi/listar
- Resposta esperada: Code 200 (OK)
- Body:
```text
[
  {
    "coordenadaX": 99.99,
    "coordenadaY": 99.99,
    "nome": "Minha Casa"
  }
]
```
## 3 - Listar todos os POIs
Endpoint para listar todos os POIs cadastrados por proximidade
- Path: GET/v2/poi/proximidade
- Parâmetros:
  - coordX(number): Coordenada X como referência
  - coordY(number): Coordenada Y como referência
  - dMax(number): Distância máxima para busca de POIs nas proximidades
- Resposta esperada: Code 200 (OK)
- Body:
```text
[
  {
    "coordenadaX": 99.99,
    "coordenadaY": 99.99,
    "nome": "Minha Casa"
  }
]
```
# Executando a API
Passo a passo para executar o projeto no Windows, onde todos os acessos e comandos foram executados via `Prompt de Comando` ou `Power Shell`:

- Tenha todas as ferramentas instaladas:
  - `Java JDK 11`
  - `Apache Maven`
  - `Docker`
  - `Docker Compose`
  - `Git`
- Faça o clone do projeto: `git clone https://github.com/volgarini/xy-inc.git`
- Na pasta raiz do projeto `xy-inc` execute o commando:
  - `mvn clean package -DskipTests`
- Após finalizar o empacotamento, vamos criar a imagem do Docker:
  - `docker build -t volgarini/xy-inc .`
- Feito isso, a última parte é criar o container, para isso, na pasta do projeto `xy-inc` execute:
  - `docker-compose up -d`
  
Prontinho, a sua API está rodando na porta `8081`.

# Documentação com Swagger
A Swagger UI fornece uma documentação totalmente dinâmica da aplicação, basta acessar a url: http://localhost:8081/

# Fechamento de Patch via Shell Script
É possível realizar um fechamento de patch utilizando o arquivo `patch-release-finish.sh`. Esse arquivo irá fechar a versão atual, vai incrementar com mais 1 o valor na patch na nova branch.
Obs.: Caso esteja no Windows precisa ser usado alguma ferramenta de terceiro, e se o Git estiver instalado no Windows é possível executar com o Git Bash.

# Teste Unitário com jUnit + Maven
Para executar os testes, certifique-se dos seguintes passos:
 - Verifique se o container `xy_inc_postgres` esteja rodando
 - Altere o arquivo `xy-inc/src/main/resources/application.properties`, tire o comentário # do valor `spring.datasource.url=jdbc:postgresql://localhost:5432` e comente com # o valor `spring.datasource.url=jdbc:postgresql://postgres:5432`
 - Agora na raiz do projeto, execute o comando `mvn test`

Pronto, os testes unitários irão rodar!

# Desenvolvedor
**Lucas Volgarini**
- Linkdin: https://www.linkedin.com/in/lucasvolgarini/
- Github: https://github.com/volgarini
- Email: lucasvolgarini@gmail.com
