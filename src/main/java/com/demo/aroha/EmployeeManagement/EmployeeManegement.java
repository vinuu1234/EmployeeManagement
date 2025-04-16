package com.demo.aroha.EmployeeManagement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class EmployeeManegement {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Employee e1 = new Employee(101, "Vinod", "IT", "vinod@gmail.com", 100000);

		HashMap<Integer, Employee> empHashMap = new HashMap<>();
		System.out.println("* * * menu * * *");
		System.out.println("\n1. Add Employee");
		System.out.println("2. View All Employees");
		System.out.println("3. View by Employee ID");
		System.out.println("4. View by Email");
		System.out.println("5. Delete by Employee ID");
		System.out.println("6. Exit");

		while (true) {

			System.out.println("Enter Your choice :");
			int choice = sc.nextInt();

			switch (choice) {
			case 1: {

				System.out.println("Adding the Record ");
				System.out.println("Enter eid :");
				int eid = sc.nextInt();
				sc.nextLine();

				System.out.println("Enter the Name :");
				String name = sc.nextLine();

				System.out.println("Enter the Department :");
				String dept = sc.nextLine();

				System.out.println("Enter the EmailId");
				String emailId = sc.nextLine();
				while (true) {
					System.out.println("Enter Salary :");
					double sal = sc.nextDouble();

					if (sal >= 1500 && sal < 175000) {
						Employee e1 = new Employee(eid, name, dept, emailId, sal);

						empHashMap.put(e1.getEmpId(), e1);
						System.out.println("Employee added Successfully");
						break;
					} else {
						System.out.println("Salary Not in the range");
					}
				}
				// System.out.println(empHashMap);

			}
			case 2: {
				for (Entry<Integer, Employee> entry : empHashMap.entrySet()) {

					System.out.println(entry.getValue());

				}
				break;
			}
			case 3: {
				System.out.println("Enter the empid to be searched :");
				int searchId = sc.nextInt();
				sc.nextLine();
				if (empHashMap.containsKey(searchId)) {
					System.out.println(empHashMap.get(searchId));
				} else {
					System.out.println("Employee not found.");
				}
				break;
				// System.out.println(entry.getValue());

			}
			case 4: {
				System.out.println("Enter the emailid to be searched :");
				String email = sc.next();
				boolean found = false;
				for (Employee emp : empHashMap.values()) {

					if (emp.getEmailId().equalsIgnoreCase(email)) {
						System.out.println(emp);
						found = true;
						break;
					}
				}
				if (!found) {
					System.out.println("Employee with this email not found.");
				}
				break;

			}

			case 5:
				System.out.print("Enter Employee ID to delete: ");
				int delId = sc.nextInt();
				if (empHashMap.containsKey(delId)) {
					empHashMap.remove(delId);
					System.out.println("Employee deleted.");
				} else {
					System.out.println("Employee not found.");
				}
				break;

			case 6: {
				System.out.println("Exiting...");
				saveEmployee(empHashMap);

				break;
			}

			default:
				System.out.println("Invalid choice. Try again.");
			}
		}
	}

	public static void saveEmployee(HashMap<Integer, Employee> empHashMap) {

		String DB_URL = "jdbc:mysql://localhost:3306/employeedb";
		String DB_USER = "root";
		String DB_PASSWORD = "root";

		String insertSQL = "INSERT INTO employee (empid, name, dept, email, salary) VALUES (?, ?, ?, ?, ?)";

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				PreparedStatement ps = conn.prepareStatement(insertSQL)) {

			for (Employee emp : empHashMap.values()) {
				ps.setInt(1, emp.getEmpId());
				ps.setString(2, emp.getName());
				ps.setString(3, emp.getDept());
				ps.setString(4, emp.getEmailId());
				ps.setDouble(5, emp.getSalary());
				ps.addBatch(); // Add to batch for efficient insert
			}

			int[] result = ps.executeBatch(); // Execute all at once
			System.out.println("Inserted " + result.length + " employees into DB.");

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
