# 📚 Book Catalog API

A Spring Boot application that calculates discounted prices for baskets of books. Rest API, and Swagger-based API documentation.

---

## 🚀 Getting Started

### ✅ Prerequisites

- Java 21 or higher
- Maven 3.6 or higher
- Git (if cloning from GitHub)

---

### 📦 Clone and Build the Project

```
git clone https://github.com/maurymartinez/2025-DEV3-009-DevelopmentBooks.git
cd 2025-DEV3-009-DevelopmentBooks

# Build the project
mvn clean install
```

### ▶️ Run the Application

```
# Option 1: Run with Maven
mvn spring-boot:run

# Option 2: Run the JAR directly
java -jar target/DevelopmentBooks-1.0.0.jar
```

### 🧪 API Testing with Swagger UI

Once the application is running, open your browser and navigate to:
```
http://localhost:8080/swagger-ui.html
```

## 🧪 Testing the API with Postman

You can also test the `/api/catalog/price` endpoint using **Postman**:

### ✅ 1. Configure the Request

- **Method:** `POST`
- **URL:** `http://localhost:8080/api/catalog/price`

### 🧾 2. Set Headers

| Key            | Value              |
|----------------|--------------------|
| Content-Type   | application/json   |

### 📦 3. Request Body

In the **Body** tab, choose `raw` and select `JSON` as the format. Paste this:

```json
{
  "items": [
    { "name": "Clean Code", "amount": 1 },
    { "name": "The Clean Coder", "amount": 1 },
    { "name": "Clean Architecture", "amount": 1 },
    { "name": "Test Driven Development by Example", "amount": 1 },
    { "name": "Working Effectively With Legacy Code", "amount": 1 }
  ]
}
