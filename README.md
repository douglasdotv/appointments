# Spring Boot REST API

[![en](https://img.shields.io/badge/lang-en-red.svg)](https://github.com/douglasdotv/spring-boot-api-rest/blob/main/README.md)
[![pt-br](https://img.shields.io/badge/lang-pt--br-green.svg)](https://github.com/douglasdotv/spring-boot-api-rest/blob/main/README.pt-br.md)

This is a RESTful API built with Spring Boot 3 for managing doctors' information.

### Endpoints

(POST) /doctors: this endpoint allows doctor registration by sending a request containing the doctor's information in the request body.

(GET) /doctors: returns a paginated list of all active doctors sorted by name.

(GET) /doctors/{id}: returns a doctor's information by their ID.

(PUT) /doctors: updates an existing doctor's information by sending a request containing the updated doctor's information in the request body.

(DELETE) /doctors/{id}: this endpoint enables one to soft-delete a doctor by their ID.

(POST) /auth: this endpoint authenticates a user by their login and password. In case of successful authentication, a JSON Web Token is returned on the response body.

(**_Note_**: It is necessary to send a valid JWT in the Authorization header of every request to the API, except for the /auth endpoint.)

### Example Requests

#### POST: /auth

Request:

```json
{
  "login": "admin@admin.com",
  "password": "123456"
}
```

Response:

```
token
```

#### GET: /doctors

Headers:

```
Authorization: Bearer [AUTH_TOKEN]
```

Response:

```json
{
  "content": [
    {
      "id": 1,
      "name": "John Doe",
      "email": "johndoe@email.com",
      "crm": "111111",
      "specialty": "CARDIOLOGY"
    },
    {
      "id": 2,
      "name": "Jane Doe",
      "email": "janedoe@email.com",
      "crm": "222222",
      "specialty": "ORTHOPEDICS"
    }
  ],
  "pageable": {
    "sort": {
      "empty": false,
      "unsorted": false,
      "sorted": true
    },
    "offset": 0,
    "pageNumber": 0,
    "pageSize": 2,
    "unpaged": false,
    "paged": true
  },
  "last": true,
  "totalPages": 1,
  "totalElements": 2,
  "size": 2,
  "number": 0,
  "sort": {
    "empty": false,
    "unsorted": false,
    "sorted": true
  },
  "numberOfElements": 2,
  "first": true,
  "empty": false
}
```

### Error Handling

The application implements an error handler to return proper HTTP status codes and error messages in response to exceptions through the ErrorHandler class.

EntityNotFoundException is handled by returning a 404 Not Found HTTP status code.

MethodArgumentNotValidException is handled by returning a 400 Bad Request HTTP status code along with a list of validation errors in the response body.
This error handling mechanism is implemented in the ErrorHandler class which acts as a central location for exception handling in the application.

### Spring Security

The application uses Spring Security to implement authentication and authorization. The authentication mechanism is implemented in SecurityConfiguration and SecurityFilter class.

In order to get access to the resources, you will need to obtain a JWT token by making a POST request to the /auth endpoint with a valid username and password. The token will then need to be included in the header of subsequent requests to secured endpoints.

The authentication process is managed by AuthenticationManager Spring Security interface and the passwords are hashed with BCrypt algorithm.

### JWT

The application uses a JWT token service to implement authentication. The JWT token is generated by the JsonWebTokenService class which is responsible for creating and validating tokens. Also, it's important to note that the token is configured to expire after 2 hours.

### Note

This API is just a simple implementation and might not be suitable for production use. It will be updated as I learn more about Spring Boot. (I still need to implement testing, for example.)
