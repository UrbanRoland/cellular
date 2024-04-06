# Cellular
Cellular stores billing information in a relational database and generates a billing report via Spring Batch

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
The src/main/resources folder contains two files, billing-2023-01.csv and billing-2023-02.csv billing-2023-03.csv containing the test billing data.

### Prerequisites

- Java
- Gradle
- PostgreSQL

### Installing

1. Clone the repository
2. Navigate to the project directory
3. Run `./gradlew clean build` to build the project
4. Run billing.sql to create the database and tables
5. java -jar build/libs/biling-job-0.0.1-SNAPSHOT.jar input.file=src/main/resources/billing-2023-01.csv output.file=staging/billing-report-2023-01.csv data.year=2023 data.month=1


## Running the tests

The tests can be run by executing `./gradlew test` in the project directory.

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Gradle](https://gradle.org/)