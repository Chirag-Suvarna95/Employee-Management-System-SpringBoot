package com.project.EmployeeManagement.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.EmployeeManagement.entities.Employee;
import com.project.EmployeeManagement.dto.EmployeeStatsDTO;
import com.project.EmployeeManagement.services.EmployeeServiceImpl;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeServiceImpl employeeServiceImpl;

    public EmployeeController(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    // Get all employees
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return ResponseEntity.ok(employeeServiceImpl.getAllEmployees());
    }


    // Search by first or last name - MOVED UP
    @GetMapping("/search")
    public ResponseEntity<List<Employee>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(employeeServiceImpl.searchEmployeesByName(name));
    }

    // Search employees by department - MOVED UP
    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>> getByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(employeeServiceImpl.findEmployeesByDepartment(department));
    }

    // Search employees by job title - MOVED UP  
    @GetMapping("/job/{jobTitle}")
    public ResponseEntity<List<Employee>> getByJobTitle(@PathVariable String jobTitle) {
        return ResponseEntity.ok(employeeServiceImpl.findEmployeesByJobTitle(jobTitle));
    }

    // Reports endpoints 
    @GetMapping("/reports/department-stats")
    public ResponseEntity<List<EmployeeStatsDTO>> getDepartmentStats() {
        return ResponseEntity.ok(employeeServiceImpl.getDepartmentWiseStats());
    }

    @GetMapping("/reports/total")
    public ResponseEntity<Long> getTotalEmployees() {
        return ResponseEntity.ok(employeeServiceImpl.getTotalEmployees());
    }

    @GetMapping("/reports/highest-salary")
    public ResponseEntity<List<Employee>> getHighestSalaryEmployees() {
        return ResponseEntity.ok(employeeServiceImpl.getHighestPaidEmployees());
    }
    
    @GetMapping("/reports/lowest-salary")
    public ResponseEntity<List<Employee>> getLowestPaidEmployee() {
        return ResponseEntity.ok(employeeServiceImpl.getLowestPaidEmployees());
    }
    
    @GetMapping("/reports/higher-than/{amount}")
    public ResponseEntity<List<Employee>> getEmpsWithSalHigherThan(@PathVariable double amount) {
        return ResponseEntity.ok(employeeServiceImpl.getEmpsWithSalHigherThan(amount));
    }
    
    @GetMapping("/reports/lower-than/{amount}")
    public ResponseEntity<List<Employee>> getEmpsWithSalLowerThan(@PathVariable double amount) {
        return ResponseEntity.ok(employeeServiceImpl.getEmpsWithSalLowerThan(amount));
    }

    // *** GENERIC {id} ROUTE COMES LAST ***

    // Get employee by id - MOVED TO END
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Employee employee = employeeServiceImpl.getEmployeeById(id);
        return ResponseEntity.ok(employee);
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
        emp.setId(id);
        Employee updatedEmp = employeeServiceImpl.updateEmployee(emp);
        return ResponseEntity.ok(updatedEmp);
    }

    // Delete employee 
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        employeeServiceImpl.deleteEmployeeById(id);
        return ResponseEntity.noContent().build();
    }
}
