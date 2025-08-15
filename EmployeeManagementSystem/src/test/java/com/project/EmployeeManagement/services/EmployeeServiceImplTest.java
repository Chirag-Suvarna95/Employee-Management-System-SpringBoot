package com.project.EmployeeManagement.services;

import com.project.EmployeeManagement.dao.EmployeeRepository;
import com.project.EmployeeManagement.dto.EmployeeStatsDTO;
import com.project.EmployeeManagement.entities.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

	@Mock
	private EmployeeRepository employeeRepository;

	@InjectMocks
	private EmployeeServiceImpl employeeService;

	private Employee testEmployee;

	@BeforeEach
	void setUp() {
		testEmployee = new Employee();
		testEmployee.setId(1L);
		testEmployee.setFirstName("Ethan");
		testEmployee.setLastName("Hunt");
		testEmployee.setEmail("impossible.mission@imf.com");
		testEmployee.setDepartment("IMF");
		testEmployee.setJobTitle("Spy");
		testEmployee.setSalary(1000000.0);
	}

	@Test
	void addEmployee() {
		when(employeeRepository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.empty());
		when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);

		Employee result = employeeService.addEmployee(testEmployee);

		assertNotNull(result);
		assertEquals(testEmployee.getEmail(), result.getEmail());
		verify(employeeRepository).findByEmailIgnoreCase(testEmployee.getEmail());
		verify(employeeRepository).save(testEmployee);
	}

	@Test
	void getEmployeeById() {
		when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(testEmployee));

		Employee result = employeeService.getEmployeeById(1L);

		assertNotNull(result);
		assertEquals(testEmployee.getId(), result.getId());
		verify(employeeRepository).findById(1L);
	}

	@Test
	void getAllEmployees() {
		List<Employee> employees = Arrays.asList(testEmployee);
		when(employeeRepository.findAll()).thenReturn(employees);

		List<Employee> result = employeeService.getAllEmployees();

		assertNotNull(result);
		assertEquals(1, result.size());
		verify(employeeRepository).findAll();
	}

	@Test
	void deleteEmployeeById() {
		when(employeeRepository.existsById(anyLong())).thenReturn(true);

		employeeService.deleteEmployeeById(1L);

		verify(employeeRepository).existsById(1L);
		verify(employeeRepository).deleteById(1L);
	}

	// -------Tests for Stats----------------

	@Test
	void getDepartmentWiseStats() {
		List<EmployeeStatsDTO> stats = new ArrayList<>();
		stats.add(new EmployeeStatsDTO("IMF", 5L, 1000000.0));
		stats.add(new EmployeeStatsDTO("MI6", 3L, 900000.0));

		when(employeeRepository.getDepartmentWiseStats()).thenReturn(stats);

		List<EmployeeStatsDTO> result = employeeService.getDepartmentWiseStats();

		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals("IMF", result.get(0).getDepartment());
		assertEquals(5L, result.get(0).getEmployeeCount());
		assertEquals(1000000.0, result.get(0).getAverageSalary());

		verify(employeeRepository).getDepartmentWiseStats();
	}

	@Test
	void getTotalEmployees() {
		when(employeeRepository.getTotalEmployees()).thenReturn(8L);

		long total = employeeService.getTotalEmployees();

		assertEquals(8L, total);
		verify(employeeRepository).getTotalEmployees();
	}

	@Test
	void getHighestPaidEmployees() {
		List<Employee> highestPaid = new ArrayList<>();
		highestPaid.add(testEmployee);

		when(employeeRepository.getHighestPaidEmployees()).thenReturn(highestPaid);
		List<Employee> result = employeeService.getHighestPaidEmployees();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(testEmployee.getEmail(), result.get(0).getEmail());

		verify(employeeRepository).getHighestPaidEmployees();
	}

}
