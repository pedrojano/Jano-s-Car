# Jano's Cars - Back-end 

Este é o repositório do **back-end da plataforma Jano's Cars**. Nossa aplicação tem como objetivo facilitar o gerenciamento de **manutenções e abastecimentos de veículos**, permitindo que usuários registrem seus carros e motos, mantendo um **histórico completo de serviços e reabastecimentos**, com data e quilometragem.

O back-end foi construído em **Java 21 com Spring Boot**, e será consumido por um front-end em **React**.

---

##  Objetivo do Projeto

Desenvolver uma plataforma completa para o gerenciamento pessoal de veículos, oferecendo:

- **Registro e Gestão de Usuários**: Base para personalização da experiência.
- **Cadastro e Associação de Veículos**: Cada usuário pode gerenciar sua frota.
- **Histórico de Manutenções**: Registro de serviços e reparos.
- **Histórico de Abastecimentos**: Monitoramento de consumo e custos de combustível.

---

##  Funcionalidades Atuais do Back-end

### 1. Módulo de Usuário (`/api/users`)

**CRUD Completo**:
- **Criação**: Registro de usuários com nome de usuário, senha e e-mail.
- **Leitura**: Listagem de todos os usuários ou busca por ID.
- **Atualização**: Modificação de dados do usuário.
- **Deleção**: Remoção de usuários.

**Validações**:
- Verificação de unicidade de `username` e `email`.

---

### 2. Módulo de Veículo

- **Entidades**: `User` e `Vehicle`, com relacionamento `@OneToMany` (um usuário possui muitos veículos).
- **Associação**: Veículos são associados a usuários no momento da criação.
- **Repositório**: `VehicleRepository` com método de busca por `userId`.

 *OBS: Os endpoints REST para veículos ainda estão em desenvolvimento.*

---

##  Tecnologias Utilizadas

- **Linguagem**: Java 21  
- **Framework**: Spring Boot 3.x  
- **Banco de Dados**: PostgreSQL  
- **ORM**: Spring Data JPA com Hibernate  
- **Build**: Apache Maven  
- **Segurança**: Spring Security (`permitAll()` temporário)  
- **Produtividade**: Project Lombok  

---

##  Primeiros Passos: Configuração e Execução

###  Pré-requisitos

- Java 21 (JDK) → [Download](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)  
- Maven → [Download](https://maven.apache.org/download.cgi)  
- PostgreSQL → [Download](https://www.postgresql.org/download/)  
- IDE (ex: IntelliJ, Eclipse, VS Code)

---

### Configuração do Banco de Dados

1. Crie um banco no PostgreSQL, por exemplo: `janoscars_db`

2. Configure o arquivo `application.properties` em `src/main/resources/`:

```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/janoscars_db
    spring.datasource.username=SEU_USUARIO
    spring.datasource.password=SUA_SENHA

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
```
---

### Executando o Projeto

1. Clone o repositório:

       bash
          git clone <URL_DO_SEU_REPOSITORIO>
          cd janoscars/back-end/janoscars


2. Inicie a aplicação:

       bash
           mvn clean spring-boot:run


3. Quando aparecer Started JanoscarsApplication..., a API estará no ar em:
http://localhost:8080

---

### Como Testar a API:

Utilize Postman, Insomnia ou outro cliente HTTP.

URL Base: http://localhost:8080/api

### Endpoints de Usuário:

POST /api/users
Cria um novo usuário.

     Json
     
      {
        "username": "exemplo_usuario",
        "password": "umaSenhaForte",
        "email": "email.exemplo@janoscars.com"
      }

### Respostas:

201 Created, 400 Bad Request (duplicado)

---

GET /api/users
Lista todos os usuários.
Resposta: 200 OK

---

GET /api/users/{id}
Busca usuário por ID.
Exemplo: /api/users/1
Resposta: 200 OK, 404 Not Found

---

PUT /api/users/{id}
Atualiza um usuário existente.

      json
      
      {
        "username": "usuario_atualizado_exemplo",
        "password": "senhaExistente",
        "email": "novo.email@janoscars.com"
      }
Resposta: 200 OK, 404 Not Found, 400 Bad Request

---

DELETE /api/users/{id}
Deleta um usuário.
Exemplo: /api/users/1
Resposta: 204 No Content, 404 Not Found

---

 ### Próximos Passos
 
 Construção do front-end em React

 Implementar CRUD completo de veículos

 Adicionar módulos de manutenção e abastecimento

 Segurança com JWT (Spring Security)

 Testes unitários e de integração

 Documentação com Swagger/OpenAPI
