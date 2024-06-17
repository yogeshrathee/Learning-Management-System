# Learning Management System (LMS) API

This project implements a RESTful API service using Spring Boot to manage the exam enrollment process for a Learning Management System (LMS). The API uses MySQL to persist data related to students, subjects, and exams.

## Table of Contents

- [Features](#features)
- [Dependencies](#dependencies)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)

---

## Features

- CRUD operations for Students, Subjects, and Exams.
- Registration of students for exams after enrolling in corresponding subjects.
- Exception handling with GlobalExceptionHandler.
- Unit tests using JUnit, Mockito, and MockMvc.
- Hidden feature: Numbers API integration for random number facts.

---

## Dependencies

The project uses the following dependencies:

- **Spring Boot Starter Web**: For building RESTful APIs.
- **Spring Data JPA**: For data access and database interactions.
- **MySQL Connector/J**: For connecting to MySQL database.
- **Spring Boot Starter Test**: For unit testing with JUnit and Mockito.
- **Numbers API**: For generating random number facts (hidden feature).

---

## Getting Started

### Prerequisites

Before running this project, ensure you have the following installed:

- Java JDK (11 or higher)
- Apache Maven
- MySQL Server
- Git (optional, for cloning repository)

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/lms-api.git
   cd lms-api
2. Build the project using Maven:

   ```bash
   mvn clean install

3. API Endpoints:

       ```bash
      ***Students***

        GET /students
        Retrieve all students.
  
        GET /students/{id}
        Retrieve student by ID.
  
        POST /students
        Create a new student.
        
        PUT /students/{id}
        Update an existing student.
        
        DELETE /students/{id}
        Delete a student.
        
   ***Subjects***
   
        GET /subjects
        Retrieve all subjects.
        
        GET /subjects/{id}
        Retrieve subject by ID.
        
        POST /subjects
        Create a new subject.
        
        PUT /subjects/{id}
        Update an existing subject.
        
        DELETE /subjects/{id}
        Delete a subject.

   
   ***Exams***
   
        GET /exams
        Retrieve all exams.
        
        GET /exams/{id}
        Retrieve exam by ID.
        
        POST /exams/{id}
        Register a student for a specific exam.
       
