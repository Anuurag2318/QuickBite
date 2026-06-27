# QuickBite Notification Service

## Overview

The QuickBite Notification Service is an independent microservice responsible for consuming order events from Apache Kafka and sending notifications to users.

Currently, the service consumes `OrderPlacedEvent` messages published by the Food Delivery Service and logs order confirmations. In future iterations, it will be extended to send real email notifications using SMTP.

The Notification Service is completely decoupled from the Food Delivery Service and communicates asynchronously through Kafka.

---

# Responsibilities

* Consume Kafka Events
* Process OrderPlacedEvent
* Send Order Notifications
* Handle Failed Message Processing
* Support Future Email Integration
* Remain Independent from Business Logic

---

# Tech Stack

## Backend

* Java 17
* Spring Boot
* Spring Kafka
* Maven
* Lombok

## Messaging

* Apache Kafka

## Infrastructure

* Docker
* Docker Compose

---

# Current Features

## Kafka Consumer

* Kafka Listener
* Consumer Group Support
* JSON Event Deserialization
* Independent Event Processing
* Automatic Event Consumption

---

## Notification Processing

* Receive OrderPlacedEvent
* Extract Order Information
* Process Notification Request
* Log Order Confirmation

---

# Architecture

```text
                Kafka Broker
                      │
                      ▼
         quickbite-notification-service
                      │
                      ▼
              Kafka Listener
                      │
                      ▼
           Deserialize JSON Event
                      │
                      ▼
         Notification Processing
                      │
                      ▼
          Console Notification
         (Email Integration Upcoming)
```

---

# Event Flow

```text
Food Delivery Service
          │
          ▼
 Publish OrderPlacedEvent
          │
          ▼
      Kafka Topic
      order-created
          │
          ▼
Notification Service
          │
          ▼
Kafka Listener
          │
          ▼
Deserialize Event
          │
          ▼
Process Notification
          │
          ▼
Order Confirmation
```

---

# Consumed Events

## OrderPlacedEvent

This event is published whenever a customer successfully places an order.

### Event Structure

```json
{
  "orderId": 10,
  "userId": 1,
  "totalAmount": 850.0,
  "status": "PLACED"
}
```

### Fields

| Field       | Type   | Description             |
| ----------- | ------ | ----------------------- |
| orderId     | Long   | Unique Order Identifier |
| userId      | Long   | Customer Identifier     |
| totalAmount | Double | Total Order Amount      |
| status      | String | Current Order Status    |

---

# Kafka Configuration

## Topic

```text
order-created
```

## Consumer Group

```text
notification-group
```

## Bootstrap Server

```text
localhost:9092
```

---

# Current Processing Flow

```text
Receive Kafka Message
        │
        ▼
Deserialize JSON
        │
        ▼
Create OrderPlacedEvent Object
        │
        ▼
Read Event Details
        │
        ▼
Log Notification
```

---

# Future Email Workflow

```text
Receive Kafka Event
        │
        ▼
Extract User Information
        │
        ▼
Generate Email Template
        │
        ▼
Connect SMTP Server
        │
        ▼
Send Email
        │
        ▼
Log Success / Failure
```

---

# Running the Service

## Prerequisites

* Java 17
* Apache Kafka
* Docker Desktop

---

## Start Infrastructure

```bash
docker-compose up -d
```

---

## Run Application

```bash
mvn spring-boot:run
```

The Notification Service starts on:

```text
http://localhost:8081
```

---

# Project Structure

```text
src/main/java/com/quickbite/notification

config/
consumer/
event/
```

---

# Current Status

## Kafka Consumer

* ✅ Completed

## OrderPlacedEvent Consumption

* ✅ Completed

## JSON Event Deserialization

* ✅ Completed

## Independent Microservice

* ✅ Completed

## Email Notification (SMTP)

* 🚧 In Progress

## HTML Email Templates

* 🚧 Planned

## Retry Mechanism

* 🚧 Planned

## Dead Letter Queue (DLQ)

* 🚧 Planned

---

# Future Enhancements

* SMTP Email Integration
* HTML Email Templates
* Email Retry Mechanism
* Dead Letter Queue (DLQ)
* SMS Notifications
* Push Notifications
* Notification Preferences
* Notification History

---

# Learning Outcomes

* Apache Kafka Consumer
* Event-Driven Communication
* Asynchronous Processing
* Microservices Architecture
* JSON Deserialization
* Spring Kafka
* Consumer Groups
* Loose Coupling Between Services

---

## Author

**Anurag Tiwari**

This service demonstrates asynchronous communication in a microservices architecture by consuming Kafka events independently from the core Food Delivery Service. It forms the foundation for scalable notification processing and future email delivery capabilities.
