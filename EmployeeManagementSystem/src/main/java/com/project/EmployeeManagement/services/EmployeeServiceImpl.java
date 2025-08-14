package com.project.EmployeeManagement.services;

import com.project.EmployeeManagement.entities.Employee;
import com.project.EmployeeManagement.dao.EmployeeRepository;
import com.project.EmployeeManagement.dto.EmployeeStatsDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	private final EmployeeRepository employeeRepository;

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee addEmployee(Employee employee) {
		logger.info("Adding new employee: {}", employee.getEmail());
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(Employee employee) {
		logger.info("Updating employee with id: {}", employee.getId());
		return employeeRepository.save(employee);
	}

	@Override
	public void deleteEmployeeById(Long employeeId) {
		logger.info("Deleting employee with id: {}", employeeId);
		employeeRepository.deleteById(employeeId);
	}

	@Override
	public Optional<Employee> getEmployeeById(Long employeeId) {
		logger.info("Fetching employee with id: {}", employeeId);
		return employeeRepository.findById(employeeId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		logger.info("Fetching all employees");
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> findEmployeesByDepartment(String department) {
		logger.info("Finding employees by department: {}", department);
		return employeeRepository.findByDepartmentIgnoreCase(department);
	}

	@Override
	public List<Employee> findEmployeesByJobTitle(String jobTitle) {
		logger.info("Finding employees by job title: {}", jobTitle);
		return employeeRepository.findByJobTitleIgnoreCase(jobTitle);
	}

	@Override
	public List<Employee> searchEmployeesByName(String name) {
		logger.info("Searching employees by name: {}", name);
		return employeeRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(name, name);
	}

	@Override
	public List<EmployeeStatsDTO> getDepartmentWiseStats() {
		return employeeRepository.getDepartmentWiseStats();
	}

	@Override
	public long getTotalEmployees() {
		return employeeRepository.getTotalEmployees();
	}

	@Override
	public List<Employee> getHighestPaidEmployees() {
		return employeeRepository.getHighestPaidEmployees();
	}

}
