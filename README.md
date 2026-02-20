# 📘 toupel-cafe-business — toupel management API services

This project is a **Spring Boot 3 / JDK 21** application built as part of a personal DevOps learning journey.  
It is designed to serve as a backend service that will later be containerized, deployed, monitored, and automated with full Infrastructure‑as‑Code.

---

# 🚀 Overview

**Spring DevOps Demo** is a lightweight microservice featuring:

- REST API exposing `/transactions`
- PostgreSQL database integration
- Spring Boot Actuator (health, metrics, info)
- Readiness & liveness probes (Kubernetes‑ready)
- Prometheus metrics export
- Clean architecture (controller → service → model)

This app is intentionally simple to let you focus on **DevOps, cloud automation, CI/CD, infra orchestration, observability, and deployments**.

---

# 🏗️ Technologies

| Layer | Tech |
|------|------|
| Language | Java 21 |
| Framework | Spring Boot 3.x |
| Build tool | Maven |
| Database | PostgreSQL |
| Observability | Actuator + Micrometer + Prometheus |
| Packaging | JAR (Docker to be added later) |

---

# 📂 Project Structure
    toupel-cafe-business/
    ├── pom.xml
    ├── src
    │   ├── main/java/com/example/demo
    │   │   ├── DemoApplication.java
    │   │   ├── controller/TransactionController.java
    │   │   ├── service/TransactionService.java
    │   │   └── model/Transaction.java
    │   └── main/resources/application.yaml
    └── README.md

---

# 🔧 Features

## ✔ REST API

`GET /transactions` → list all transactions  
`POST /transactions` → create a new transaction

---

## ✔ PostgreSQL Integration

Configured via `application.yaml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/devopsdb
    username: postgres
    password: postgres
```
## ✔ Actuator Endpoints

Enabled in this project:

| Purpose           | Endpoint                               |
|------------------|-----------------------------------------|
| Full health       | `/actuator/health`                      |
| Liveness probe    | `/actuator/health/liveness`             |
| Readiness probe   | `/actuator/health/readiness`            |
| Prometheus metrics| `/actuator/prometheus`                  |


    