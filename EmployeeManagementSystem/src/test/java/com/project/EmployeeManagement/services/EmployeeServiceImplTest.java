package com.project.EmployeeManagement.services;

import com.project.EmployeeManagement.dao.EmployeeRepository;
import com.project.EmployeeManagement.entities.Employee;
import com.project.EmployeeManagement.exceptions.EmailAlreadyExistsException;
import com.project.EmployeeManagement.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void addEmployee_Success() {
        // Given
        when(employeeRepository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.empty());
        when(employeeRepository.save(any(Employee.class))).thenReturn(testEmployee);

        // When
        Employee result = employeeService.addEmployee(testEmployee);

        // Then
        assertNotNull(result);
        assertEquals(testEmployee.getEmail(), result.getEmail());
        verify(employeeRepository).findByEmailIgnoreCase(testEmployee.getEmail());
        verify(employeeRepository).save(testEmployee);
    }

    @Test
    void addEmployee_EmailAlreadyExists_ThrowsException() {
        // Given
        when(employeeRepository.findByEmailIgnoreCase(anyString())).thenReturn(Optional.of(testEmployee));

        // When & Then
        assertThrows(EmailAlreadyExistsException.class, 
            () -> employeeService.addEmployee(testEmployee));
        verify(employeeRepository).findByEmailIgnoreCase(testEmployee.getEmail());
        verify(employeeRepository, never()).save(any());
    }

    @Test
    void getEmployeeById_Success() {
        // Given
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(testEmployee));

        // When
        Employee result = employeeService.getEmployeeById(1L);

        // Then
        assertNotNull(result);
        assertEquals(testEmployee.getId(), result.getId());
        verify(employeeRepository).findById(1L);
    }

    @Test
    void getEmployeeById_NotFound_ThrowsException() {
        // Given
        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ResourceNotFoundException.class, 
            () -> employeeService.getEmployeeById(999L));
        verify(employeeRepository).findById(999L);
    }

    @Test
    void getAllEmployees_Success() {
        // Given
        List<Employee> employees = Arrays.asList(testEmployee);
        when(employeeRepository.findAll()).thenReturn(employees);

        // When
        List<Employee> result = employeeService.getAllEmployees();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        verify(employeeRepository).findAll();
    }

    @Test
    void deleteEmployeeById_Success() {
        // Given
        when(employeeRepository.existsById(anyLong())).thenReturn(true);

        // When
        employeeService.deleteEmployeeById(1L);

        // Then
        verify(employeeRepository).existsById(1L);
        verify(employeeRepository).deleteById(1L);
    }

    @Test
    void deleteEmployeeById_NotFound_ThrowsException() {
        // Given
        when(employeeRepository.existsById(anyLong())).thenReturn(false);

        // When & Then
        assertThrows(ResourceNotFoundException.class, 
            () -> employeeService.deleteEmployeeById(999L));
        verify(employeeRepository).existsById(999L);
        verify(employeeRepository, never()).deleteById(any());
    }
   
}
