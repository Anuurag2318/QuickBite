# QuickBite Notification Service

## Overview

The QuickBite Notification Service is an independent microservice responsible for consuming order events from Apache Kafka and sending order confirmation emails to customers.

The service listens for `OrderPlacedEvent` messages published by the Food Delivery Service, processes the event asynchronously, and sends an email notification using Gmail SMTP. Communication between services is completely decoupled through Apache Kafka, making the system scalable and resilient.

---

# Responsibilities

* Consume Kafka Events
* Process `OrderPlacedEvent`
* Deserialize JSON Messages
* Send Order Confirmation Emails
* Asynchronous Event Processing
* Remain Independent from Business Logic

---

# Tech Stack

## Backend

* Java 17
* Spring Boot
* Spring Kafka
* Spring Mail
* Maven
* Lombok

## Messaging

* Apache Kafka

## Email

* Gmail SMTP
* JavaMailSender

## Infrastructure

* Docker
* Docker Compose

---

# Features

## Kafka Consumer

* Kafka Listener
* Consumer Group Support
* JSON Event Deserialization
* Automatic Event Consumption
* Asynchronous Processing

---

## Email Notification

* Consume `OrderPlacedEvent`
* Extract Customer Information
* Generate Order Confirmation Email
* Send Email using Gmail SMTP
* Log Notification Processing

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
              Email Service
                      │
                      ▼
             Gmail SMTP Server
                      │
                      ▼
           Customer Receives Email
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
Email Service
          │
          ▼
JavaMailSender
          │
          ▼
Customer Email Inbox
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
  "customerName": "Anurag Tiwari",
  "customerEmail": "anurag@example.com",
  "totalAmount": 850.0,
  "status": "PLACED"
}
```

### Fields

| Field         | Type   | Description             |
| ------------- | ------ | ----------------------- |
| orderId       | Long   | Unique Order Identifier |
| userId        | Long   | Customer Identifier     |
| customerName  | String | Customer Name           |
| customerEmail | String | Customer Email Address  |
| totalAmount   | Double | Total Order Amount      |
| status        | String | Current Order Status    |

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

# Processing Flow

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
Extract Customer Details
        │
        ▼
Generate Email
        │
        ▼
Send Email using Gmail SMTP
        │
        ▼
Log Success
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
service/
```

---

# Current Status

* ✅ Kafka Consumer
* ✅ JSON Event Deserialization
* ✅ OrderPlacedEvent Consumption
* ✅ Gmail SMTP Integration
* ✅ Email Notification Service
* ✅ Independent Microservice
* ✅ Asynchronous Communication

---

# Future Enhancements

* HTML Email Templates
* Retry Mechanism for Failed Emails
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
* Spring Kafka
* Spring Mail
* Gmail SMTP Integration
* JSON Serialization & Deserialization
* Consumer Groups
* Microservices Architecture
* Loose Coupling Between Services

---

## Author

**Anurag Tiwari**

The Notification Service demonstrates asynchronous communication in a microservices architecture by consuming Kafka events independently from the Food Delivery Service. It processes business events, sends real email notifications using Gmail SMTP, and showcases event-driven design principles for scalable backend systems.
