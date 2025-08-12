package com.project.EmployeeManagement.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private double salary;
	private String department_name;
	private String job_title;
	
	
	public Employee() {
		super();
	}
	
	public Employee(String name, double salary, String department_name, String job_title) {
		super();
		this.name = name;
		this.salary = salary;
		this.department_name = department_name;
		this.job_title = job_title;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getDepartment_name() {
		return department_name;
	}
	public void setDepartment_name(String department_name) {
		this.department_name = department_name;
	}
	public String getJob_title() {
		return job_title;
	}
	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}
	
	@Override
	public String toString() {
		return "Employee [ID=" + id + ", Name=" + name + ", Salary=" + salary + ", Department_name=" + department_name
				+ ", Job_title=" + job_title + "]";
	}
}
