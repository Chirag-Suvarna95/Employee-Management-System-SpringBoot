import React, { useState } from 'react';
import employeeService from '../services/employeeService';

const AdvancedSearch = ({ onSearchResults }) => {
  const [searchType, setSearchType] = useState('department');
  const [searchValue, setSearchValue] = useState('');
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');

  const handleSearch = async (e) => {
    e.preventDefault();
    if (!searchValue.trim()) return;

    setLoading(true);
    setError('');

    try {
      let response;
      switch (searchType) {
        case 'department':
          response = await employeeService.getEmployeesByDepartment(searchValue);
          break;
        case 'jobTitle':
          response = await employeeService.getEmployeesByJobTitle(searchValue);
          break;
        case 'salaryHigher':
          response = await employeeService.getEmployeesWithSalaryHigherThan(parseFloat(searchValue));
          break;
        case 'salaryLower':
          response = await employeeService.getEmployeesWithSalaryLowerThan(parseFloat(searchValue));
          break;
        default:
          return;
      }
      onSearchResults(response.data);
    } catch (error) {
      setError('Search failed');
    }
    setLoading(false);
  };

  return (
    <div className="card mb-4">
      <div className="card-header">
        <h5>Advanced Search</h5>
      </div>
      <div className="card-body">
        {error && (
          <div className="alert alert-danger" role="alert">
            {error}
          </div>
        )}
        
        <form onSubmit={handleSearch}>
          <div className="row">
            <div className="col-md-4">
              <select 
                className="form-select"
                value={searchType}
                onChange={(e) => setSearchType(e.target.value)}
              >
                <option value="department">By Department</option>
                <option value="jobTitle">By Job Title</option>
                <option value="salaryHigher">Salary Higher Than</option>
                <option value="salaryLower">Salary Lower Than</option>
              </select>
            </div>
            <div className="col-md-6">
              <input
                type={searchType.includes('salary') ? 'number' : 'text'}
                className="form-control"
                placeholder={
                  searchType === 'department' ? 'Enter department...' :
                  searchType === 'jobTitle' ? 'Enter job title...' :
                  'Enter salary amount...'
                }
                value={searchValue}
                onChange={(e) => setSearchValue(e.target.value)}
                required
              />
            </div>
            <div className="col-md-2">
              <button 
                type="submit" 
                className="btn btn-primary"
                disabled={loading}
              >
                {loading ? 'Searching...' : 'Search'}
              </button>
            </div>
          </div>
        </form>
      </div>
    </div>
  );
};

export default AdvancedSearch;
