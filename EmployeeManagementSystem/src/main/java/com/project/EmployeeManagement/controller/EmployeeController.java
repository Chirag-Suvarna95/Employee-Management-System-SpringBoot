package com.project.EmployeeManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.EmployeeManagement.entities.Employee;
import com.project.EmployeeManagement.services.EmployeeServiceImpl;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	private final EmployeeServiceImpl employeeServiceImpl;

	// no need for autowired since only one constructor
	public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
		this.employeeServiceImpl = employeeServiceImpl;
	}

	// Get all employees
	@GetMapping
	public ResponseEntity<List<Employee>> getAllEmployees() {
		return ResponseEntity.ok(employeeServiceImpl.getAllEmployees());
	}

	// Get employee by id
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		return employeeServiceImpl.getEmployeeById(id).map(ResponseEntity::ok)
				.orElseGet(() -> ResponseEntity.notFound().build());
	}

	// Add a new employee
	@PostMapping
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) {
		Employee savedEmp = employeeServiceImpl.addEmployee(emp);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedEmp);

	}

	// Update existing employee
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee emp) {
		if (employeeServiceImpl.getEmployeeById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		emp.setId(id); //updated fields are sent through front end
		Employee updatedEmp = employeeServiceImpl.updateEmployee(emp);
		return ResponseEntity.ok(updatedEmp);
	}

	// Delete employee
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
		if (employeeServiceImpl.getEmployeeById(id).isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		employeeServiceImpl.deleteEmployeeById(id);
		return ResponseEntity.noContent().build();
	}

	// Search employees by department
	@GetMapping("/department/{department}")
	public ResponseEntity<List<Employee>> getByDepartment(@PathVariable String department) {
		return ResponseEntity.ok(employeeServiceImpl.findEmployeesByDepartment(department));
	}

	// Search employees by job title
	@GetMapping("/job/{jobTitle}")
	public ResponseEntity<List<Employee>> getByJobTitle(@PathVariable String jobTitle) {
		return ResponseEntity.ok(employeeServiceImpl.findEmployeesByJobTitle(jobTitle));
	}

	// Search by first or last name (partial, case-insensitive)
	@GetMapping("/search")
	public ResponseEntity<List<Employee>> searchByName(@RequestParam String name) {
		return ResponseEntity.ok(employeeServiceImpl.searchEmployeesByName(name));
	}
}
