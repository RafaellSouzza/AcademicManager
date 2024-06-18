# Academic Manager API

Este projeto é uma API para gerenciamento acadêmico, permitindo o cadastro de alunos, disciplinas e notas.

## Visão Geral

A API Academic Manager foi desenvolvida usando Spring Boot, Hibernate e banco de dados H2 para simplificar o gerenciamento acadêmico. A aplicação permite que professores cadastrem alunos e disciplinas, alocando alunos em disciplinas e atribuindo notas. Também é possível listar alunos aprovados e reprovados com base nas notas atribuídas.

## Estrutura do Projeto

### Modelos

1. **Aluno**:
    - ID (UUID)
    - Nome
    - CPF
    - Email
    - Telefone
    - Endereço
    - Notas (lista de notas associadas)

2. **Disciplina**:
    - ID (UUID)
    - Nome
    - Código
    - Notas (lista de notas associadas)

3. **Nota**:
    - ID (UUID)
    - Aluno (referência ao aluno)
    - Disciplina (referência à disciplina)
    - Valor

### Endpoints

#### Alunos

- **Listar Todos os Alunos**
    - **GET** `/alunos`

- **Criar Aluno**
    - **POST** `/alunos`
    - Request:
      ```json
      {
        "nome": "Rafael",
        "cpf": "12345678901",
        "email": "rafael@gmail.com",
        "telefone": "1234567890",
        "endereco": "Rua Exemplo, 123"
      }
      ```

#### Disciplinas

- **Listar Todas as Disciplinas**
    - **GET** `/disciplinas`

- **Criar Disciplina**
    - **POST** `/disciplinas`
    - Request:
      ```json
      {
        "nome": "História",
        "codigo": "HIS101"
      }
      ```

- **Alocar Aluno em Disciplina**
    - **POST** `/disciplinas/{disciplinaId}/alocarAluno`
    - Request:
      ```json
      {
        "alunoId": "3805f540-64cd-4c15-9f2f-17e4c9ad2f20"
      }
      ```

#### Notas

- **Atribuir Nota**
    - **POST** `/notas/atribuirNotaUUID`
    - Request:
      ```json
      {
        "alunoId": "3805f540-64cd-4c15-9f2f-17e4c9ad2f20",
        "disciplinaId": "0ba06567-e1bc-4441-b01a-f93ef0cee295",
        "valor": 8.0
      }
      ```

- **Listar Alunos Aprovados**
    - **GET** `/notas/aprovados/{disciplinaId}`

- **Listar Alunos Reprovados**
    - **GET** `/notas/reprovados/{disciplinaId}`

## Como Ativar a Aplicação

1. **Clone o repositório**:
   ```sh
   git clone https://github.com/RafaellSouzza/AcademicManager
   cd AcademicManager
