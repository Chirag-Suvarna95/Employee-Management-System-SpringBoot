package com.project.EmployeeManagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.EmployeeManagement.entities.Employee;
import com.project.EmployeeManagement.services.EmployeeServiceImpl;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeServiceImpl employeeServiceImpl;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees()
	{
		return this.employeeServiceImpl.getAllEmployees();
	}
	
	

}
