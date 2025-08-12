package com.project.EmployeeManagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.EmployeeManagement.entities.Employee;

/*Repository interface for Employee, this Inherits CRUD operations from JpaRepository*/
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
	//Custom queries : Search and filter employees by department, job title, etc.
	
	//Returns a list of Employees, with the department in the query
	List<Employee> findByDepartmentIgnoreCase(String department);

    //Find by job title
    List<Employee> findByJobTitleIgnoreCase(String jobTitle);

    //Search employees by first or last name
    @Query("SELECT e FROM Employee e WHERE LOWER(e.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR LOWER(e.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Employee> findByFirstOrLastName(@Param("name") String name);

}
