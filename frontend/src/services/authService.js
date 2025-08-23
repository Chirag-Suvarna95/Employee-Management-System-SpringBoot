import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080';

const authService = {
  login: (username, password) => {
    const credentials = btoa(`${username}:${password}`);
    
    return axios.get(`${API_BASE_URL}/employees`, {
      headers: {
        'Authorization': `Basic ${credentials}`,
        'Content-Type': 'application/json'
      }
    }).then(() => {
      localStorage.setItem('credentials', credentials);
      localStorage.setItem('username', username);
      return { success: true };
    }).catch(error => {
      return { success: false, error: error.response?.data?.message || 'Login failed' };
    });
  },

  logout: () => {
    localStorage.removeItem('credentials');
    localStorage.removeItem('username');
  },

  getAuthHeader: () => {
    const credentials = localStorage.getItem('credentials');
    return credentials ? { 'Authorization': `Basic ${credentials}` } : {};
  }
};

export default authService;
