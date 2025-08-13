package com.project.EmployeeManagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.EmployeeManagement.entities.Employee;
import com.project.EmployeeManagement.services.EmployeeServiceImpl;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;

	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		return this.employeeServiceImpl.getAllEmployees();
	}

	@GetMapping("/employee/{s_id}")
	public Optional<Employee> getEmployeeById(@PathVariable("s_id") Long id) {
		return this.employeeServiceImpl.getEmployeeById(id);
	}

	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee emp) {
		return employeeServiceImpl.addEmployee(emp);
	}

}
