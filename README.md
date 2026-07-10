# NFS_JAVA_C2_2026 | Full-Stack Development with Java, React & MongoDB



## Programme Description



This 20-day programme is designed to help participants build a complete full-stack web application using Java, Spring Boot, React, and MongoDB.



The programme takes learners from programming and web fundamentals to backend API development, frontend interface design, database modelling, authentication, testing, performance improvement, and final capstone presentation.



Throughout the programme, participants will work on practical exercises and gradually build a small but production-like web application. The final outcome is a working capstone project that demonstrates the use of a React frontend, Spring Boot backend, MongoDB database, secure authentication, API documentation, testing practices, and deployment-readiness basics.



AI tools such as Gemini are used as learning accelerators to help scaffold examples, suggest refactoring ideas, draft tests, generate sample data, and support MongoDB query or aggregation design. However, participants are expected to review, verify, understand, and take ownership of all generated code.



---



## Programme Duration



* Duration: 20 training days

* Daily Duration: 7 hours per day

* Total Training Hours: 140 hours

* Mode: Instructor-led training with guided labs, team build activities, review sessions, quizzes, and capstone development



---



## Programme Objectives



By the end of this programme, participants will be able to:



* Understand web fundamentals, HTTP, REST, and JSON.

* Write basic to intermediate Java and JavaScript code.

* Build REST APIs using Spring Boot.

* Apply validation, authentication, authorisation, and error-handling practices.

* Model data effectively using MongoDB.

* Use MongoDB indexes, queries, pagination, and aggregation pipelines.

* Build accessible React user interfaces with routing, forms, state, and data fetching.

* Apply testing practices for backend and frontend development.

* Use AI coding assistants responsibly for learning, refactoring, testing, and documentation.

* Design, build, document, and present a full-stack capstone project.



---





---



## Day 7 Exercise 1 — Reflection Questions

### 1. What is the purpose of the `admin` database?

The `admin` database is MongoDB's built-in administrative database that serves as the home for all server-level administrative users and operations. It stores superuser and administrator accounts (such as the `root` role) that have the authority to manage the entire MongoDB server. Any user created in the `admin` database with a server-wide role can perform actions across all databases, such as creating or dropping databases, managing other users, and configuring replication or sharding. It is also the required authentication source for administrative credentials when authentication is enabled.

---

### 2. Why should an application use its own database user instead of the root administrator?

An application should use a dedicated database user rather than the root administrator for the following reasons:

- **Principle of Least Privilege:** The application user should only have the minimum permissions needed (e.g., `readWrite` on a specific database). The root administrator has unrestricted access to the entire server, which is far more than any application needs.
- **Security isolation:** If the application's credentials are compromised through a code vulnerability or configuration leak, the damage is limited to that one database. A compromised root account would expose every database on the server.
- **Auditability:** Using separate accounts makes it easier to trace which actions were performed by the application versus a human administrator.
- **Accidental damage prevention:** An application running with root privileges could accidentally drop collections or databases outside its own scope.

---

### 3. What is the difference between authentication and authorization?

| Concept | Definition | Question it answers |
|---|---|---|
| **Authentication** | The process of verifying the identity of a user or system — confirming *who* you are. | "Are you really who you claim to be?" |
| **Authorization** | The process of determining what an authenticated identity is permitted to do — defining *what* you can access. | "Are you allowed to perform this action?" |

In MongoDB's context:
- **Authentication** happens when a user provides a username and password to connect. MongoDB verifies the credentials before allowing a connection.
- **Authorization** happens after successful authentication. MongoDB checks the roles assigned to that user to decide whether the requested operation (e.g., insert, read, drop) is permitted on a given database or collection.

Authentication must come before authorization — you must first prove who you are before the system can decide what you are allowed to do.

---

### 4. What would happen if authentication was disabled on a production database?

Disabling authentication on a production MongoDB instance would create severe security risks:

- **Unrestricted access:** Any person or process that can reach the network port (default: `27017`) could connect to the database without providing any credentials.
- **Data breach:** Sensitive data such as user records, personal information, and business data could be read or exported by anyone with network access.
- **Data tampering or destruction:** An attacker could modify, corrupt, or delete all data in every database on the server.
- **Full server compromise:** Without authentication, an attacker could create new administrator accounts, disable existing protections, or use the database server as a pivot point to attack other internal systems.
- **Compliance violations:** Most data protection regulations (e.g., GDPR, PDPA) require access controls on systems holding personal data. Disabling authentication would violate these requirements and expose the organisation to legal and financial penalties.

In short, a MongoDB instance with authentication disabled in production is effectively an open, public database — a critical security failure.

---

## Day 7 Exercise 5 — Persistence Checkpoint: Reflection Questions

### 1. What is the role of the repository?

The repository (`TicketRepository`) acts as the **data access layer** between the application and MongoDB. It extends `MongoRepository<Ticket, String>`, which provides ready-to-use CRUD operations (save, find all, find by ID, delete, count, etc.) without writing any database queries manually. Its role is to abstract away all the low-level MongoDB interaction so that the service layer can work with plain Java objects instead of raw database commands. In this project, `TicketRepository` is the only class that directly communicates with the `tickets` collection in MongoDB.

---

### 2. What is the difference between `Ticket` and `TicketResponse`?

| Aspect | `Ticket` (Model) | `TicketResponse` (DTO) |
|---|---|---|
| **Package** | `com.example.supportdesk.model` | `com.example.supportdesk.dto` |
| **Purpose** | Represents the domain entity stored in MongoDB. It is annotated with `@Document(collection = "tickets")` and `@Id` so Spring Data MongoDB knows how to persist it. | A Data Transfer Object that shapes the data sent back to the client in API responses. |
| **Setter methods** | Has setters — fields can be modified after creation. | No setters — it is read-only. Once constructed, the values cannot be changed. |
| **Annotations** | Uses Spring Data annotations (`@Document`, `@Id`) to map to a MongoDB collection. | Plain Java class with no database annotations. |
| **Direction** | Used for **input** to the database (save/persist). | Used for **output** from the API (response to client). |

This separation ensures that the database model is never directly exposed to the API consumer, allowing the API response format to evolve independently from the storage model.

---

### 3. What does MongoDB store as the document ID?

MongoDB stores a **`_id`** field on every document. In this project, the `Ticket` model declares `@Id private String id;`. Spring Data MongoDB maps this `id` field to MongoDB's native `_id` field. When a new ticket is saved without providing an `id`, MongoDB **auto-generates a unique `ObjectId`** (a 24-character hexadecimal string, e.g., `668a1f3e9b2d4c7a1234abcd`) and stores it as the value of `_id`. Spring Data then reads it back as a `String` and returns it through `getId()`.

---

### 4. Why should the controller not talk directly to MongoDB?

The controller should not talk directly to MongoDB for several important reasons:

- **Separation of concerns:** The controller's job is to handle HTTP requests and responses (routing, status codes, request validation). Database access is a completely different responsibility that belongs to the repository/service layers.
- **Testability:** A controller that depends on `TicketService` can be easily unit-tested by mocking the service. A controller that directly uses `TicketRepository` or MongoDB commands is much harder to test in isolation.
- **Business logic centralisation:** The service layer (`TicketService`) contains the business rules — such as setting default status, converting between `Ticket` and `TicketResponse`, and validation. If the controller talked directly to MongoDB, this logic would be scattered across multiple controllers.
- **Maintainability:** If the storage mechanism changes (e.g., switching from MongoDB to PostgreSQL), only the repository layer needs to change. The controller remains untouched as long as the service interface stays the same.
- **Consistent pattern:** Following the layered architecture (Controller → Service → Repository → Database) keeps the codebase organised, predictable, and easy for other developers to understand.

In this project, the flow is: `TicketController` → `TicketService` → `TicketRepository` → MongoDB. Each layer has a single, clear responsibility.

---