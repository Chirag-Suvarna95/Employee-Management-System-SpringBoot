package com.project.EmployeeManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.EmployeeManagement.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
