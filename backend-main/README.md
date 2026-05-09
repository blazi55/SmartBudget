# Budget Service

## 📌 Overview

**Budget Service** is the core microservice responsible for managing users' financial data within the SmartBudget system. It handles all operations related to budgets, transactions, and expense categorization.

This service acts as the **source of truth** for financial records and is optimized for **write operations and transactional consistency**.

---

## 🎯 Responsibilities

The Budget Service is responsible for:

* Creating and managing **budgets**
* Recording **income and expenses (transactions)**
* Managing **categories** (e.g., Food, Transport, Entertainment)
* Enforcing **business rules** (e.g., budget limits)
* Providing data for other services (e.g., Report Service)

---

## 🧱 Core Domain

Main entities managed by this service:

* **User**
* **Budget**
* **Transaction**
* **Category**

---

## 🔄 Role in System Architecture

The Budget Service is part of a microservices architecture and plays the role of:

* **Write Model (CQRS pattern)**
* **Primary data owner**

It is responsible for persisting all financial operations and publishing domain events to other services.

---

## 📡 Integration

The service can communicate with other components via:

### 1. REST API

Used for synchronous operations such as:

* Creating transactions
* Managing budgets
* Fetching user data

### 2. Event Streaming

The service publishes domain events (e.g., `TransactionCreated`) to an event broker like Apache Kafka.

These events are consumed by other services, such as the Report Service, to build analytical views.

---

## ⚙️ Key Features

* Transaction management with validation
* Budget limit tracking
* Category-based organization
* Event publishing for downstream services
* Scalable and modular architecture

---

## 🚀 Example Use Cases

* A user adds a new expense → stored in the database → event emitted
* A budget limit is defined → system tracks spending against it
* External services consume financial data via events or API

---

## 🧠 Design Principles

* **Single Responsibility** – focuses only on financial data management
* **High cohesion, low coupling**
* **Event-driven communication**
* **Database per service**

---

## 📂 Typical Flow

```
Client → Controller → Service → Repository → Database
                                ↓
                             Event (Kafka)
```

---

## 🛠️ Technology Stack (example)

* Java + Spring Boot
* Spring Data JPA
* PostgreSQL
* Apache Kafka
* Docker / Podman

---

## 📎 Notes

This service does not handle reporting or analytics directly.
All heavy data processing and aggregations should be delegated to a dedicated **Report Service**.
