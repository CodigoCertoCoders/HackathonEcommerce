import React from 'react';
import { AuthContext } from '../context/AuthProvider';
import { Navigate } from 'react-router-dom';

const PublicRoute = ({ element }) => {
  const { isAuthenticated } = React.useContext(AuthContext);

  if (isAuthenticated) {
    return <Navigate to="/catalog" />;
  }

  return element;
};

export default PublicRoute;
