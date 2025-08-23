import React, { useState, useEffect } from 'react';
import employeeService from '../services/employeeService';

const Reports = () => {
  const [stats, setStats] = useState({
    totalEmployees: 0,
    departmentStats: [],
    highestPaid: [],
    lowestPaid: []
  });
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState('');

  useEffect(() => {
    loadReportsData();
  }, []);

  const loadReportsData = async () => {
    try {
      const [total, deptStats, highest, lowest] = await Promise.all([
        employeeService.getTotalEmployees(),
        employeeService.getDepartmentStats(),
        employeeService.getHighestSalaryEmployees(),
        employeeService.getLowestSalaryEmployees()
      ]);

      setStats({
        totalEmployees: total.data,
        departmentStats: deptStats.data,
        highestPaid: highest.data,
        lowestPaid: lowest.data
      });
      setError('');
    } catch (error) {
      setError('Failed to load reports data');
    }
    setLoading(false);
  };

  if (loading) {
    return <div className="text-center">Loading reports...</div>;
  }

  return (
    <div>
      {/* <h2 className="mb-4">Employee Reports Dashboard</h2> */}
      
      {error && (
        <div className="alert alert-danger" role="alert">
          {error}
        </div>
      )}

      {/* Total Employees Card */}
      <div className="row mb-4">
        <div className="col-md-3">
          <div className="card text-center">
            <div className="card-body">
              <h5 className="card-title">Total Employees</h5>
              <h2 className="text-primary">{stats.totalEmployees}</h2>
            </div>
          </div>
        </div>
      </div>

      {/* Department Statistics */}
      <div className="row mb-4">
        <div className="col-md-6">
          <div className="card">
            <div className="card-header">
              <h5>Department Statistics</h5>
            </div>
            <div className="card-body">
              <table className="table">
                <thead>
                  <tr>
                    <th>Department</th>
                    <th>Employee Count</th>
                    <th>Average Salary</th>
                  </tr>
                </thead>
                <tbody>
                  {stats.departmentStats.map((dept, index) => (
                    <tr key={index}>
                      <td>{dept.department}</td>
                      <td>{dept.employeeCount}</td>
                      <td>${dept.averageSalary?.toLocaleString()}</td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          </div>
        </div>

        <div className="col-md-6">
          <div className="card mb-3">
            <div className="card-header">
              <h6>Highest Paid Employees</h6>
            </div>
            <div className="card-body">
              {stats.highestPaid.map(emp => (
                <div key={emp.id} className="d-flex justify-content-between">
                  <span>{emp.firstName} {emp.lastName}</span>
                  <span>${emp.salary?.toLocaleString()}</span>
                </div>
              ))}
            </div>
          </div>

          <div className="card">
            <div className="card-header">
              <h6>Lowest Paid Employees</h6>
            </div>
            <div className="card-body">
              {stats.lowestPaid.map(emp => (
                <div key={emp.id} className="d-flex justify-content-between">
                  <span>{emp.firstName} {emp.lastName}</span>
                  <span>${emp.salary?.toLocaleString()}</span>
                </div>
              ))}
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Reports;
