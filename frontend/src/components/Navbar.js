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
        <Link className="navbar-brand" to="/employees">
          Employee Management
        </Link>
        <div className="navbar-nav me-auto">
          <Link className="nav-link" to="/employees">
            Employees
          </Link>
          <Link className="nav-link" to="/employees/new">
            Add Employee
          </Link>
        </div>
        <div className="navbar-nav">
          <span className="navbar-text me-3">
            Welcome, {getUsername()}
          </span>
          <button className="btn btn-outline-light" onClick={handleLogout}>
            Logout
          </button>
        </div>
      </div>
    </nav>
  );
};

export default Navbar;
