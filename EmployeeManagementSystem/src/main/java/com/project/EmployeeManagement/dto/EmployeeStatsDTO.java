package com.project.EmployeeManagement.dto;

public class EmployeeStatsDTO {
    private String department;
    private long employeeCount;
    private double averageSalary;

    public EmployeeStatsDTO(String department, long employeeCount, double averageSalary) {
        this.department = department;
        this.employeeCount = employeeCount;
        this.averageSalary = averageSalary;
    }

    public String getDepartment() {
        return department;
    }

    public long getEmployeeCount() {
        return employeeCount;
    }

    public double getAverageSalary() {
        return averageSalary;
    }
}
