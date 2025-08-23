import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import employeeService from '../services/employeeService';
import AdvancedSearch from './AdvancedSearch'; // Add this import

const EmployeeList = () => {
  const [employees, setEmployees] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  const [searchTerm, setSearchTerm] = useState('');
  const [isAdvancedSearch, setIsAdvancedSearch] = useState(false); // Add this state

  useEffect(() => {
    loadEmployees();
  }, []);

  const loadEmployees = async () => {
    try {
      const response = await employeeService.getAllEmployees();
      setEmployees(response.data);
      setError('');
      setIsAdvancedSearch(false); // Reset advanced search flag
    } catch (error) {
      setError('Failed to load employees');
      console.error('Error loading employees:', error);
    }
    setLoading(false);
  };

  const handleDelete = async (id) => {
    if (window.confirm('Are you sure you want to delete this employee?')) {
      try {
        await employeeService.deleteEmployee(id);
        loadEmployees();
      } catch (error) {
        setError('Failed to delete employee');
      }
    }
  };

  const handleSearch = async (e) => {
    e.preventDefault();
    if (searchTerm.trim()) {
      try {
        const response = await employeeService.searchEmployees(searchTerm);
        setEmployees(response.data);
        setIsAdvancedSearch(false);
      } catch (error) {
        setError('Search failed');
      }
    } else {
      loadEmployees();
    }
  };

  // Add this function to handle advanced search results
  const handleAdvancedSearchResults = (results) => {
    setEmployees(results);
    setIsAdvancedSearch(true);
  };

  if (loading) {
    return <div className="text-center">Loading...</div>;
  }

  return (
    <div>
      <div className="d-flex justify-content-between align-items-center mb-4">
        <h2>Employee List</h2>
        <Link to="/employees/new" className="btn btn-primary">
          Add New Employee
        </Link>
      </div>

      {error && (
        <div className="alert alert-danger" role="alert">
          {error}
        </div>
      )}

      {/* Basic Search */}
      <form onSubmit={handleSearch} className="mb-3">
        <div className="row">
          <div className="col-md-8">
            <input
              type="text"
              className="form-control"
              placeholder="Search by name..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
          </div>
          <div className="col-md-4">
            <button type="submit" className="btn btn-outline-primary me-2">
              Search
            </button>
            <button 
              type="button" 
              className="btn btn-outline-secondary"
              onClick={() => {
                setSearchTerm('');
                loadEmployees();
              }}
            >
              Clear
            </button>
          </div>
        </div>
      </form>

      {/* Add Advanced Search Component */}
      <AdvancedSearch onSearchResults={handleAdvancedSearchResults} />

      {/* Results Summary */}
      {isAdvancedSearch && (
        <div className="alert alert-info">
          Advanced search results: {employees.length} employee(s) found
        </div>
      )}

      <div className="table-responsive">
        <table className="table table-striped">
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Email</th>
              <th>Department</th>
              <th>Job Title</th>
              <th>Salary</th>
              <th>Actions</th>
            </tr>
          </thead>
          <tbody>
            {employees.map(employee => (
              <tr key={employee.id}>
                <td>{employee.id}</td>
                <td>{employee.firstName} {employee.lastName}</td>
                <td>{employee.email}</td>
                <td>{employee.department}</td>
                <td>{employee.jobTitle}</td>
                <td>${employee.salary?.toLocaleString()}</td>
                <td>
                  <Link 
                    to={`/employees/${employee.id}`} 
                    className="btn btn-info btn-sm me-2"
                  >
                    View
                  </Link>
                  <Link 
                    to={`/employees/edit/${employee.id}`} 
                    className="btn btn-warning btn-sm me-2"
                  >
                    Edit
                  </Link>
                  <button 
                    className="btn btn-danger btn-sm" 
                    onClick={() => handleDelete(employee.id)}
                  >
                    Delete
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {employees.length === 0 && !loading && (
        <div className="text-center mt-4">
          <p>No employees found.</p>
        </div>
      )}
    </div>
  );
};

export default EmployeeList;
