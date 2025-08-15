package com.project.EmployeeManagement.controller;

import com.project.EmployeeManagement.entities.Employee;
import com.project.EmployeeManagement.services.EmployeeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private EmployeeServiceImpl employeeService;

	@Test
	@WithMockUser(roles = { "ADMIN" })
	void getAllEmployees() throws Exception {
		Employee emp = new Employee();
		emp.setId(1L);
		emp.setFirstName("Admin");
		emp.setLastName("1");
		emp.setEmail("HelloThere.IAmYourBoss@GetBackToWork.com");

		when(employeeService.getAllEmployees()).thenReturn(Arrays.asList(emp));

		mockMvc.perform(get("/employees").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$[0].email").value("HelloThere.IAmYourBoss@GetBackToWork.com"));
	}
}
