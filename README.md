
Alumni LinkedIn Profile Searcher (Backend)

A Spring Boot backend application that searches alumni profiles based on **University**, **Current Designation**, and optional **Passout Year**.

The system follows a **DB-first strategy**:

* First checks PostgreSQL for existing alumni data
* If not found, triggers **PhantomBuster** to fetch data from LinkedIn
* Saves fetched data into PostgreSQL for future requests

---

## 🚀 Tech Stack

Java 21
Spring Boot 4
Spring Data JPA
PostgreSQL
PhantomBuster API
REST APIs
Maven

---

## 🧠 High-Level Architecture

```
Client (Postman / Frontend)
        |
        v
AlumniController
        |
        v
AlumniService
        |
        +--> PostgreSQL (DB First)
        |
        +--> PhantomBuster (DB Miss)
```

---

## 📦 Features

* Search alumni by:

  * University (mandatory)
  * Designation (mandatory)
  * Passout Year (optional)
* PostgreSQL caching (avoids repeated LinkedIn scraping)
* Clean layered architecture:

  * Controller
  * Service
  * Repository
  * Client (External API)
* Extensible for real LinkedIn parsing later

---

## 📂 Project Structure

```
com.example.AlumniLinkedInProfile
│
├── controller
│   └── AlumniController.java
│
├── service
│   ├── AlumniService.java
│   └── impl
│       └── AlumniServiceImpl.java
│
├── repository
│   └── AlumniRepository.java
│
├── entity
│   └── AlumniProfile.java
│
├── dto
│   ├── RequestDTO.java
│   └── ResponseDTO.java
│
├── client
│   └── PhantomBusterClient.java
│
└── AlumniLinkedInProfileApplication.java
```

---

## 🗄️ Database Configuration (PostgreSQL)

### Create Database

```sql
CREATE DATABASE alumni_db;
```

(Optional user)

```sql
CREATE USER alumni_user WITH PASSWORD 'password';
GRANT ALL PRIVILEGES ON DATABASE alumni_db TO alumni_user;
```

---

### `application.properties`

```properties
# ===============================
# PostgreSQL Configuration
# ===============================
spring.datasource.url=jdbc:postgresql://localhost:5432/alumni_db
spring.datasource.username=alumni_user
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

# ===============================
# JPA / Hibernate
# ===============================
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect

# ===============================
# PhantomBuster Configuration
# ===============================
phantombuster.api.key=YOUR_PHANTOMBUSTER_API_KEY
phantombuster.agent.id=YOUR_AGENT_ID
```

---

## 🔑 PhantomBuster Setup

1. Create an account on **PhantomBuster**
2. Create a **LinkedIn Search Agent**
3. Provide:

   * LinkedIn session cookie
   * Browser user agent
4. Copy:

   * **API Key**
   * **Agent ID**
5. Paste them into `application.properties`

⚠️ **Note:** PhantomBuster launches jobs asynchronously.
This project demonstrates **integration flow**, not real-time scraping.

---

## 🔌 API Endpoints

### 1️⃣ Search Alumni Profiles

**URL**

```
POST /api/alumni/search
```

**Request Body**

```json
{
  "university": "IIT Bombay",
  "designation": "Software Engineer",
  "passOutYear": 2020
}
```

**Response**

```json
{
  "status": "success",
  "data": [
    {
      "name": "John Doe",
      "designation": "Software Engineer",
      "university": "IIT Bombay",
      "location": "Mumbai, India",
      "linkedInHeadline": "Software Engineer at XYZ",
      "passOutYear": 2020
    }
  ]
}
```

---

### 2️⃣ (Optional) Get All Saved Alumni

**URL**

```
GET /api/alumni/all
```

Returns all alumni stored in PostgreSQL.

---

## 🔄 Request Flow Explained

1. Client sends search request
2. Service checks PostgreSQL:

   * **If data exists** → return immediately
   * **If not found**:

     * PhantomBuster is triggered
     * Mock/parsed data is generated
     * Data is saved to PostgreSQL
3. Response returned to client
4. Next identical request → served from DB

---

## ▶️ How to Run

```bash
mvn clean install
mvn spring-boot:run
```

Application starts on:

```
http://localhost:8080
```

---

## 🧪 Testing with Postman

* Method: `POST`
* URL: `http://localhost:8080/api/alumni/search`
* Headers:

  ```
  Content-Type: application/json
  ```
* Body:

```json
{
  "university": "IIT Bombay",
  "designation": "Software Engineer"
}
```

---

## ⚠️ Important Notes

* PhantomBuster does **not return LinkedIn data instantly**
* This project demonstrates:

  * External API orchestration
  * DB caching strategy
  * Clean backend design
* Real LinkedIn parsing can be added via:

  * PhantomBuster result polling API
  * Webhooks

---

## ✅ What This Project Demonstrates

* Clean backend architecture
* External API integration
* PostgreSQL persistence
* DB-first optimization
* Production-ready structure

---

## 📌 Future Improvements

* Async PhantomBuster result polling
* Webhook-based updates
* Pagination & filters
* Authentication
* Docker + Docker Compose
* Flyway migrations

---

## 👤 Author

**Abhishek Tiwari**
Backend Developer | Java | Spring Boot
