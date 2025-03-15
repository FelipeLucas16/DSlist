# 🎮 DSList - Catálogo de Jogos

Este é um projeto desenvolvido com **Spring Boot** para gerenciar uma lista de jogos. Ele permite adicionar, listar, atualizar e remover jogos, além de categorizá-los.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **Hibernate**
- **Banco de Dados H2 / PostgreSQL**
- **JPA e DTOs**
- **API REST**
- **Frontend em HTML + JavaScript**

## ⚙️ Funcionalidades

✅ Listar todos os jogos
✅ Buscar jogo por ID
✅ Adicionar novo jogo
✅ Atualizar informações de um jogo
✅ Excluir jogo do catálogo
✅ Organizar jogos por categorias

## 📌 Como Rodar o Projeto

### Backend (Spring Boot)
1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/dslist.git
   ```
2. Acesse a pasta do projeto:
   ```bash
   cd dslist
   ```
3. Configure o **application.properties** com as credenciais do banco de dados.
4. Execute o projeto com:
   ```bash
   mvn spring-boot:run
   ```

## 🔥 Exemplo de Consumo da API
### Requisição GET para listar jogos:
```http
GET http://localhost:8080/games
```
### Resposta JSON:
```json
[
  {
    "id": 1,
    "title": "The Legend of Zelda: Breath of the Wild",
    "year": 2017,
    "imgUrl": "https://link-da-imagem.com/zelda.jpg",
    "shortDescription": "Aventura em mundo aberto com Link."
  }
]


