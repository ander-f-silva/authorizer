## Authorizer

### Introduction

Hello, my name is Anderson and my proposal is to create a purchase authorization following the model of the document I received.
I divided this document into 4 sessions to explain what technologies are used, design and architecture of the project and how to execute it.

### Design and project architecture

For this project I used the layered model, being able to evolve it into a microservices model (Rest or Fila) or even a door model and adapters.

I organized the layers in 3 directories

- Application: responsible for handling the input and output data;
- Domain: responsible for the components with the business rules.
- Infrastructure: responsible for the components of access to the database or queues.

For integration between the layers I thought of some designs used in the Java world, such as inversion of control, factories for creating objects, strategy for cases to apply different behaviors to actions such as creating an account or authorizing a transaction.

My thought has always been to build a code that is flexible to changes and does not depend on other layers to perform the tests.

Principles like SOLID, DRY and KISS were my guides to think about creating this solution.

### Technologies

- Java language - JDK v.11;
- Maven to manage dependencies;
- Docker (use the commands to not depend on the installation on the machine).

### Dependencies

For this project use two important libraries for this solution:

- Gson: a librarian that converts a json to an object and reverse too;
- Junit 5: a librarian to build unit testing.

### How to run the tests and perform the build

Access the Application folder:

```shell script
cd authorizer
```

To run the tests, type the command below:

```shell script
 docker run --rm -v $PWD:/app -w /app maven:3.6.3-jdk-11 mvn clean test
```

To build the project:

```shell script
 docker run --rm -v $PWD:/app -w /app maven:3.6.3-jdk-11 mvn clean package
```

***Note***: Unfortunately and even for the time being, I was unable to make a way that before running the tests or performing the buil, it is not downloading the maven's dependencies.

To build the project:

```shell script
 docker run --rm -v $PWD:/app -w/app openjdk:11 java -jar ./target/authorizer-1.0-SNAPSHOT.jar ./operations
```

***Note***: For this version it is important to include the file with jsons in the project root before performing the flow, you can include several and execute the command by changing only the file name.
