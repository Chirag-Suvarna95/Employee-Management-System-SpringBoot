import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import 'bootstrap/dist/js/bootstrap.bundle.min.js';
import './App.css';

import Login from './components/Login';
import Navbar from './components/Navbar';
import Home from './components/Home'; // Add this import
import EmployeeList from './components/EmployeeList';
import EmployeeForm from './components/EmployeeForm';
import EmployeeDetails from './components/EmployeeDetails';
import Reports from './components/Reports';
import SalaryAnalysis from './components/SalaryAnalysis';
import { isAuthenticated } from './utils/auth';

function App() {
  const [authenticated, setAuthenticated] = useState(false);

  useEffect(() => {
    setAuthenticated(isAuthenticated());
  }, []);

  const PrivateRoute = ({ children }) => {
    return authenticated ? children : <Navigate to="/login" />;
  };

  return (
    <Router>
      <div className="App">
        {authenticated && <Navbar setAuthenticated={setAuthenticated} />}
        <div className="container mt-4">
          <Routes>
            <Route 
              path="/login" 
              element={
                authenticated ? 
                <Navigate to="/home" /> : // Changed from /employees to /home
                <Login setAuthenticated={setAuthenticated} />
              } 
            />
            <Route 
              path="/home" 
              element={
                <PrivateRoute>
                  <Home />
                </PrivateRoute>
              } 
            />
            <Route 
              path="/employees" 
              element={
                <PrivateRoute>
                  <EmployeeList />
                </PrivateRoute>
              } 
            />
            <Route 
              path="/employees/new" 
              element={
                <PrivateRoute>
                  <EmployeeForm />
                </PrivateRoute>
              } 
            />
            <Route 
              path="/employees/edit/:id" 
              element={
                <PrivateRoute>
                  <EmployeeForm />
                </PrivateRoute>
              } 
            />
            <Route 
              path="/employees/:id" 
              element={
                <PrivateRoute>
                  <EmployeeDetails />
                </PrivateRoute>
              } 
            />
            <Route 
              path="/reports" 
              element={
                <PrivateRoute>
                  <Reports />
                </PrivateRoute>
              } 
            />
            <Route 
              path="/salary-analysis" 
              element={
                <PrivateRoute>
                  <SalaryAnalysis />
                </PrivateRoute>
              } 
            />
            <Route path="/" element={<Navigate to="/home" />} /> {/* Changed default route */}
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
