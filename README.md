# DemoUserApp
API Rest demo for register a new user using JAVA Spring boot features.

## Description

The application allows user registration and is designed to demonstrate the use of technologies such as Spring Boot and Spring JPA. It includes unit tests to ensure code quality.

## Preview requirements

- JDK 17.
- Port 8086 available.
- IDE like IntelliJ IDEA, Eclipse or Spring Tool Suite.

## Instalation

1. **Clone the next repository to a local folder

 git clone https://github.com/PatricioBerrios/DemoUserApp.git

2. Import the project in your IDE

3. Run your application
  Use the Run option in your IDE for start the project, this will execute the SpringBootApplication class "DemoUserApp".

4. Test the API
   With Postman or a similar tool, do a POST petition to the next url:
    http://localhost:8086/users/register

Example request:

    {
      "name": "Juan Rodriguez",
      "email": "juaanrodrigue1z@gl.com",
      "password": "Hello24",
      "phones": [
          {
              "number": "1234567",
              "citycode": "1",
              "contrycode": "57"
          },
          {
              "number": "1234568",
              "citycode": "13",
              "contrycode": "57"
          }
      ]
  }

5. Testing

The project includes unitary tests. You can execute using the next command in your IDE or in your terminal: 

./gradlew test

6. Swagger documentation

You can check the API documentation using Swagger in the next url:

http://localhost:8086/swagger-ui/index.html#/

