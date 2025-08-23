import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';

import Login from './components/Login';
import Navbar from './components/Navbar';
import EmployeeList from './components/EmployeeList';
import EmployeeForm from './components/EmployeeForm';
import EmployeeDetails from './components/EmployeeDetails';
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
                <Navigate to="/employees" /> : 
                <Login setAuthenticated={setAuthenticated} />
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
            <Route path="/" element={<Navigate to="/employees" />} />
          </Routes>
        </div>
      </div>
    </Router>
  );
}

export default App;
