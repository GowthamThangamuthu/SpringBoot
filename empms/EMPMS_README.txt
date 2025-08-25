
# Employee Management System (EMPMS)

A **Spring Boot 3** REST API for managing employees, departments, and users with **JWT authentication** and **role-based access control**.  
Includes Swagger API documentation for easy testing.

---

## 🚀 Features

- **User Authentication** (JWT-based)
- **Role-based Authorization**
  - **ADMIN**: Full access (CRUD for employees & departments)
  - **MANAGER**: Read access for all employees
  - **EMPLOYEE**: Read access for own details only
- **Employee Management**
- **Department Management**
- **Swagger UI Documentation**
- **MySQL Database Integration**

---

## 🛠 Tech Stack

- **Backend:** Spring Boot 3, Spring Security, Spring Data JPA, Hibernate
- **Database:** MySQL
- **Authentication:** JWT (JSON Web Token)
- **Documentation:** SpringDoc OpenAPI + Swagger UI
- **Build Tool:** Maven
- **Java Version:** 21

---

## 📂 Project Structure

src/main/java/com/example/empms
├── config        # Security configuration
├── controller    # REST Controllers
├── dto           # Data Transfer Objects
├── model         # JPA Entities
├── repository    # Spring Data Repositories
├── security      # JWT utilities & filters
└── service       # Business Logic

---

## ⚙️ Setup Instructions

### 1️⃣ Clone the repository
git clone https://github.com/your-username/empms.git
cd empms

### 2️⃣ Configure MySQL Database
Edit src/main/resources/application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/empms
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

### 3️⃣ Build and Run
mvn clean install
mvn spring-boot:run

---

## 🔑 API Authentication Flow

1. **Register** a user → POST /api/auth/register
2. **Login** with credentials → POST /api/auth/login
3. Copy the **JWT token** from response
4. In Swagger UI, click **Authorize** → Paste `Bearer <your-token>`

---

## 📜 API Endpoints

### Authentication
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | `/api/auth/register` | PUBLIC | Register a new user |
| POST | `/api/auth/login` | PUBLIC | Login and get JWT token |

### Employees
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | `/api/employees` | ADMIN | Create new employee |
| GET  | `/api/employees` | ADMIN, MANAGER, EMPLOYEE | Get employees (Employee sees only own data) |

### Departments
| Method | Endpoint | Role | Description |
|--------|----------|------|-------------|
| POST | `/api/departments` | ADMIN | Create department |
| GET  | `/api/departments` | ADMIN, MANAGER, EMPLOYEE | List all departments |

---

## 📄 Swagger UI

After running the project, open:
http://localhost:8080/swagger-ui/index.html

---

## 📦 Download API Docs
You can download the OpenAPI spec (JSON) from:
http://localhost:8080/v3/api-docs

---

## 📊 Sample API Requests & Responses

### 1. Register User
**Request:**
POST /api/auth/register
{
  "username": "admin",
  "password": "password123",
  "role": "ADMIN"
}

**Response:**
{
  "message": "User registered successfully!"
}

### 2. Login User
**Request:**
POST /api/auth/login
{
  "username": "admin",
  "password": "password123"
}

**Response:**
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
}

### 3. Create Employee (Admin Only)
**Request:**
POST /api/employees
Authorization: Bearer <token>
{
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "salary": 50000,
  "departmentId": 1
}

**Response:**
{
  "id": 1,
  "firstName": "John",
  "lastName": "Doe",
  "email": "john@example.com",
  "salary": 50000,
  "department": {
    "id": 1,
    "name": "IT"
  }
}

### 4. Get All Employees
**Request:**
GET /api/employees
Authorization: Bearer <token>

**Response:**
[
  {
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john@example.com",
    "salary": 50000,
    "department": {
      "id": 1,
      "name": "IT"
    }
  }
]

---

## 📌 Future Enhancements
- Add pagination & sorting
- Add update & delete operations
- Add unit & integration tests
- Add audit logging

---

## 👨‍💻 Author
**Gowtham**  
Backend Developer | Java | Spring Boot | MySQL
