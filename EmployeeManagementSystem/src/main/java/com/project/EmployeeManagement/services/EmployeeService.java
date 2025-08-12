package com.project.EmployeeManagement.services;

import com.project.EmployeeManagement.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployeeById(Long employeeId);

    Optional<Employee> getEmployeeById(Long employeeId);

    List<Employee> getAllEmployees();

    List<Employee> findEmployeesByDepartment(String department);

    List<Employee> findEmployeesByJobTitle(String jobTitle);

    List<Employee> searchEmployeesByName(String name);
}
