package com.project.EmployeeManagement.services;

import com.project.EmployeeManagement.dto.EmployeeStatsDTO;
import com.project.EmployeeManagement.entities.Employee;

import java.util.List;

public interface EmployeeService {

    Employee addEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    void deleteEmployeeById(Long employeeId);

    Employee getEmployeeById(Long employeeId);

    List<Employee> getAllEmployees();

    List<Employee> findEmployeesByDepartment(String department);

    List<Employee> findEmployeesByJobTitle(String jobTitle);

    List<Employee> searchEmployeesByName(String name);
    
    //Methods for Stats
    List<EmployeeStatsDTO> getDepartmentWiseStats();
    
    long getTotalEmployees();
    
    List<Employee> getHighestPaidEmployees();

}
