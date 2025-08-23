import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import employeeService from '../services/employeeService';
import { getUsername } from '../utils/auth';

const Home = () => {
  const [dashboardStats, setDashboardStats] = useState({
    totalEmployees: 0,
    departmentCount: 0,
    loading: true
  });

  useEffect(() => {
    loadDashboardData();
  }, []);

  const loadDashboardData = async () => {
    try {
      const [totalResponse, deptResponse] = await Promise.all([
        employeeService.getTotalEmployees(),
        employeeService.getDepartmentStats()
      ]);
      
      setDashboardStats({
        totalEmployees: totalResponse.data,
        departmentCount: deptResponse.data.length,
        loading: false
      });
    } catch (error) {
      console.error('Error loading dashboard data:', error);
      setDashboardStats(prev => ({ ...prev, loading: false }));
    }
  };

  const currentHour = new Date().getHours();
  const greeting = currentHour < 12 ? 'Good Morning' : currentHour < 18 ? 'Good Afternoon' : 'Good Evening';

  return (
    <div>
      {/* Welcome Header */}
      <div className="row mb-4">
        <div className="col-12">
          <div className="card bg-primary text-white">
            <div className="card-body">
              <h1 className="card-title mb-2">
                {greeting}, {getUsername()}! 
              </h1>
            
            </div>
          </div>
        </div>
      </div>

      {/* Quick Stats */}
      <div className="row mb-4">
        <div className="col-md-4">
          <div className="card bg-light">
            <div className="card-body text-center">
              <i className="fas fa-users fa-2x text-primary mb-2"></i>
              <h3 className="text-primary">
                {dashboardStats.loading ? '...' : dashboardStats.totalEmployees}
              </h3>
              <p className="text-muted mb-0">Total Employees</p>
            </div>
          </div>
        </div>
        <div className="col-md-4">
          <div className="card bg-light">
            <div className="card-body text-center">
              <i className="fas fa-building fa-2x text-success mb-2"></i>
              <h3 className="text-success">
                {dashboardStats.loading ? '...' : dashboardStats.departmentCount}
              </h3>
              <p className="text-muted mb-0">Departments</p>
            </div>
          </div>
        </div>
        <div className="col-md-4">
          <div className="card bg-light">
            <div className="card-body text-center">
              <i className="fas fa-clock fa-2x text-info mb-2"></i>
              <h3 className="text-info">{new Date().toLocaleDateString()}</h3>
              <p className="text-muted mb-0">Today's Date</p>
            </div>
          </div>
        </div>
      </div>

      {/* Main Navigation Cards */}
      <div className="row mb-4">
        <div className="col-12">
          <h2 className="mb-3">Quick Actions</h2>
        </div>
      </div>

      <div className="row g-4">
        {/* Employee Management */}
        <div className="col-md-6 col-lg-4">
          <Link to="/employees" className="text-decoration-none">
            <div className="card h-100 shadow-sm hover-card">
              <div className="card-body text-center">
                <div className="mb-3">
                  <i className="fas fa-users fa-3x text-primary"></i>
                </div>
                <h5 className="card-title">Employee Management</h5>
                <p className="card-text text-muted">
                  View, add, edit, and manage all employee records
                </p>
                <div className="mt-auto">
                  <span className="badge bg-primary">View All</span>
                </div>
              </div>
            </div>
          </Link>
        </div>

        {/* Add Employee */}
        <div className="col-md-6 col-lg-4">
          <Link to="/employees/new" className="text-decoration-none">
            <div className="card h-100 shadow-sm hover-card">
              <div className="card-body text-center">
                <div className="mb-3">
                  <i className="fas fa-user-plus fa-3x text-success"></i>
                </div>
                <h5 className="card-title">Add New Employee</h5>
                <p className="card-text text-muted">
                  Register a new employee with all details
                </p>
                <div className="mt-auto">
                  <span className="badge bg-success">Add New</span>
                </div>
              </div>
            </div>
          </Link>
        </div>

        {/* Reports */}
        <div className="col-md-6 col-lg-4">
          <Link to="/reports" className="text-decoration-none">
            <div className="card h-100 shadow-sm hover-card">
              <div className="card-body text-center">
                <div className="mb-3">
                  <i className="fas fa-chart-bar fa-3x text-info"></i>
                </div>
                <h5 className="card-title">Reports & Analytics</h5>
                <p className="card-text text-muted">
                  View department statistics and employee analytics
                </p>
                <div className="mt-auto">
                  <span className="badge bg-info">View Reports</span>
                </div>
              </div>
            </div>
          </Link>
        </div>

        {/* Salary Analysis */}
        <div className="col-md-6 col-lg-4">
          <Link to="/salary-analysis" className="text-decoration-none">
            <div className="card h-100 shadow-sm hover-card">
              <div className="card-body text-center">
                <div className="mb-3">
                  <i className="fas fa-dollar-sign fa-3x text-warning"></i>
                </div>
                <h5 className="card-title">Salary Analysis</h5>
                <p className="card-text text-muted">
                  Analyze employee salaries and compensation
                </p>
                <div className="mt-auto">
                  <span className="badge bg-warning">Analyze</span>
                </div>
              </div>
            </div>
          </Link>
        </div>

        {/* Search Employees */}
        <div className="col-md-6 col-lg-4">
          <Link to="/employees" className="text-decoration-none">
            <div className="card h-100 shadow-sm hover-card">
              <div className="card-body text-center">
                <div className="mb-3">
                  <i className="fas fa-search fa-3x text-secondary"></i>
                </div>
                <h5 className="card-title">Search Employees</h5>
                <p className="card-text text-muted">
                  Find employees by name, department, or position
                </p>
                <div className="mt-auto">
                  <span className="badge bg-secondary">Search</span>
                </div>
              </div>
            </div>
          </Link>
        </div>

        {/* System Info */}
        <div className="col-md-6 col-lg-4">
          <div className="card h-100 shadow-sm">
            <div className="card-body text-center">
              <div className="mb-3">
                <i className="fas fa-info-circle fa-3x text-muted"></i>
              </div>
              <h5 className="card-title">System Information</h5>
              <p className="card-text text-muted">
                Employee Management System v1.0
              </p>
              <div className="mt-auto">
                <small className="text-muted">Built with Spring Boot & React</small>
              </div>
            </div>
          </div>
        </div>
      </div>

      {/* Recent Activity Section */}
      <div className="row mt-5">
        <div className="col-12">
          <h3 className="mb-3">Recent Activity</h3>
          <div className="card">
            <div className="card-body">
              <div className="d-flex align-items-center">
                <i className="fas fa-clock text-muted me-3"></i>
                <div>
                  <p className="mb-1">Welcome to Employee Management System</p>
                  <small className="text-muted">
                    System logged in at {new Date().toLocaleTimeString()}
                  </small>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Home;
