# Courses Management System

A Spring Boot REST API for managing courses, students, teachers, and enrollments with JWT authentication and role-based authorization.

## Features

- **User Management**: Student and teacher registration/authentication
- **Course Management**: Create, read, and delete courses
- **Enrollment System**: Enroll/unenroll students in courses
- **Grade Management**: Update student grades for courses
- **Role-Based Access Control**: Admin, student, and teacher roles
- **JWT Authentication**: Secure API endpoints
- **Student Profiles**: Track enrolled courses per student

## Tech Stack

- **Java 21**
- **Spring Boot 3.5.5**
- **Spring Security** with JWT
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**
- **Bean Validation**

## Prerequisites

- Java 21+
- PostgreSQL
- Maven 3.6+

## Setup

1. **Clone the repository**
bash
git clone <https://github.com/A-h-m-e-d004/courses_system.git>
cd courses_system

## Configure PostgreSQL

Create database: courses_system_DB

Update credentials in application.properties

## API Endpoints
## Authentication
POST /auth/signup/student - Register student

POST /auth/signup/teacher - Register teacher

POST /auth/login/student - Student login

POST /auth/login/teacher - Teacher login

## Courses
GET /course - Get all courses

POST /course - Create course (Admin only)

DELETE /course/{id} - Delete course (Admin only)

GET /course/{title} - Get course by title

## Students
GET /student - Get all students

DELETE /student/{id} - Delete student

GET /student/profile/{id} - Get student profile

## Enrollment
POST /enroll - Enroll student in course

DELETE /enroll - Unenroll student from course

GET /enroll/{student_id} - Get student's enrolled courses

PUT /updateGrade - Update student grade (Admin only)

## Roles
POST /role - Create role (Admin only)

GET /role - Get all roles (Admin only)

POST /role/{roleTitle}/student/{studentId} - Assign role to student (Admin only)

POST /role/{roleTitle}/teacher/{teacherId} - Assign role to teacher (Admin only)

## Authentication
Include JWT token in Authorization header:

Authorization: bearer <your-jwt-token>

## Database Schema
Student: id, name, email, password

Teacher: id, name, email, password

Course: id, title

Enrollment: id, student_id, course_id, grade, enrolled_at

Role: id, title

StudentProfile: id, name, courses, student_id

## Default Behavior
Teachers automatically get ADMIN role upon registration

Student profiles are created automatically upon registration

JWT tokens expire after 24 hours
