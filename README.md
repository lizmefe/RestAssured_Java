# Java RestAssured Project

This project is a Java-based API testing framework utilizing RestAssured library.

## Overview

This repository contains a Java project for testing APIs using RestAssured. It includes sample test cases, configurations, and utilities to facilitate API testing.

## Prerequisites

- JDK (Java Development Kit) installed on your machine.
- Maven for managing project dependencies.

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

3. **Run Tests**: 
    ```bash
    mvn test
    ```

## Structure

- `src/main/test/java`: Contains test cases.
- `src/main/testdata`: Contains test data or configurations.
- `pom.xml`: Maven configuration file.
- `docker-compose-restfulbooker.yml`: Contain docker compose

## Usage

1. Add your test cases under `src/test/java`.
2. Execute tests using Maven or your preferred IDE.

## Dependencies

- RestAssured: For API testing.
- JUnit: For writing and executing tests.
- Other dependencies are managed through Maven.

## Contributing

Contributions are welcome! If you find any issues or want to enhance the project, feel free to create a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

---

Feel free to customize it further to include specific details about your project or any additional sections you may want to add.