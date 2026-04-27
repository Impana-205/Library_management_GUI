# Library_management_GUI

# 📚 Library Management System

## 📌 Project Description

The Library Management System is a desktop-based application developed using **Java Swing**, **JDBC**, and **MySQL**. It helps in managing library operations such as adding books, viewing available books, and issuing books to students.

This project demonstrates the integration of a graphical user interface with a database using JDBC.

---

## 🛠️ Technologies Used

* Java (Swing for GUI)
* JDBC (Java Database Connectivity)
* MySQL Database
* Eclipse IDE / IntelliJ IDEA

---

## ✨ Features

* ➕ Add new books to the library
* 📖 View all available books
* 📤 Issue books to students
* 🔄 Automatically updates book quantity
* 💻 Simple and user-friendly interface

---

## 🗂️ Project Structure

LibraryManagementSystem/
│
├── src/
│   ├── DBConnection.java
│   └── LibraryManagement.java
│
├── lib/
│   └── mysql-connector-j-8.x.x.jar
│
├── sql/
│   └── database.sql
│
└── README.md

---

## 🗄️ Database Setup

Run the following SQL queries in MySQL:

```sql
CREATE DATABASE library_db;

USE library_db;

CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    author VARCHAR(100),
    quantity INT
);

CREATE TABLE issued_books (
    issue_id INT PRIMARY KEY AUTO_INCREMENT,
    book_id INT,
    student_name VARCHAR(100),
    issue_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

---

## ▶️ How to Run the Project

1. Clone or download the project
2. Open the project in Eclipse / IntelliJ
3. Add MySQL Connector JAR file to the project
4. Update database username and password in `DBConnection.java`
5. Run the SQL script to create database and tables
6. Run `LibraryManagement.java`

---

## ⚠️ Important Notes

* Ensure MySQL server is running
* Ensure correct database credentials
* Add MySQL connector JAR properly to avoid `ClassNotFoundException`

---

## 🧠 Architecture

This project follows a **2-tier architecture**:

* Presentation Layer (Java Swing GUI)
* Database Layer (MySQL using JDBC)

---

## 🚀 Future Enhancements

* 🔐 User Login System
* 🔁 Return Book Feature
* 🔍 Search Functionality
* 📊 Reports and Dashboard
* 🎨 Improved UI Design

---

## 👩‍💻 Author

* Name: Impana R
* Course: Anudip Assignment

---

## 📄 License

This project is for educational purposes only.
