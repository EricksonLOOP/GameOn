# GameOn API

Esta é uma API desenvolvida para o projeto GameOn, uma plataforma de jogos online.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Lombok
- H2 Database (para ambiente de desenvolvimento)

## Configuração e Execução

1. Clone este repositório.
2. Certifique-se de ter o JDK e o Maven instalados em seu sistema.
3. Importe o projeto em sua IDE Java favorita.
4. Execute a classe `GameOnApplication` para iniciar o servidor localmente.

## Endpoints

A API possui os seguintes endpoints:

- `/signup`: Cadastro de novos usuários.
- `/login`: Autenticação de usuários.
- `/logoff/{id}`: Deslogar usuário.
- `/usuarios/{name}`: Pesquisar usuário pelo nome.
- `/usuarios`: Obter todos os usuários.
- `/usuarios/{id}`: Obter um usuário pelo ID.
- `/usuarios/{id}`: Atualizar um usuário.
- `/usuarios/{id}`: Deletar um usuário.
- `/helloworld`: Exemplo de endpoint para testar a API.
- `/chat`: Enviar mensagem no chat.
- `/chat`: Obter todas as mensagens do chat.
- `/chat/{id}`: Deletar mensagem do chat.
- `/publishs`: Publicar uma postagem.
- `/publishs/{id}`: Listar publicações.
- `/publishs/{id}`: Deletar uma publicação.
- `/checkAuthority/{loggedUser}/{publishUser}`: Verificar autoridade de uma publicação.

## Segurança

- A autenticação de usuários é realizada através do endpoint `/login`, que recebe as credenciais do usuário e retorna um token de acesso JWT.
- O token JWT deve ser incluído no cabeçalho das solicitações subsequentes para endpoints protegidos. O token é verificado pelo Spring Security para garantir que o usuário esteja autenticado antes de conceder acesso.
- A senha dos usuários é armazenada no banco de dados após ser criptografada usando BCryptPasswordEncoder.
- O Spring Security é configurado para permitir acesso público a alguns endpoints, como `/signup`, `/login`, `/logoff` e `/helloworld`, enquanto outros endpoints exigem autenticação.
- A autenticação é baseada em roles, onde cada usuário autenticado recebe automaticamente a role "USER".

## Chat

- O endpoint `/chat` permite que os usuários enviem mensagens no chat da plataforma.
- As mensagens do chat podem ser visualizadas através do endpoint `/chat`.
- É possível excluir uma mensagem do chat usando o endpoint `/chat/{id}`.

## Publicações

- O endpoint `/publishs` permite que os usuários publiquem conteúdo na plataforma.
- As publicações podem ser listadas usando o endpoint `/publishs`.
- As publicações podem ser deletadas usando o endpoint `/publishs/{id}`.

## Contribuindo

Sinta-se à vontade para contribuir com este projeto! Basta fazer um fork do repositório, fazer as alterações desejadas e enviar uma pull request.

## Licença

Este projeto está licenciado sob a [Licença MIT](https://opensource.org/licenses/MIT).
