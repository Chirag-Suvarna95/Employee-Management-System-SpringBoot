import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import authService from '../services/authService';
import { getUsername } from '../utils/auth';

const Navbar = ({ setAuthenticated }) => {
  const navigate = useNavigate();

  const handleLogout = () => {
    authService.logout();
    setAuthenticated(false);
    navigate('/login');
  };

  return (
    <nav className="navbar navbar-expand-lg navbar-dark bg-primary">
      <div className="container">
        <Link className="navbar-brand" to="/home">
          <i className="fas fa-building me-2"></i>
          Employee Management System
        </Link>
        
        <button 
          className="navbar-toggler" 
          type="button" 
          data-bs-toggle="collapse" 
          data-bs-target="#navbarNav"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        
        <div className="collapse navbar-collapse" id="navbarNav">
          <div className="navbar-nav me-auto">
            <Link className="nav-link" to="/home">
              <i className="fas fa-home me-1"></i>Home
            </Link>
            <Link className="nav-link" to="/employees">
              <i className="fas fa-users me-1"></i>Employees
            </Link>
            <Link className="nav-link" to="/employees/new">
              <i className="fas fa-user-plus me-1"></i>Add Employee
            </Link>
            <Link className="nav-link" to="/reports">
              <i className="fas fa-chart-bar me-1"></i>Reports
            </Link>
            <Link className="nav-link" to="/salary-analysis">
              <i className="fas fa-dollar-sign me-1"></i>Salary Analysis
            </Link>
          </div>
          
          <div className="navbar-nav">
            <span className="navbar-text me-3">
              <i className="fas fa-user me-1"></i>
              Welcome, <strong>{getUsername()}</strong>
            </span>
            <button className="btn btn-outline-light" onClick={handleLogout}>
              <i className="fas fa-sign-out-alt me-1"></i>Logout
            </button>
          </div>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
