# DemoUserApp English instructions
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

Request example:

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

---

# DemoUserApp Instrucciones en español
API Rest de ejemplo para registrar un nuevo usuario utilizando características de JAVA Spring Boot.

## Descripción

La aplicación permite el registro de un usuario y está diseñada para demostrar el uso de tecnologías como Spring Boot y Spring JPA. Incluye pruebas unitarias para asegurar la calidad del código.

## Requerimientos previos

- JDK 17.
- Puerto 8086 disponible.
- Un IDE como IntelliJ IDEA, Eclipse o Spring Tool Suite.

## Instalación

1. **Clona el siguiente repositorio hacia un directorio local: 

 git clone https://github.com/PatricioBerrios/DemoUserApp.git

2. Importa el proyecto en tu IDE.

3. Corre la aplicación:
  Usa la opción "Run" en tu IDE para correr el proyecto, esto ejecutará la clase "SpringBootApplication" llamada "DemoUserAppUse".

4. Probar la API
   Con Postman o una herramienta similar, haz una petición de tipo POST a la siguiente URL:
    http://localhost:8086/users/register

Ejemplo de request:

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

El proyecto incluye pruebas unitarias. Puedes ejecutarlas usando el siguiente comando en tu IDE o terminal:

./gradlew test

6. Documentación Swagger

Puedes revisar la documentación Swagger de la API en la siguiente URL:

http://localhost:8086/swagger-ui/index.html#/
