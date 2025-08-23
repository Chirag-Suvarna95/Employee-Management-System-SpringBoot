import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import employeeService from '../services/employeeService';


const EmployeeDetails = () => {
  const [employee, setEmployee] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');
  
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    loadEmployee();
  }, [id]);

  const loadEmployee = async () => {
    try {
      const response = await employeeService.getEmployeeById(id);
      setEmployee(response.data);
      setError('');
    } catch (error) {
      setError('Failed to load employee details');
    }
    setLoading(false);
  };

  const handleDelete = async () => {
    if (window.confirm('Are you sure you want to delete this employee?')) {
      try {
        await employeeService.deleteEmployee(id);
        navigate('/employees');
      } catch (error) {
        setError('Failed to delete employee');
      }
    }
  };

  if (loading) {
    return <div className="text-center">Loading...</div>;
  }

  if (error) {
    return (
      <div className="alert alert-danger" role="alert">
        {error}
      </div>
    );
  }

  if (!employee) {
    return <div className="text-center">Employee not found</div>;
  }

  return (
    <div className="row justify-content-center">
      <div className="col-md-8">
        <div className="card">
          <div className="card-header d-flex justify-content-between align-items-center">
            <h3>Employee Details</h3>
            <div>
              <Link 
                to={`/employees/edit/${employee.id}`} 
                className="btn btn-warning me-2"
              >
                Edit
              </Link>
              <button 
                className="btn btn-danger me-2" 
                onClick={handleDelete}
              >
                Delete
              </button>
              <Link to="/employees" className="btn btn-secondary">
                Back to List
              </Link>
            </div>
          </div>
          <div className="card-body">
            <div className="row">
              <div className="col-md-6">
                <p><strong>ID:</strong> {employee.id}</p>
                <p><strong>First Name:</strong> {employee.firstName}</p>
                <p><strong>Last Name:</strong> {employee.lastName}</p>
                <p><strong>Email:</strong> {employee.email}</p>
              </div>
              <div className="col-md-6">
                <p><strong>Department:</strong> {employee.department}</p>
                <p><strong>Job Title:</strong> {employee.jobTitle}</p>
                <p><strong>Salary:</strong> ${employee.salary?.toLocaleString()}</p>
                <p><strong>Date Created:</strong> {new Date(employee.dateCreated).toLocaleDateString()}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default EmployeeDetails;
