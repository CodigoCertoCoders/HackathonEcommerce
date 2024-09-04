import React from 'react';
import { AuthContext } from '../context/AuthProvider';
import { Navigate } from 'react-router-dom';

const ProtectedRoute = ({ element }) => {
  const { isAuthenticated } = React.useContext(AuthContext);

  if (!isAuthenticated) {
    return <Navigate to="/" />;
  }

  return element;
};

export default ProtectedRoute;
