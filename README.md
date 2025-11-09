# Task Manager API

A robust, production-ready RESTful API for managing tasks built with Spring Boot. This project demonstrates modern Java development practices, clean architecture, and enterprise-level features including pagination, filtering, DTO mapping, and database versioning.

## 🚀 Features

### Core Functionality
- **CRUD Operations**: Complete Create, Read, Update, and Delete operations for tasks
- **Pagination**: Efficient pagination support with customizable page size (default: 5 tasks per page)
- **Filtering**: Advanced filtering capabilities by:
  - Priority (LOW, MEDIUM, HIGH)
  - Status (NOT_STARTED, PENDING, IN_PROGRESS, COMPLETED)
  - Combined priority and status filters
- **Sorting**: Automatic sorting by task title in ascending order
- **Task Management**: 
  - Create tasks with title, description, priority, status, and due date
  - Update existing tasks
  - Retrieve tasks by ID or with pagination
  - Delete tasks

### Technical Features
- **DTO Pattern**: Separation of concerns using Data Transfer Objects (DTOs) for API responses
- **MapStruct Integration**: Type-safe, compile-time entity-to-DTO mapping with zero runtime overhead
- **Input Validation**: Comprehensive validation using Jakarta Bean Validation
- **Exception Handling**: Custom exception handling with `ResourceNotFound` exception
- **Global Response Wrapper**: Consistent API response structure with `ApiResponse` wrapper
- **Database Versioning**: Liquibase integration for database schema and data migration management
- **JPA Auditing**: Automatic timestamp management for entity creation dates
- **HATEOAS Support**: Spring HATEOAS integration for RESTful API design

## 🛠️ Technology Stack

### Backend Framework
- **Spring Boot 3.5.7**: Modern Java application framework
- **Java 25**: Latest Java features and performance improvements

### Data Layer
- **Spring Data JPA**: Simplified database access and repository pattern
- **PostgreSQL**: Robust relational database management system
- **Liquibase**: Database schema versioning and migration tool

### Mapping & Validation
- **MapStruct 1.6.3**: Compile-time code generation for object mapping
- **Jakarta Bean Validation**: Input validation and constraint enforcement
- **Lombok**: Reduced boilerplate code with annotations

### Development Tools
- **Gradle**: Modern build automation and dependency management
- **Spring Boot DevTools**: Enhanced development experience with hot reload
- **JUnit 5**: Comprehensive testing framework

## 📋 API Endpoints

### Base URL
```
/api/tasks
```

### Endpoints

#### 1. Get All Tasks (Paginated)
```http
GET /api/tasks?page={pageNumber}&priority={priority}&status={status}
```

**Query Parameters:**
- `page` (optional, default: 0): Page number (0-indexed)
- `priority` (optional): Filter by priority (LOW, MEDIUM, HIGH)
- `status` (optional): Filter by status (NOT_STARTED, PENDING, IN_PROGRESS, COMPLETED)

**Response:**
```json
{
  "content": [
    {
      "id": 1,
      "title": "Complete project documentation",
      "description": "Write comprehensive README",
      "status": "IN_PROGRESS",
      "priority": "HIGH",
      "due_date": "2024-12-31",
      "created_at": "2024-11-09"
    }
  ],
  "page_number": 0,
  "page_size": 5,
  "total_elements": 10,
  "total_pages": 2,
  "last": false
}
```

#### 2. Get Task by ID
```http
GET /api/tasks/{id}
```

**Response:**
```json
{
  "id": 1,
  "title": "Complete project documentation",
  "description": "Write comprehensive README",
  "status": "IN_PROGRESS",
  "priority": "HIGH",
  "due_date": "2024-12-31",
  "created_at": "2024-11-09"
}
```

#### 3. Create Task
```http
POST /api/tasks
Content-Type: application/json
```

**Request Body:**
```json
{
  "title": "New Task",
  "description": "Task description",
  "status": "NOT_STARTED",
  "priority": "MEDIUM",
  "due_date": "2024-12-31"
}
```

**Validation Rules:**
- `title`: Required, max 100 characters
- `status`: Required
- `priority`: Required
- `due_date`: Must be today or in the future

#### 4. Update Task
```http
PUT /api/tasks/{id}
Content-Type: application/json
```

**Request Body:** Same as Create Task

#### 5. Delete Task
```http
DELETE /api/tasks/{id}
```

**Response:** `true` on successful deletion

## 🗄️ Database Schema

### Tasks Table
```sql
CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    status VARCHAR(50),
    priority VARCHAR(50),
    due_date DATE,
    created_at DATE DEFAULT CURRENT_DATE
);
```

### Enums

**Priority:**
- `LOW`
- `MEDIUM`
- `HIGH`

**TaskStatus:**
- `NOT_STARTED`
- `PENDING`
- `IN_PROGRESS`
- `COMPLETED`

## 🏗️ Architecture

### Layered Architecture
```
┌─────────────────────────────────┐
│   REST Controller Layer         │  (TaskRestController)
├─────────────────────────────────┤
│   Service Layer                 │  (TaskService, TaskServiceImpl)
├─────────────────────────────────┤
│   Repository Layer              │  (TaskRepository)
├─────────────────────────────────┤
│   Entity Layer                  │  (Task)
└─────────────────────────────────┘
```

### Key Components

1. **Controller Layer** (`TaskRestController`)
   - Handles HTTP requests and responses
   - Input validation
   - DTO conversion using MapStruct

2. **Service Layer** (`TaskServiceImpl`)
   - Business logic implementation
   - Pagination and filtering logic
   - Exception handling

3. **Repository Layer** (`TaskRepository`)
   - Extends Spring Data JPA `JpaRepository`
   - Custom query methods for filtering
   - Database abstraction

4. **Entity Layer** (`Task`)
   - JPA entity with Lombok annotations
   - JPA Auditing for automatic timestamp management

5. **DTO Layer** (`TaskDTO`)
   - Data Transfer Objects for API communication
   - Validation constraints
   - Separation from entity layer

6. **Mapper Layer** (`TaskMapper`)
   - MapStruct interface for entity-DTO conversion
   - Extends `BaseMapper` for reusable mapping logic
   - Compile-time code generation

## 📁 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/kyra/TaskManager/
│   │       ├── TaskManagerApplication.java
│   │       ├── controllers/
│   │       │   └── TaskRestController.java
│   │       ├── service/
│   │       │   ├── TaskService.java
│   │       │   └── impl/
│   │       │       └── TaskServiceImpl.java
│   │       ├── repository/
│   │       │   └── TaskRepository.java
│   │       ├── db/
│   │       │   ├── Task.java
│   │       │   └── enums/
│   │       │       ├── Priority.java
│   │       │       └── TaskStatus.java
│   │       ├── dto/
│   │       │   ├── TaskDTO.java
│   │       │   └── PagedResponse.java
│   │       ├── converters/
│   │       │   ├── BaseMapper.java
│   │       │   └── TaskMapper.java
│   │       ├── exceptions/
│   │       │   └── ResourceNotFound.java
│   │       └── advice/
│   │           ├── ApiResponse.java
│   │           └── GlobalResponseHandler.java
│   └── resources/
│       ├── application.properties
│       └── db/
│           └── changelog/
│               ├── main-changelog.xml
│               ├── schema/
│               │   └── 01-create-task-table.sql
│               └── data/
│                   └── 01-insert-starter-values.sql
└── test/
    └── java/
        └── com/kyra/TaskManager/
            └── TaskManagerApplicationTests.java
```

## 🚀 Getting Started

### Prerequisites
- Java 25 or higher
- PostgreSQL 12 or higher
- Gradle 9.2 or higher

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd TaskManager
   ```

2. **Set up PostgreSQL database**
   ```sql
   CREATE DATABASE taskmgr;
   ```

3. **Configure database connection**
   
   Update `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:postgresql://localhost:5432/taskmgr
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

4. **Run Liquibase migrations**
   
   The database schema will be automatically created on application startup via Liquibase.

5. **Build the project**
   ```bash
   ./gradlew build
   ```

6. **Run the application**
   ```bash
   ./gradlew bootRun
   ```

   Or using the JAR:
   ```bash
   java -jar build/libs/TaskManager-0.0.1-SNAPSHOT.jar
   ```

7. **Access the API**
   
   The application will start on `http://localhost:8080`
   
   Test the API:
   ```bash
   curl http://localhost:8080/api/tasks
   ```

## 🧪 Testing

Run tests using Gradle:
```bash
./gradlew test
```

## 📝 Configuration

### Application Properties

Key configuration options in `application.properties`:

- **Database**: PostgreSQL connection settings
- **Liquibase**: Database migration configuration
- **Jackson**: JSON property naming strategy (SNAKE_CASE)
- **JPA**: Hibernate DDL auto mode (set to `none` for production)

## 🎯 Key Highlights

### Best Practices Implemented
- ✅ **Clean Architecture**: Separation of concerns with layered architecture
- ✅ **DTO Pattern**: Prevents entity exposure and provides API versioning flexibility
- ✅ **MapStruct**: Zero-overhead object mapping at compile-time
- ✅ **Input Validation**: Comprehensive validation using Jakarta Bean Validation
- ✅ **Exception Handling**: Custom exceptions with proper error handling
- ✅ **Database Versioning**: Liquibase for schema and data migration management
- ✅ **Pagination**: Efficient data retrieval with Spring Data pagination
- ✅ **Filtering**: Dynamic query methods for flexible data retrieval
- ✅ **Code Generation**: Lombok for reducing boilerplate code
- ✅ **RESTful Design**: Proper HTTP methods and status codes

### Performance Optimizations
- Compile-time code generation (MapStruct, Lombok)
- Efficient pagination to reduce memory footprint
- Database indexing on frequently queried fields
- JPA query optimization with custom repository methods

### Security Considerations
- Input validation on all endpoints
- SQL injection prevention through JPA parameterized queries
- Proper exception handling to avoid information leakage

## 🔧 Development

### Build Commands
```bash
# Clean build
./gradlew clean build

# Run application
./gradlew bootRun

# Run tests
./gradlew test

# Generate MapStruct implementations
./gradlew compileJava
```

### Code Generation
- **MapStruct**: Generated implementations in `src/main/generated/`
- **Lombok**: Compile-time code generation for getters, setters, builders

## 📚 Technologies & Libraries

| Technology | Version | Purpose |
|------------|---------|---------|
| Spring Boot | 3.5.7 | Application framework |
| Spring Data JPA | 3.5.7 | Data access layer |
| PostgreSQL | Latest | Database |
| Liquibase | Latest | Database migrations |
| MapStruct | 1.6.3 | Object mapping |
| Lombok | Latest | Code generation |
| Jakarta Validation | Latest | Input validation |
| Spring HATEOAS | 3.5.7 | RESTful API support |

## 🤝 Contributing

This is a personal project, but suggestions and improvements are welcome!

## 📄 License

This project is open source and available for educational purposes.

## 👤 Author

**Kyra**
- Built with Spring Boot and modern Java best practices
- Demonstrates enterprise-level RESTful API development

---

**Note**: This project demonstrates proficiency in:
- Spring Boot and Spring ecosystem
- RESTful API design and implementation
- Database design and migration management
- Object-oriented programming and design patterns
- Build tools and dependency management
- Code quality and best practices

