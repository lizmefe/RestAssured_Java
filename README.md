# Java RestAssured Project

This project is a Java-based API testing framework utilizing the RestAssured library.

## Overview

This repository contains a Java project for testing APIs using RestAssured. It includes sample test cases, configurations, and utilities to facilitate API testing.

## Prerequisites

- JDK 17 (Java Development Kit) installed on your machine.
- Maven for managing project dependencies.
- Docker installed on your machine.

## Setup

1. **Clone the Repository**: 
    ```bash
    git clone <repository_url>
    ```

2. **Build the Project**: 
    ```bash
    cd Java_RestAssured_Project
    mvn clean install
    ```

3. **Run Docker Compose**:
    ```bash
    docker-compose -f docker-compose-restfulbooker.yml up -d
    ```

4. **Access API Documentation**:
    ```bash
    Once Docker Compose is running, the API documentation is accessible at:
    [http://localhost:3001/apidoc/index.html](http://localhost:3001/apidoc/index.html)
    ```

5. **Run Tests**: 
    ```bash
    mvn test
    ```

## Structure

- `src/main/test/java`: Contains test cases.
- `src/main/testdata`: Contains test data or configurations.
- `src/test/java/interfaces`: Contains IServiceEndpoints file with endpoint definitions.
- `pom.xml`: Maven configuration file.
- `docker-compose-restfulbooker.yml`: Contains docker compose configuration.
- `src/test/java/test`: Contains test cases.
- `src/test/java/testdata`: Contains test data.

## Usage

1. Add your test cases under `src/test/java/test`.
2. Execute tests using Maven or your preferred IDE.

## Dependencies

- RestAssured: For API testing.
- JUnit: For writing and executing tests.
- Lombok: For reducing boilerplate code.
- Other dependencies are managed through Maven.

## Contributing

Contributions are welcome! If you find any issues or want to enhance the project, feel free to create a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

---

Feel free to customize it further to include specific details about your project or any additional sections you may want to add.