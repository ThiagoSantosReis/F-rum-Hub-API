## 📄 Descrição

FórumHub é uma API REST desenvolvida em **Java com Spring Boot**, criada como **challenge alura**.  
Ela permite gerenciar tópicos de discussão com **CRUD completo** e **autenticação JWT**, garantindo que apenas usuários autenticados possam interagir com os recursos da API.

Este projeto demonstra habilidades em:  

- Spring Boot e Spring Data JPA  
- Spring Security e JWT  
- Validação de dados e tratamento de erros  
- Banco de dados MySQL com migrations Flyway  
- Criação de endpoints RESTful seguindo boas práticas  

---

## 🛠 Tecnologias Utilizadas

- Java 17  
- Spring Boot 3  
- Spring Security  
- Spring Data JPA  
- MySQL  
- Flyway  
- JWT (JSON Web Token)  
- Lombok  

---

## ⚙️ Funcionalidades

- **Cadastro de usuário** (`POST /usuarios`)  
- **Login** (`POST /login`) com geração de JWT  
- **CRUD de tópicos** (`/topicos`):  
  - Criar tópico (`POST /topicos`)  
  - Listar todos os tópicos (`GET /topicos`)  
  - Detalhar tópico específico (`GET /topicos/{id}`)  
  - Atualizar tópico (`PUT /topicos/{id}`)  
  - Excluir tópico (`DELETE /topicos/{id}`)  
- **Autenticação e autorização** usando JWT  

---

## 🚀 Como Executar

1. Configure o banco de dados MySQL e atualize o arquivo `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forumhub
spring.datasource.username=root
spring.datasource.password=1234
spring.jpa.hibernate.ddl-auto=update
spring.flyway.enabled=true
jwt.secret=yourSecretKey
jwt.expiration=3600000
```


2. Execute as migrations Flyway para criar as tabelas.

3. Compile e rode o projeto:
```bash
./gradlew bootRun
```

4. Teste a API usando Postman ou Insomnia.

# 🧪 Testes de Requisição no Postman

exemplos de requisições para testar a API **FórumHub** utilizando **Postman**.

---

## 🔹 1. Criar Usuário

**Endpoint:** `POST /usuarios`  
**Descrição:** Cadastra um novo usuário para autenticação.

**Requisição:**
```http
POST http://localhost:8080/usuarios
Content-Type: application/json

{
  "login": "usuario1",
  "senha": "123456",
  "role": "ROLE_ROLE_ADMIN"
}
```

## 🔹 2. Logar Usuário

**Endpoint:** `POST /usuarios`  
**Descrição:** Cadastra um novo usuário para autenticação.

**Requisição:**
```http
POST http://localhost:8080/login
Content-Type: application/json

{
  "login": "admin2",
  "senha": "12345"
}
```
**Resposta esperada:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}
```
Obs: Copie o token retornado para usar nas próximas requisições.

## 🔹 3. Criar tópico

**Endpoint:** `POST /topicos`  
**Descrição:** Cria um novo tópico. Requer token JWT.

**Requisição:**
```http
POST http://localhost:8080/topicos
Content-Type: application/json
Authorization: Bearer <token>

{
  "titulo": "Como usar Spring Security?",
  "mensagem": "Preciso de ajuda para implementar JWT na minha API",
  "autor": "admin2",
  "curso": "Spring Boot"
}
```
**Resposta esperada:**
```json
{
  "id": 1,
  "titulo": "Como usar Spring Security?",
  "mensagem": "Preciso de ajuda para implementar JWT na minha API",
  "dataCriacao": "2025-08-13T17:00:00",
  "status": "NAO_RESPONDIDO",
  "autor": "admin2",
  "curso": "Spring Boot"
}
```
## 🧩 Outras Requisições da API

Além das operações de criação de usuário, login e criação de tópicos, a API FórumHub oferece os seguintes endpoints para gerenciamento de tópicos:

| Método | Endpoint               | Descrição                                      |
|--------|-----------------------|------------------------------------------------|
| GET    | /topicos              | Lista todos os tópicos cadastrados            |
| GET    | /topicos/{id}         | Retorna os detalhes de um tópico específico   |
| PUT    | /topicos/{id}         | Atualiza os dados de um tópico existente      |
| DELETE | /topicos/{id}         | Remove um tópico específico                    |

> Observação: Todas as requisições acima, exceto `/usuarios` e `/login`, requerem **autenticação via token JWT**.
