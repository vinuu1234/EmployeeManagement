package com.demo.aroha.EmployeeManagement;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class Employee {

	private int empId;
	private String Name;
	private String dept;
	private String emailId;
	private double salary;

	public Employee(int empId, String name, String dept, String emailId, double salary) {
		super();
		this.empId = empId;
		Name = name;
		this.dept = dept;
		this.emailId = emailId;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", Name=" + Name + ", dept=" + dept + ", emailId=" + emailId + ", salary="
				+ salary + "]";
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

		
}
