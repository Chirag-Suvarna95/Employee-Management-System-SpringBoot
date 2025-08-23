import axios from 'axios';
import authService from './authService';

const API_BASE_URL = 'http://localhost:8080';

const employeeService = {
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

  searchEmployees: (name) => {
  return axios.get(`${API_BASE_URL}/employees/search?name=${name}`, {
    headers: authService.getAuthHeader()
  });
}

};

export default employeeService;
