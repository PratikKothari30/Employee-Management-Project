# Employee-Management-Project
This project is a Java-based Employee Management System that uses JDBC (Java Database Connectivity) to interact with a MySQL database. It allows users to add, view, update, and delete employee records through a console-based interface.

# Why do we use JDBC here?
Database Connectivity: JDBC allows the Java program to communicate with the MySQL database.

SQL Queries Execution: We need JDBC to execute INSERT, SELECT, UPDATE, and DELETE queries in the database.

Retrieve and Manipulate Data: We fetch employee records from MySQL, display them in Java, and modify them when required.

Persistence: Without a database, data would be lost when the program exits. JDBC ensures data is permanently stored.

# Why did we create a Maven project? 
Dependency Management: We need external libraries (like MySQL JDBC Driver). Maven automatically downloads and manages them.

Project Structure: Maven provides a standard project layout, making it easier to maintain. Build Automation: Instead of manually compiling and running, Maven automates these tasks (mvn clean install).

# This project is a great practice for:
1. JDBC (Database Connectivity)
2. CRUD Operations in MySQL
3. Java Programming
4. OOP Concepts (Encapsulation, DAO Pattern, etc.)
