package com.project.EmployeeManagement.controller;

import com.project.EmployeeManagement.entities.Employee;
import com.project.EmployeeManagement.exceptions.ResourceNotFoundException;
import com.project.EmployeeManagement.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

@WebMvcTest(EmployeeController.class)
@MockBean(JpaMetamodelMappingContext.class)
class EmployeeControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl employeeService;

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getAllEmployees() throws Exception {
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setFirstName("John");
        emp.setLastName("Doe");
        emp.setEmail("john@test.com");

        when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(emp));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("John"));
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void createEmployee() throws Exception {
        Employee emp = new Employee();
        emp.setId(1L);
        emp.setFirstName("New");
        emp.setLastName("Employee");

        when(employeeService.addEmployee(any(Employee.class))).thenReturn(emp);

        mockMvc.perform(post("/employees")
                .with(csrf())
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"firstName\":\"New\",\"lastName\":\"Employee\",\"email\":\"new@test.com\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void deleteEmployee() throws Exception {
        doNothing().when(employeeService).deleteEmployeeById(1L);

        mockMvc.perform(delete("/employees/1")
                .with(csrf()))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(roles = {"ADMIN"})
    void getEmployeeById_notFound() throws Exception {
        when(employeeService.getEmployeeById(1000L))
                .thenThrow(new ResourceNotFoundException("Employee not found"));

        mockMvc.perform(get("/employees/1000"))
                .andExpect(status().isNotFound());
    }


    @Test
    void getAllEmployees_withoutAuth() throws Exception {
        mockMvc.perform(get("/employees"))
                .andExpect(status().isUnauthorized());
    }
}
