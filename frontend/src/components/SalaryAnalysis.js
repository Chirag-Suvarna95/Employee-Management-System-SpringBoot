import React, { useState } from 'react';
import employeeService from '../services/employeeService';

const SalaryAnalysis = () => {
  const [salaryAmount, setSalaryAmount] = useState('');
  const [results, setResults] = useState(null);
  const [analysisType, setAnalysisType] = useState('higher');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleAnalysis = async (e) => {
    e.preventDefault();
    if (!salaryAmount) return;

    setLoading(true);
    setError('');

    try {
      let response;
      if (analysisType === 'higher') {
        response = await employeeService.getEmployeesWithSalaryHigherThan(parseFloat(salaryAmount));
      } else {
        response = await employeeService.getEmployeesWithSalaryLowerThan(parseFloat(salaryAmount));
      }
      
      setResults({
        type: analysisType,
        amount: salaryAmount,
        employees: response.data
      });
    } catch (error) {
      setError('Failed to fetch salary analysis');
    }
    setLoading(false);
  };

  return (
    <div>
      <h2 className="mb-4">Salary Analysis</h2>
      
      <div className="card mb-4">
        <div className="card-header">
          <h5>Analyze Employees by Salary Range</h5>
        </div>
        <div className="card-body">
          {error && (
            <div className="alert alert-danger" role="alert">
              {error}
            </div>
          )}
          
          <form onSubmit={handleAnalysis}>
            <div className="row">
              <div className="col-md-3">
                <select 
                  className="form-select"
                  value={analysisType}
                  onChange={(e) => setAnalysisType(e.target.value)}
                >
                  <option value="higher">Salary Higher Than</option>
                  <option value="lower">Salary Lower Than</option>
                </select>
              </div>
              <div className="col-md-6">
                <input
                  type="number"
                  className="form-control"
                  placeholder="Enter salary amount..."
                  value={salaryAmount}
                  onChange={(e) => setSalaryAmount(e.target.value)}
                  required
                />
              </div>
              <div className="col-md-3">
                <button 
                  type="submit" 
                  className="btn btn-primary"
                  disabled={loading}
                >
                  {loading ? 'Analyzing...' : 'Analyze'}
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>

      {/* Results */}
      {results && (
        <div className="card">
          <div className="card-header">
            <h5>
              Employees with salary {results.type} than ${parseFloat(results.amount).toLocaleString()}
            </h5>
            <small className="text-muted">Found {results.employees.length} employee(s)</small>
          </div>
          <div className="card-body">
            {results.employees.length > 0 ? (
              <div className="table-responsive">
                <table className="table table-striped">
                  <thead>
                    <tr>
                      <th>Name</th>
                      <th>Email</th>
                      <th>Department</th>
                      <th>Job Title</th>
                      <th>Salary</th>
                    </tr>
                  </thead>
                  <tbody>
                    {results.employees.map(employee => (
                      <tr key={employee.id}>
                        <td>{employee.firstName} {employee.lastName}</td>
                        <td>{employee.email}</td>
                        <td>{employee.department}</td>
                        <td>{employee.jobTitle}</td>
                        <td className="fw-bold">${employee.salary?.toLocaleString()}</td>
                      </tr>
                    ))}
                  </tbody>
                </table>
              </div>
            ) : (
              <p className="text-center text-muted">No employees found matching the criteria.</p>
            )}
          </div>
        </div>
      )}
    </div>
  );
};

export default SalaryAnalysis;
