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