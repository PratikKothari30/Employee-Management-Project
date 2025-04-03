package com.pratik.java_project;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAO();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Update Employee");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter Department: ");
                    String department = scanner.next();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();
                    employeeDAO.addEmployee(name, age, department, salary);
                    break;
                    
                case 2:
                    List<Employee> employees = employeeDAO.getAllEmployees();
                    if (employees.isEmpty()) {
                        System.out.println("No employees found. The list is empty.");
                    } else {
                        System.out.println("Employee List:");
                        for (Employee emp : employees) {
                            System.out.println(emp.getId() + " | " + emp.getName() + " | " +
                                               emp.getAge() + " | " + emp.getDepartment() + " | " + emp.getSalary());
                        }
                    }
                    break;
            
                case 3:
                    List<Employee> employeesForUpdate = employeeDAO.getAllEmployees();
                    if (employeesForUpdate.isEmpty()) {
                        System.out.println("No employees found. Cannot update.");
                    } else {
                        System.out.println("Employee List:");
                        for (Employee emp : employeesForUpdate) {
                            System.out.println(emp.getId() + " | " + emp.getName() + " | " +
                                               emp.getAge() + " | " + emp.getDepartment() + " | " + emp.getSalary());
                        }

                        System.out.print("Enter Employee ID to Update: ");
                        int updateId = scanner.nextInt();
                        scanner.nextLine();  // Consume the newline

                        Employee existingEmployee = employeeDAO.getEmployeeById(updateId);
                        if (existingEmployee == null) {
                            System.out.println("Employee with ID " + updateId + " not found.");
                            break;
                        }

                        System.out.print("Enter New Name (Press Enter to keep [" + existingEmployee.getName() + "]): ");
                        String newName = scanner.nextLine().trim();
                        if (newName.isEmpty()) newName = existingEmployee.getName();

                        System.out.print("Enter New Age (Enter -1 to keep [" + existingEmployee.getAge() + "]): ");
                        int newAge = scanner.nextInt();
                        if (newAge == -1) newAge = existingEmployee.getAge();

                        scanner.nextLine(); // Consume newline
                        System.out.print("Enter New Department (Press Enter to keep [" + existingEmployee.getDepartment() + "]): ");
                        String newDept = scanner.nextLine().trim();
                        if (newDept.isEmpty()) newDept = existingEmployee.getDepartment();

                        System.out.print("Enter New Salary (Enter -1 to keep [" + existingEmployee.getSalary() + "]): ");
                        double newSalary = scanner.nextDouble();
                        if (newSalary == -1) newSalary = existingEmployee.getSalary();

                        employeeDAO.updateEmployee(updateId, newName, newAge, newDept, newSalary);
                    }
                    break;

                    
                case 4:
                    List<Employee> employeesForDelete = employeeDAO.getAllEmployees();
                    if (employeesForDelete.isEmpty()) {
                        System.out.println("No employees found. Cannot delete.");
                    } else {
                        System.out.println("Employee List:");
                        for (Employee emp : employeesForDelete) {
                            System.out.println(emp.getId() + " | " + emp.getName() + " | " +
                                               emp.getAge() + " | " + emp.getDepartment() + " | " + emp.getSalary());
                        }
                        
                        System.out.print("Enter Employee ID to Delete: ");
                        int deleteId = scanner.nextInt();
                        employeeDAO.deleteEmployee(deleteId);
                    }
                    break;

                    
                case 5:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                    
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
