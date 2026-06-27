# QuickBite - Event-Driven Food Delivery Platform

## Overview

QuickBite is an event-driven food delivery platform built using Java and Spring Boot following a microservices architecture.

The project began as a monolithic backend and is being incrementally transformed into independent microservices that communicate asynchronously using Apache Kafka. The architecture focuses on scalability, reliability, loose coupling, and production-ready design patterns such as Redis Caching and the Outbox Pattern.

This repository serves as the parent project containing all QuickBite microservices.

---

# Project Architecture

```
QuickBite/
│
├── docker-compose.yml
│
├── quickbite-food-delivery/
│      ├── Order & Restaurant Service
│      ├── REST APIs
│      ├── PostgreSQL
│      ├── Redis Cache
│      ├── Kafka Producer
│      └── Outbox Pattern
│
├── quickbite-notification-service/
│      ├── Kafka Consumer
│      └── Email Notifications (Upcoming)
│
├── inventory-service/          (Upcoming)
├── payment-service/            (Upcoming)
├── api-gateway/                (Upcoming)
├── config-server/              (Upcoming)
└── service-registry/           (Upcoming)
```

---

# Microservices

| Service                        | Description                                                            | Status                       |
| ------------------------------ | ---------------------------------------------------------------------- | ---------------------------- |
| quickbite-food-delivery        | Core backend responsible for Users, Restaurants, Food Items and Orders | ✅ Completed                  |
| quickbite-notification-service | Consumes Kafka events and sends notifications                          | ✅ Kafka Integration Complete |
| inventory-service              | Inventory Management                                                   | 🚧 Upcoming                  |
| payment-service                | Payment Processing                                                     | 🚧 Upcoming                  |
| api-gateway                    | Single Entry Point                                                     | 🚧 Upcoming                  |
| config-server                  | Centralized Configuration                                              | 🚧 Upcoming                  |
| service-registry               | Service Discovery                                                      | 🚧 Upcoming                  |

---

# Technology Stack

## Backend

* Java 17
* Spring Boot
* Spring Data JPA
* Spring Security
* Spring Validation
* Maven
* Lombok

## Database

* PostgreSQL

## Caching

* Redis

## Messaging

* Apache Kafka

## Infrastructure

* Docker
* Docker Compose

---

# Current Architecture

```
                 Client
                    │
                    ▼
          quickbite-food-delivery
                    │
      ┌─────────────┴─────────────┐
      ▼                           ▼
   PostgreSQL                  Redis
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
Email Notification (Upcoming)
```

---

# Event-Driven Flow

```
Client Places Order
        │
        ▼
Save Order in Database
        │
        ▼
Save Event in Outbox Table
        │
        ▼
Database Transaction Commits
        │
        ▼
Scheduler Reads Pending Events
        │
        ▼
Publish Event to Kafka
        │
        ▼
Notification Service Consumes Event
        │
        ▼
Send Email (Upcoming)
```

---

# Implemented Features

## Authentication

* JWT Authentication
* JWT Authorization
* BCrypt Password Encryption
* Role-Based Access Control

## Restaurant Management

* CRUD Operations
* Redis Caching
* Cache Eviction

## Food Item Management

* CRUD Operations
* Redis Caching
* Cache Eviction

## Order Management

* Place Order
* Cancel Order
* Track Order
* Pagination
* Transaction Management

## Event-Driven Architecture

* Kafka Producer
* Kafka Consumer
* Outbox Pattern
* Reliable Event Publishing
* Scheduled Event Processing

---

# Running the Project

## Prerequisites

* Java 17
* Maven
* Docker Desktop
* PostgreSQL

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

```
QuickBite/
│
├── README.md
├── docker-compose.yml
│
├── quickbite-food-delivery/
│
├── quickbite-notification-service/
│
├── inventory-service/        (Upcoming)
├── payment-service/          (Upcoming)
├── api-gateway/              (Upcoming)
├── config-server/            (Upcoming)
└── service-registry/         (Upcoming)
```

---

# Roadmap

## Phase 1

* Spring Boot Backend
* JWT Authentication
* Restaurant APIs
* Food Item APIs
* Order APIs

Status: ✅ Completed

---

## Phase 2

* Redis Integration
* Cache Eviction
* TTL
* Cache Optimization

Status: ✅ Completed

---

## Phase 3

* Apache Kafka
* Event-Driven Architecture
* Notification Service
* Outbox Pattern

Status: ✅ Completed

---

## Phase 4

* SMTP Email Notifications

Status: 🚧 In Progress

---

## Phase 5

* Inventory Service

Status: 🚧 Planned

---

## Phase 6

* Payment Service

Status: 🚧 Planned

---

## Phase 7

* API Gateway
* Config Server
* Service Registry

Status: 🚧 Planned

---

## Learning Outcomes

* Spring Boot
* Spring Security
* JWT Authentication
* REST API Design
* PostgreSQL
* Redis Caching
* Apache Kafka
* Event-Driven Architecture
* Outbox Pattern
* Transaction Management
* Docker
* Microservices Architecture
* Distributed Systems Fundamentals

---

## Author

**Anurag Tiwari**

This project is being built incrementally to demonstrate backend development best practices, distributed systems concepts, and production-ready microservices architecture using the Spring ecosystem.
