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
# Documentação com Swagger
A Swagger UI fornece uma documentação totalmente dinâmica da aplicação, basta acessar a url: http://localhost:8081/
