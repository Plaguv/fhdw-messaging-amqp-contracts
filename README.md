![Apache](https://img.shields.io/badge/apache-%23D42029.svg?style=for-the-badge&logo=apache&logoColor=white)

# Messaging AMQP Contracts

This module defines the **shared contracts** for messages exchanged between applications. 

More specifically, for applications that utilize Spring Boots AMQP.

It includes:
- Message classes, enums, and records representing domain events.
- Queue names and exchange constants to ensure consistent routing.

Its main use-case is found in this custom [Spring Boot AMQP Starter](https://github.com/plaguv/messaging-amqp-starter)

# Dependencies
- `Java LTS >= 17`

# Installation
You can easily add the `io.github.plaguv:messaging-amqp-contracts` library to 
your project using the **Maven Central Repository**.

> At the time of release the libarary might **still be private** and therefore not publically available.
> In that case, check out the repository yourself and run `mvn clean install`.

```xml
<dependency>
    <groupId>io.github.plaguv</groupId>
    <artifactId>messaging-amqp-contracts</artifactId>
    <version>1.0.0</version>
</dependency>
```