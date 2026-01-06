# FHDW Messaging AMQP Contracts

## Overview

This repository contains **messaging contracts** for asynchronous communication between our Java microservices using **AMQP/RabbitMQ**.  
The contracts define standardized event structures, types, and versions that all participating projects must implement, ensuring consistency and compatibility.

The goal is to provide a **stable, reusable contract layer** that:

- Simplifies communication between different microservices
- Enforces uniform event types and metadata
- Supports schema evolution and versioning
- Allows snapshot and release distribution via GitHub Packages

---

## Repository Contents

1. **Common Contracts**
    - `EventMetadata` – Metadata for each event (e.g., event ID, type, version, timestamp, producer)
    - `EventType` – Enumerations of all possible event types (e.g., `STORE_CREATED`, `CASHIER_ASSIGNED`)
    - `EventVersion` – Versioning of event schemas (major/minor)

2. **Specific Events**
    - Events are standardized across projects, e.g., `StoreCreatedEvent`, `CashierAssignedEvent`
    - Each event always includes an `EventMetadata` object as an envelope for traceability and schema versioning

3. **Maven Build**
    - Configured as a JAR package
    - Published to **GitHub Packages Maven Repository**
    - Snapshot and release versions are versioned and distributed automatically

---

## Usage

### Including in Other Projects

Add the package as a Maven dependency:

```xml
<dependency>
    <groupId>de.fhdw</groupId>
    <artifactId>fhdw-messaging-amqp-contracts</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```