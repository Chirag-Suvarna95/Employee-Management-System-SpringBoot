package com.project.EmployeeManagement.services;

import com.project.EmployeeManagement.entities.Employee;
import com.project.EmployeeManagement.dao.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee addEmployee(Employee employee) {
        logger.info("Adding employee: {}", employee.getEmail() );
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Employee employee) {
        logger.info("Updating employee with id: {}", employee.getId());
        return employeeRepository.save(employee);
    }

    public void deleteEmployeeById(Long employeeId) {
        logger.info("Deleting employee with id: {}", employeeId);
        employeeRepository.deleteById(employeeId);
    }

   
}

