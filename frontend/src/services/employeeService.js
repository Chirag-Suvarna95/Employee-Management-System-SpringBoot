import axios from 'axios';
import authService from './authService';

const API_BASE_URL = 'http://localhost:8080';

const employeeService = {
  // Basic CRUD operations
  getAllEmployees: () => {
    return axios.get(`${API_BASE_URL}/employees`, {
      headers: {
        ...authService.getAuthHeader(),
        'Content-Type': 'application/json'
      }
    });
  },

  getEmployeeById: (id) => {
    return axios.get(`${API_BASE_URL}/employees/${id}`, {
      headers: authService.getAuthHeader()
    });
  },

  createEmployee: (employee) => {
    return axios.post(`${API_BASE_URL}/employees`, employee, {
      headers: {
        ...authService.getAuthHeader(),
        'Content-Type': 'application/json'
      }
    });
  },

  updateEmployee: (id, employee) => {
    return axios.put(`${API_BASE_URL}/employees/${id}`, employee, {
      headers: {
        ...authService.getAuthHeader(),
        'Content-Type': 'application/json'
      }
    });
  },

  deleteEmployee: (id) => {
    return axios.delete(`${API_BASE_URL}/employees/${id}`, {
      headers: authService.getAuthHeader()
    });
  },

  // Search operations
  searchEmployees: (name) => {
    return axios.get(`${API_BASE_URL}/employees/search?name=${name}`, {
      headers: authService.getAuthHeader()
    });
  },

  getEmployeesByDepartment: (department) => {
    return axios.get(`${API_BASE_URL}/employees/department/${department}`, {
      headers: authService.getAuthHeader()
    });
  },

  getEmployeesByJobTitle: (jobTitle) => {
    return axios.get(`${API_BASE_URL}/employees/job/${jobTitle}`, {
      headers: authService.getAuthHeader()
    });
  },

  // Reporting operations
  getDepartmentStats: () => {
    return axios.get(`${API_BASE_URL}/employees/reports/department-stats`, {
      headers: authService.getAuthHeader()
    });
  },

  getTotalEmployees: () => {
    return axios.get(`${API_BASE_URL}/employees/reports/total`, {
      headers: authService.getAuthHeader()
    });
  },

  getHighestSalaryEmployees: () => {
    return axios.get(`${API_BASE_URL}/employees/reports/highest-salary`, {
      headers: authService.getAuthHeader()
    });
  },

  getLowestSalaryEmployees: () => {
    return axios.get(`${API_BASE_URL}/employees/reports/lowest-salary`, {
      headers: authService.getAuthHeader()
    });
  },

  getEmployeesWithSalaryHigherThan: (amount) => {
    return axios.get(`${API_BASE_URL}/employees/reports/higher-than/${amount}`, {
      headers: authService.getAuthHeader()
    });
  },

  getEmployeesWithSalaryLowerThan: (amount) => {
    return axios.get(`${API_BASE_URL}/employees/reports/lower-than/${amount}`, {
      headers: authService.getAuthHeader()
    });
  }
};

export default employeeService;
