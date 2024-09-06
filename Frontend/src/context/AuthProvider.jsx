import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

export const AuthContext = React.createContext();

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [userData, setUserData] = useState(null);

  const login = (data) => {
    setUserData(data);
    setIsAuthenticated(true);
  };

  const logout = () => {
    setIsAuthenticated(false);
    setUserData(null);
  };

  console.log(userData);
  console.log(isAuthenticated);

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, setUserData, userData, login, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
};
