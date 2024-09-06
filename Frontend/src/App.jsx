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
import AdressPage from './pages/AdressPage';
import CheckoutPage from './pages/CheckoutPage';
import CartPage from './pages/CartPage';

function App() {
  return (
    <Router>
      <AuthProvider>
        <ProductProvider>
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
            <Route
              path="/catalog"
              element={<ProtectedRoute element={<ProductPage />} />}
            />
            <Route
              path="/adress"
              element={<ProtectedRoute element={<AdressPage />} />}
            />
            <Route
              path="/catalog/:id"
              element={<ProtectedRoute element={<ProductDetails />} />}
            />
            <Route
              path="/checkout"
              element={<ProtectedRoute element={<CheckoutPage />} />}
            />
          </Routes>
        </ProductProvider>
      </AuthProvider>
    </Router>
  );
}

export default App;
