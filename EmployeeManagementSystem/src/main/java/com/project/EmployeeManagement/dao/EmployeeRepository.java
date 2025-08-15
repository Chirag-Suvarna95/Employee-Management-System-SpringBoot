package com.project.EmployeeManagement.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.EmployeeManagement.dto.EmployeeStatsDTO;
import com.project.EmployeeManagement.entities.Employee;

/*Repository interface for Employee, this Inherits CRUD operations from JpaRepository*/
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	// Custom queries : Search and filter employees by department, job title, etc.

	// Returns a list of Employees, with the department in the query
	List<Employee> findByDepartmentIgnoreCase(String department);

	// Find by job title
	List<Employee> findByJobTitleIgnoreCase(String jobTitle);

	// Search employees by first or last name
	List<Employee> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);

	
	@Query("SELECT new com.project.EmployeeManagement.dto.EmployeeStatsDTO(e.department, COUNT(e), AVG(e.salary)) "
			+ "FROM Employee e GROUP BY e.department")
	List<EmployeeStatsDTO> getDepartmentWiseStats();

	@Query("SELECT COUNT(e) FROM Employee e")
	long getTotalEmployees();

	@Query("SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(e2.salary) FROM Employee e2)")
	List<Employee> getHighestPaidEmployees();
	
	Optional<Employee> findByEmailIgnoreCase(String email);
	
	@Query("SELECT e from Employee e where e.salary =(select min(e2.salary) from Employee e2)")
	List<Employee> getLowestPaidEmployee();

}
