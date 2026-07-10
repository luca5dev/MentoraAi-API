
# Motor de Validação e Formação de Trilhas de Mentoria

API REST desenvolvida em **Java 21** com **Spring Boot 3** utilizando **Arquitetura Hexagonal (Ports and Adapters)**.

O projeto é resultado da refatoração de uma aplicação originalmente desenvolvida em console, preservando todas as regras de negócio da versão anterior e reorganizando a solução para separar completamente o domínio das tecnologias de infraestrutura.

---

# Objetivo

O sistema permite a criação de **Trilhas de Mentoria**, realizando todas as validações de negócio antes da persistência dos dados.

As principais regras implementadas são:

- Validação de compatibilidade técnica entre mentor e mentorados;
- Controle da carga horária máxima suportada pelo mentor;
- Validação de hierarquia entre mentor e mentorados;
- Persistência apenas quando todas as regras forem atendidas.

---

# Evolução do Projeto

## Primeira versão

A aplicação foi inicialmente desenvolvida como uma aplicação **Console (Java + Scanner)**.

Características:

- Entrada de dados pelo terminal;
- Persistência utilizando JPA/Hibernate;
- Regras de negócio utilizando Stream API;
- Exceções customizadas;
- Demonstração automática através da classe Main.

---

## Segunda versão

Posteriormente foi realizada uma refatoração completa para uma **API REST** utilizando **Arquitetura Hexagonal**.

Principais mudanças:

- Remoção completa do Scanner;
- Exposição dos serviços através de endpoints REST;
- Separação entre Domínio e Infraestrutura;
- Uso de DTOs;
- Implementação de Ports and Adapters;
- Tratamento global de exceções;
- Maior facilidade para testes unitários.

---

# Arquitetura

O projeto segue o padrão **Hexagonal Architecture (Ports and Adapters)**.

```text
                HTTP

          REST Controller
                │
                ▼
         Inbound Port
                │
                ▼
          Use Cases
                │
                ▼
            Domínio
                │
                ▼
        Outbound Port
                │
                ▼
     Adapter JPA / Banco
```

A principal característica dessa arquitetura é que o **Domínio não conhece nenhuma tecnologia externa**, como Spring Boot, JPA ou banco de dados.

---

## Estrutura Real do Projeto

```text
src
├── main
│   ├── java
│   │   └── app
│   │       ├── MentoraAiApplication.java
│   │       │
│   │       ├── adapters
│   │       │   ├── in
│   │       │   │   └── web
│   │       │   │       ├── ApiExceptionHandler.java
│   │       │   │       ├── DemonstracaoController.java
│   │       │   │       ├── ParticipanteController.java
│   │       │   │       ├── TrilhaController.java
│   │       │   │       ├── dto
│   │       │   │       │   ├── CadastrarMentorRequest.java
│   │       │   │       │   ├── CadastrarMentoradoRequest.java
│   │       │   │       │   ├── CriarTrilhaRequest.java
│   │       │   │       │   ├── DemonstracaoResponse.java
│   │       │   │       │   ├── ErroResponse.java
│   │       │   │       │   ├── MentorResponse.java
│   │       │   │       │   ├── MentoradoResponse.java
│   │       │   │       │   ├── TrilhaResponse.java
│   │       │   │       │   └── WebEnumMapper.java
│   │       │   │       └── mapper
│   │       │   │           └── WebResponseMapper.java
│   │       │   │
│   │       │   └── out
│   │       │       └── persistence
│   │       │           ├── entity
│   │       │           │   ├── MentorJpaEntity.java
│   │       │           │   ├── MentoradoJpaEntity.java
│   │       │           │   ├── ParticipanteJpaEntity.java
│   │       │           │   └── TrilhaJpaEntity.java
│   │       │           └── repository
│   │       │               ├── ParticipanteJpaMapper.java
│   │       │               ├── ParticipanteJpaRepository.java
│   │       │               ├── ParticipantePersistenceAdapter.java
│   │       │               ├── TrilhaJpaMapper.java
│   │       │               ├── TrilhaJpaRepository.java
│   │       │               └── TrilhaPersistenceAdapter.java
│   │       │
│   │       ├── config
│   │       │   └── UseCaseConfig.java
│   │       │
│   │       └── domain
│   │           ├── exception
│   │           │   ├── CampoVazioException.java
│   │           │   ├── CargaHorariaExcedidaException.java
│   │           │   ├── EntradaInvalidaException.java
│   │           │   ├── LimiteSkillsUltrapassadoException.java
│   │           │   ├── ListaVaziaException.java
│   │           │   ├── MaximoMentoradosAtingidosException.java
│   │           │   ├── NivelDesproporcionalException.java
│   │           │   ├── NumeroForaDoIntervaloException.java
│   │           │   ├── ParticipanteNaoEncontradoException.java
│   │           │   ├── SkillDuplicadaException.java
│   │           │   ├── SkillIncompativelException.java
│   │           │   └── ValorDaHoraInvalidoException.java
│   │           │
│   │           ├── model
│   │           │   ├── Mentor.java
│   │           │   ├── Mentorado.java
│   │           │   ├── NivelSenioridade.java
│   │           │   ├── ParticipantePrograma.java
│   │           │   ├── Skill.java
│   │           │   └── TrilhaMentoria.java
│   │           │
│   │           ├── port
│   │           │   ├── in
│   │           │   │   ├── CadastrarMentorDados.java
│   │           │   │   ├── CadastrarMentorPort.java
│   │           │   │   ├── CadastrarMentoradoDados.java
│   │           │   │   ├── CadastrarMentoradoPort.java
│   │           │   │   ├── CriarTrilhaDados.java
│   │           │   │   ├── CriarTrilhaPort.java
│   │           │   │   ├── ExecutarDemonstracaoPort.java
│   │           │   │   ├── ListarParticipantesPort.java
│   │           │   │   └── ListarTrilhasPort.java
│   │           │   │
│   │           │   └── out
│   │           │       ├── ParticipanteRepositoryPort.java
│   │           │       └── TrilhaRepositoryPort.java
│   │           │
│   │           ├── usecase
│   │           │   ├── CadastrarMentorUseCase.java
│   │           │   ├── CadastrarMentoradoUseCase.java
│   │           │   ├── CriarTrilhaUseCase.java
│   │           │   ├── ExecutarDemonstracaoAutomaticaUseCase.java
│   │           │   ├── ListarParticipantesUseCase.java
│   │           │   └── ListarTrilhasUseCase.java
│   │           │
│   │           └── validator
│   │               └── ValidadorTrilhaDomain.java
│   │
│   └── resources
│       ├── META-INF
│       │   └── persistence.xml
│       ├── application.properties
│       ├── application-local.properties
│       └── static
│           └── index.html
│
└── test
    └── java
        └── app
            └── domain
                ├── usecase
                │   └── CriarTrilhaUseCaseTest.java
                └── validator
                    └── ValidadorTrilhaDomainTest.java
```
---

# Regras de Negócio

## 1. Compatibilidade de Skills

O mentor deve possuir pelo menos **70% das habilidades** desejadas pelo mentorado.

Caso contrário, é lançada:

```text
SkillIncompativelException
```

## 2. Limite de Carga Horária

A soma das horas previstas para todos os mentorados da trilha não pode ultrapassar a capacidade máxima do mentor.

Caso ultrapasse:

```text
CargaHorariaExcedidaException
```

## 3. Hierarquia Técnica

Um mentor deve possuir senioridade superior aos mentorados.

Exemplo inválido:

```text
PLENO
   ↓
PLENO
```

Nesse caso é lançada:

```text
NivelDesproporcionalException
```

---

# Tecnologias Utilizadas

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Hibernate
- Maven
- H2 Database (desenvolvimento)
- JUnit 5
- Stream API
- Arquitetura Hexagonal

---

# Endpoints

## Criar Trilha

```http
POST /api/trilhas
```

### Exemplo de requisição

```json
{
  "mentorId": 1,
  "mentoradosIds": [2, 3, 4]
}
```

### Resposta de sucesso

```http
201 Created
```

## Buscar Trilhas

```http
GET /api/trilhas
```

Retorna todas as trilhas cadastradas.

## Buscar Trilha por ID

```http
GET /api/trilhas/{id}
```

---

# Tratamento de Exceções

As regras de negócio permanecem no Domínio.

Quando alguma regra é violada, uma exceção é lançada pelo Core e interceptada por um **ControllerAdvice**, retornando:

```http
HTTP 422
```

Exemplo:

```json
{
  "status": 422,
  "erro": "Skill incompatível entre mentor e mentorado."
}
```

---

# Fluxo da Aplicação

```text
Cliente
   │
POST /api/trilhas
   │
Controller REST
   │
Inbound Port
   │
Use Case
   │
Domínio (Validações)
   │
Outbound Port
   │
Adapter JPA
   │
Banco de Dados
```

---

# Princípios da Arquitetura

- O domínio não depende do Spring Boot.
- O domínio não possui anotações JPA.
- Toda comunicação ocorre através de Ports.
- Adaptadores implementam apenas detalhes de infraestrutura.
- O Controller apenas recebe e devolve dados.
- O Core concentra todas as regras de negócio.

---

# Como Executar

## Pré-requisitos

- Java 21
- Maven 3.9+

## Clonar o projeto

```bash
git clone <url-do-repositorio>
```

## Executar

```bash
mvn spring-boot:run
```

Ou

```bash
mvn clean install
java -jar target/*.jar
```

---

# Testes

```bash
mvn test
```

---

# Objetivos da Refatoração

- Eliminar o acoplamento entre regras de negócio e interface de usuário;
- Isolar completamente o domínio da infraestrutura;
- Facilitar testes unitários e de integração;
- Tornar a aplicação mais escalável e de fácil manutenção;
- Permitir a evolução para novos adaptadores (REST, mensageria, CLI, entre outros) sem alterar o Core da aplicação.

---

# Autor

Projeto desenvolvido como atividade acadêmica para demonstrar a aplicação dos conceitos de:

- Arquitetura Hexagonal (Ports and Adapters)
- Domain-Driven Design (DDD)
- SOLID
- Clean Architecture
- Spring Boot
- Spring Data JPA
- Stream API
- Tratamento de Exceções
- APIs REST
- Boas práticas de separação de responsabilidades
