# QuickBite - Event-Driven Food Delivery Platform

## Overview

QuickBite is an event-driven food delivery platform built using Java and Spring Boot following a microservices architecture.

The project started as a monolithic backend and was incrementally evolved into independent microservices that communicate asynchronously using Apache Kafka. It demonstrates modern backend engineering concepts including JWT Authentication, Redis Caching, the Outbox Pattern, Kafka-based asynchronous messaging, and SMTP email notifications.

This repository serves as the parent project containing all QuickBite microservices.

---

# Project Architecture

```text
QuickBite/
│
├── README.md
├── docker-compose.yml
│
├── quickbite-food-delivery/
│      ├── User Management
│      ├── Restaurant Management
│      ├── Food Item Management
│      ├── Order Management
│      ├── PostgreSQL
│      ├── Redis Cache
│      ├── Kafka Producer
│      └── Outbox Pattern
│
└── quickbite-notification-service/
       ├── Kafka Consumer
       ├── Gmail SMTP
       └── Email Notifications
```

---

# Microservices

| Service                        | Description                                                                               | Status      |
| ------------------------------ | ----------------------------------------------------------------------------------------- | ----------- |
| quickbite-food-delivery        | Core backend responsible for authentication, restaurants, food items and order processing | ✅ Completed |
| quickbite-notification-service | Consumes Kafka events and sends email notifications asynchronously                        | ✅ Completed |

---

# Technology Stack

## Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Security
* Spring Validation
* Spring Kafka
* Spring Mail
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

# System Architecture

```text
                 Client
                    │
                    ▼
        quickbite-food-delivery
                    │
      ┌─────────────┴─────────────┐
      ▼                           ▼
 PostgreSQL                    Redis
      │
      ▼
 Outbox Event Table
      │
      ▼
 Scheduled Publisher
      │
      ▼
      Kafka Broker
      │
      ▼
quickbite-notification-service
      │
      ▼
 Gmail SMTP
      │
      ▼
Customer Email
```

---

# Event Flow

```text
Client Places Order
        │
        ▼
Validate Request
        │
        ▼
Save Order
        │
        ▼
Save Event in Outbox Table
        │
        ▼
Transaction Commit
        │
        ▼
Scheduler Publishes Event
        │
        ▼
Kafka Topic (order-created)
        │
        ▼
Notification Service
        │
        ▼
Send Email Notification
```

---

# Features

## Authentication

* JWT Authentication
* JWT Authorization
* BCrypt Password Encryption
* Role-Based Access Control

## Restaurant Management

* CRUD Operations
* Redis Caching
* Automatic Cache Eviction

## Food Item Management

* CRUD Operations
* Redis Caching
* Automatic Cache Eviction

## Order Management

* Place Order
* Cancel Order
* Order Tracking
* Pagination
* Transaction Management

## Event-Driven Architecture

* Apache Kafka Producer
* Apache Kafka Consumer
* Outbox Pattern
* Reliable Event Publishing
* Scheduled Event Processing

## Notification Service

* Kafka Event Consumption
* JSON Event Deserialization
* Gmail SMTP Email Notifications
* Asynchronous Processing
* Independent Microservice

---

# Running the Project

## Prerequisites

* Java 17
* Maven
* PostgreSQL
* Docker Desktop

---

## Start Infrastructure

```bash
docker-compose up -d
```

This starts:

* Kafka
* Zookeeper
* Redis

---

## Run Services

Start the services in the following order:

1. quickbite-food-delivery
2. quickbite-notification-service

---

# Repository Structure

```text
QuickBite/
│
├── README.md
├── docker-compose.yml
├── quickbite-food-delivery/
└── quickbite-notification-service/
```

---

# Project Highlights

* Layered Spring Boot Architecture
* JWT Authentication & Authorization
* PostgreSQL with Spring Data JPA
* Redis Caching
* Transaction Management
* Apache Kafka Messaging
* Outbox Pattern
* Event-Driven Communication
* Gmail SMTP Email Notifications
* Dockerized Local Infrastructure

---

# Learning Outcomes

* Spring Boot
* Spring Security
* JWT Authentication
* REST API Design
* PostgreSQL
* Redis Caching
* Apache Kafka
* Event-Driven Architecture
* Outbox Pattern
* Spring Mail
* Transaction Management
* Docker
* Microservices Fundamentals

---

## Author

**Anurag Tiwari**

QuickBite demonstrates the evolution of a Spring Boot application from a monolithic backend into an event-driven microservices platform. The project showcases modern backend engineering practices including secure authentication, Redis caching, reliable event publishing using the Outbox Pattern, Apache Kafka messaging, asynchronous email notifications, and Docker-based local infrastructure.
