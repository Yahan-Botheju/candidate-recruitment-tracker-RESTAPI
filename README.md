# ⚡ Candidate Recruitment Tracker System

![Java](https://img.shields.io/badge/Java-21-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Gradle](https://img.shields.io/badge/Build-Gradle-orange)
![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue)
![License](https://img.shields.io/badge/license-MIT-lightgrey)

---

# 📌 Overview

This project is a **Spring Boot REST API** built using **Clean Architecture principles**.

It manages candidate recruitment workflows and demonstrates:

* Proper layered architecture
* DTO + MapStruct mapping
* Role-based access control
* Business logic separation
* Recruitment score calculation

---

# 📌 Features

*  Candidate registration
*  Update candidate details
*  Soft delete support
*  Pagination support
*  Recruitment score calculation
*  Role-based access control (ADMIN / USER)
*  Candidate status management (APPLIED → HIRED / REJECTED / SHORTLISTED)
*  Clean Architecture implementation
*  DTO + MapStruct mapping
*  Interceptor-based authorization
*  Swagger API documentation
---

# 🛠️ Tech Stack

| Category           | Technology                  |
|--------------------| --------------------------- |
| Language           | Java 21                     |
| Framework          | Spring Boot 3.x             |
| Build Tool         | Gradle                      |
| Database           | PostgreSQL                  |
| ORM                | Spring Data JPA / Hibernate |
| Mapping            | MapStruct                   |
| API Documentation  | Springdoc OpenAPI (Swagger) |
| Boilerplate        | Lombok                      |
| Architecture       | Clean Architecture          |

---

# 🏗️ Architecture Overview

The project follows **Clean Architecture**:

```text id="c9a1q1"
Controller → UseCase → Domain → Repository Interface
                                 ↓
                          Infrastructure (JPA)
```

---

## 📂 Layers Explanation

### 📌 Domain Layer

* Core business models (`Candidate`, `CandidateStatus`)
* Repository interfaces

### 📌 UseCase Layer

* Business logic (score calculation, status rules)
* Application workflows

### 📌 Infrastructure Layer

* Database entities
* JPA repositories
* Persistence implementation

### 📌 Web Layer

* REST Controllers
* DTOs
* Mappers
* Interceptors

---

# 🚀 Features in Detail

*  CRUD operations for candidates
*  Pagination support for large datasets
*  Recruitment score calculation system
*  Soft delete using Hibernate
*  Role-based authorization via interceptor
*  Status workflow (APPLIED → SHORTLISTED → HIRED / REJECTED)
*  DTO-based response handling
*  MapStruct for object mapping

---

# 📂 Project Structure

```text id="p1v9x2"
src
 └── main
     └── java
         ├── lk
         │   └── practice
         │       └── candidate_recruitment_tracker
         │           ├── domain
         │           │   ├── model
         │           │   │   ├── Candidate.java
         │           │   │   └── CandidateStatus.java
         │           │   └── repository
         │           │       └── CandidateRepository.java
         │           │
         │           ├── infrastructure
         │           │   ├── config
         │           │   │   └── BeanConfig.java
         │           │   │
         │           │   └── persistence
         │           │       ├── persistenceMappers
         │           │       │   └── CandidatePersistenceMapper.java
         │           │       ├── CandidateEntity.java
         │           │       ├── CandidateImpl.java
         │           │       └── JpaCandidateRepository.java
         │           │
         │           ├── usecase
         │           │   ├── CandidateUseCase.java
         │           │   └── CandidateUseCaseImpl.java
         │           │
         │           └── web
         │               ├── config
         │               │   └── WebConfig.java
         │               │
         │               ├── controllers
         │               │   └── CandidateController.java
         │               │
         │               ├── DTOs
         │               │   ├── CandidateRequestDTO.java
         │               │   └── CandidateResponseDTO.java
         │               │
         │               ├── interceptors
         │               │   └── RoleBasedInterceptor.java
         │               │
         │               └── webMappers
         │                   └── CandidateWebMapper.java
         │
         └─────────────────── resources
                              ├── static
                              ├── templates
                              ├── application.properties
                              └── example-application.properties
```

---

# ⚙️ API Endpoints

| Method | Endpoint                                | Description                    | Access       |
| ------ | --------------------------------------- | ------------------------------ | ------------ |
| GET    | `/api/v1/candidates/all?page=0&size=10` | Get all candidates (paginated) | USER / ADMIN |
| POST   | `/api/v1/candidates/register`           | Register a new candidate       | USER / ADMIN |
| PUT    | `/api/v1/candidates/{id}`               | Update candidate details       | USER / ADMIN |
| DELETE | `/api/v1/candidates/{id}`               | Soft delete candidate          | ADMIN ONLY   |
| PUT    | `/api/v1/candidates/status/{id}`        | Update candidate status        | ADMIN ONLY   |

---

# 🔐 Authorization

| Header        | Description                   |
| ------------- | ----------------------------- |
| `X-User-Role` | User role (`ADMIN` or `USER`) |

---

# 🧮 Recruitment Score Logic

Candidate score is calculated using:

* Experience points: `experience × 10`
* Salary penalty applied if salary > 500,000

```text id="s2k9x1"
Recruitment Score = (Experience × 10) - Salary Penalty
```

---


# 📚 API Documentation (Swagger)

Swagger UI:

```text id="s2k9x1"
http://localhost:5000/swagger-ui/index.html
```
OpenAPI JSON:
```text id="s2k9x1"
http://localhost:5000/v3/api-docs
```
---


# 📥 Sample Request

```json id="r1p9a2"
{
  "fullName": "John Doe",
  "email": "john@example.com",
  "experience": 3,
  "appliedRole": "Software Engineer",
  "expectedSalary": 600000
}
```

---

# 📤 Sample Response

```json id="r2k8v3"
{
  "id": 1,
  "fullName": "John Doe",
  "email": "john@example.com",
  "experience": 3,
  "appliedRole": "Software Engineer",
  "expectedSalary": 600000,
  "status": "APPLIED",
  "recruitmentScore": 30.0
}
```

---


# ▶️ How to Run

### 1. Clone repository

```bash id="c1m8x2"
git clone https://github.com/your-username/candidate-recruitment-tracker.git
```

### 2. Navigate to project

```bash id="c2p9v1"
cd candidate-recruitment-tracker
```

### 3. Run application

```bash id="c3k1x7"
./gradlew bootRun
```

### 4. Access API

```text id="c4x8p2"
http://localhost:5000/api/v1/candidates
```

---


# 👨‍💻 Author

Built as a **Clean Architecture practice project** to improve backend design skills using Spring Boot.

---

# ⭐ Project Purpose

This project was created to practice:

* Clean Architecture
* REST API design
* Real-world backend structuring
* Scalable system design principles

---

# 📌 Note

This is a **learning-focused project** designed with real-world architecture patterns and can be extended into a production-grade system.
