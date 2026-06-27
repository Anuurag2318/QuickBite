# QuickBite Food Delivery Service

## Overview

The QuickBite Food Delivery Service is the core business service of the QuickBite platform.

It manages user authentication, restaurant management, food item management, and order processing while publishing domain events to Apache Kafka using the Outbox Pattern.

This service owns the core business logic and communicates asynchronously with the Notification Service through Kafka, enabling a scalable and loosely coupled microservices architecture.

---

# Responsibilities

* User Registration & Authentication
* Restaurant Management
* Food Item Management
* Order Management
* Redis Caching
* Kafka Event Publishing
* Outbox Pattern
* Transaction Management
* Asynchronous Communication with Notification Service

---

# Tech Stack

## Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Security
* Spring Validation
* Spring Kafka
* Maven
* Lombok

## Database

* PostgreSQL

## Cache

* Redis

## Messaging

* Apache Kafka

## Infrastructure

* Docker
* Docker Compose

---

# Key Features

## User Management

* User Registration
* User Login
* JWT Authentication
* JWT Authorization
* BCrypt Password Encryption
* Role-Based User Model

---

## Restaurant Management

* Add Restaurant
* Update Restaurant
* Delete Restaurant
* Get Restaurant By ID
* Get All Restaurants
* One-To-Many Relationship with Food Items

---

## Food Item Management

* Add Food Item
* Update Food Item
* Delete Food Item
* Get Food Item By ID
* Get All Food Items
* Many-To-One Relationship with Restaurant

---

## Order Management

* Place Order
* Cancel Order
* Track Order
* Update Order Status
* Automatic Total Amount Calculation
* Historical Price Storage
* Pagination
* Transaction Management

---

## Redis Caching

* Restaurant Caching
* Food Item Caching
* Automatic Cache Eviction
* Cache Expiration (TTL)
* Cache Hit & Cache Miss Optimization

---

## Event-Driven Architecture

* Kafka Producer
* OrderPlacedEvent Publishing
* Outbox Pattern
* Reliable Event Publishing
* Scheduled Outbox Publisher
* Apache Kafka Integration
* Asynchronous Communication with Notification Service

---

# Database Schema

## Users

| Column   | Type   |
| -------- | ------ |
| id       | Long   |
| name     | String |
| email    | String |
| password | String |
| role     | Enum   |

---

## Restaurants

| Column  | Type   |
| ------- | ------ |
| id      | Long   |
| name    | String |
| address | String |

---

## Food Items

| Column        | Type   |
| ------------- | ------ |
| id            | Long   |
| name          | String |
| description   | String |
| price         | Double |
| restaurant_id | Long   |

---

## Orders

| Column       | Type          |
| ------------ | ------------- |
| id           | Long          |
| order_date   | LocalDateTime |
| total_amount | Double        |
| status       | Enum          |
| user_id      | Long          |

---

## Order Items

| Column       | Type    |
| ------------ | ------- |
| id           | Long    |
| quantity     | Integer |
| price        | Double  |
| food_item_id | Long    |
| order_id     | Long    |

---

## Outbox Events

| Column       | Type          |
| ------------ | ------------- |
| id           | Long          |
| event_type   | String        |
| payload      | TEXT          |
| status       | Enum          |
| created_at   | LocalDateTime |
| processed_at | LocalDateTime |

---

# API Endpoints

## Authentication

| Method | Endpoint       |
| ------ | -------------- |
| POST   | /auth/register |
| POST   | /auth/login    |

---

## Restaurants

| Method | Endpoint          |
| ------ | ----------------- |
| POST   | /restaurants      |
| GET    | /restaurants      |
| GET    | /restaurants/{id} |
| PUT    | /restaurants/{id} |
| DELETE | /restaurants/{id} |

---

## Food Items

| Method | Endpoint         |
| ------ | ---------------- |
| POST   | /food-items      |
| GET    | /food-items      |
| GET    | /food-items/{id} |
| PUT    | /food-items/{id} |
| DELETE | /food-items/{id} |

---

## Orders

| Method | Endpoint              |
| ------ | --------------------- |
| POST   | /orders               |
| GET    | /orders/user/{userId} |
| PUT    | /orders/{id}/status   |
| PUT    | /orders/{id}/cancel   |
| GET    | /orders/{id}/track    |

---

# Service Architecture

```text
                Client
                   │
                   ▼
           REST Controllers
                   │
                   ▼
            Service Layer
                   │
        ┌──────────┴──────────┐
        ▼                     ▼
   PostgreSQL             Redis Cache
        │
        ▼
  Outbox Event Table
        │
        ▼
Scheduled Outbox Publisher
        │
        ▼
    Kafka Producer
        │
        ▼
QuickBite Notification Service
```

---

# Order Processing Flow

```text
Client Places Order
        │
        ▼
Validate User
        │
        ▼
Validate Food Items
        │
        ▼
Create Order
        │
        ▼
Save Order Items
        │
        ▼
Calculate Total Amount
        │
        ▼
Save Order
        │
        ▼
Create OrderPlacedEvent
        │
        ▼
Store Event in Outbox Table
        │
        ▼
Return Success Response

Background Scheduler

        │
        ▼
Read Pending Events
        │
        ▼
Publish Event to Kafka
        │
        ▼
Notification Service Consumes Event
        │
        ▼
Customer Receives Email
```

---

# Outbox Pattern Flow

```text
Order Created
      │
      ▼
Save Order
      │
      ▼
Save Outbox Event
      │
      ▼
Transaction Commit
      │
      ▼
Scheduler Reads Pending Events
      │
      ▼
Publish Event to Kafka
      │
      ▼
Mark Event as PROCESSED
```

---

# Redis Flow

```text
Client Request
      │
      ▼
Check Redis Cache
      │
 ┌────┴────┐
 │         │
 ▼         ▼
Cache Hit  Cache Miss
 │         │
 ▼         ▼
Return    Query Database
Cached         │
Data           ▼
         Store in Redis
               │
               ▼
        Return Response
```

---

# Running the Service

## Prerequisites

* Java 17
* PostgreSQL
* Redis
* Apache Kafka
* Docker Desktop
* QuickBite Notification Service (Optional for complete event flow)

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

The service starts on:

```text
http://localhost:8080
```

---

# Project Structure

```text
src/main/java/com/quickbite

controller/
service/
repository/
entity/
dto/
config/
security/
filter/
exception/
redis/
kafka/
scheduler/
```

---

# Current Status

* ✅ Authentication
* ✅ Restaurant Module
* ✅ Food Item Module
* ✅ Order Module
* ✅ Redis Integration
* ✅ Apache Kafka Integration
* ✅ Outbox Pattern
* ✅ Notification Service Integration
* ✅ SMTP Email Notifications

---

# Learning Outcomes

* Spring Boot REST APIs
* Layered Architecture
* Spring Security
* JWT Authentication
* PostgreSQL
* Redis Caching
* Apache Kafka
* Event-Driven Architecture
* Outbox Pattern
* Transaction Management
* Asynchronous Microservices Communication
* Docker
* Microservices Design

---

## Author

**Anurag Tiwari**

The QuickBite Food Delivery Service is the core business service of the QuickBite platform. It demonstrates production-oriented backend development using Spring Boot, PostgreSQL, Redis, Apache Kafka, JWT Security, and the Outbox Pattern while enabling asynchronous communication with independent microservices through an event-driven architecture.
