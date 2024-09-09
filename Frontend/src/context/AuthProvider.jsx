import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';

export const AuthContext = React.createContext();

export const AuthProvider = ({ children }) => {
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [userData, setUserData] = useState(null);
  const navigate = useNavigate();

  const login = (data, token) => {
    setUserData(data);
    setIsAuthenticated(true);

    Cookies.set('token', token, { expires: 7 });
    Cookies.set('userId', data.userId, { expires: 7 });
  };

  const logout = () => {
    setIsAuthenticated(false);
    setUserData(null);

    Cookies.remove('token');
    Cookies.remove('userId');

    navigate('/login');
  };

  useEffect(() => {
    const token = Cookies.get('token');
    const userId = Cookies.get('userId');

    if (token && userId) {
      setIsAuthenticated(true);
      setUserData({ token, userId });
    }
  }, []);

  return (
    <AuthContext.Provider
      value={{ isAuthenticated, setUserData, userData, login, logout }}
    >
      {children}
    </AuthContext.Provider>
  );
};
