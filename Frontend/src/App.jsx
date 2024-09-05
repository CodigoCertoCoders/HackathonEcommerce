import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import RegisterPage from './pages/RegisterPage';
import ProductPage from './pages/ProductPage';
import { ProductContext, ProductProvider } from './context/ProductContext';
import ProductDetails from './pages/ProductDetails';
import { useContext } from 'react';
import SignUpPage from './pages/SignUpPage';
import SignInPage from './pages/SignInPage';
import ProtectedRoute from './components/ProtectedRoute';
import { AuthProvider } from './context/AuthProvider';
import PublicRoute from './components/PublicRoute';

function App() {
  return (
    <AuthProvider>
      <ProductProvider>
        <Router>
          <Routes>
            <Route
              path="/"
              element={<PublicRoute element={<RegisterPage />} />}
            />
            <Route
              path="/signup"
              element={<PublicRoute element={<SignUpPage />} />}
            />
            <Route
              path="/signin"
              element={<PublicRoute element={<SignInPage />} />}
            />
            <Route path="/catalog" element={<ProductPage />} />
            <Route
              path="/catalog/:id"
              element={<ProtectedRoute element={<ProductDetails />} />}
            />
          </Routes>
        </Router>
      </ProductProvider>
    </AuthProvider>
  );
}

export default App;
