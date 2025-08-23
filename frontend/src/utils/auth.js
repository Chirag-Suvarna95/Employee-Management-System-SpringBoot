export const isAuthenticated = () => {
  return localStorage.getItem('credentials') !== null;
};

export const getUsername = () => {
  return localStorage.getItem('username');
};
