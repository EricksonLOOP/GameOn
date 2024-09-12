# GameOn API

This is an API developed for the GameOn project, an online gaming platform.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- Lombok
- H2 Database (for development environment)

## Configuração e Execução

1. Clone this repository.
2. Make sure you have the JDK and Maven installed on your system.
3. Import the project into your favorite Java IDE.
4. Run the `GameOnApplication` class to start the server locally.

## Endpoints

The API has the following endpoints:

- `/signup`: Register new users.
- `/login`: Authenticate users.
- `/logoff/{id}`: Log out users.
- `/users/{name}`: Search for users by name.
- `/users`: Get all users.
- `/users/{id}`: Get a user by ID.
- `/users/{id}`: Update a user.
- `/users/{id}`: Delete a user.
- `/helloworld`: Example endpoint to test the API.
- `/chat`: Send a message in the chat.
- `/chat`: Get all chat messages.
- `/chat/{id}`: Delete a chat message.
- `/publishes`: Publish a post.
- `/publishes/{id}`: List publications.
- `/publishes/{id}`: Delete a publication.
- `/checkAuthority/{loggedUser}/{publishUser}`: Check the authority of a publication.

## Security

- User authentication is performed through the `/login` endpoint, which receives the user's credentials and returns a JWT access token.
- The JWT token must be included in the header of subsequent requests to protected endpoints. The token is verified by Spring Security to ensure that the user is authenticated before granting access.
- The user's password is stored in the database after being encrypted using BCryptPasswordEncoder.
- Spring Security is configured to allow public access to some endpoints, such as `/signup`, `/login`, `/logoff`, and `/helloworld`, while other endpoints require authentication.
- Authentication is role-based, where each authenticated user is automatically assigned the "USER" role.
- 
## Chat

- The `/chat` endpoint allows users to send messages in the platform's chat.
- Chat messages can be viewed through the `/chat` endpoint.
- It is possible to delete a chat message using the `/chat/{id}` endpoint.

## Publications

- The `/publishes` endpoint allows users to publish content to the platform.
- Publications can be listed using the `/publishes` endpoint.
- Publications can be deleted using the `/publishes/{id}` endpoint.

## Contributing

Feel free to contribute to this project! Just fork the repository, make your desired changes, and submit a pull request.

## License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).
