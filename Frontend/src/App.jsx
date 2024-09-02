import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import RegisterPage from './pages/RegisterPage';
import ProductPage from './pages/ProductPage';
import { ProductContext, ProductProvider } from './context/ProductContext';
import ProductDetails from './pages/ProductDetails';
import { useContext } from 'react';

function App() {
  return (
    <ProductProvider>
      <Router>
        <Routes>
          <Route path="/" element={<RegisterPage />} />
          {
            //<Route path="/signup" element={<SignUpPage />} />
            //<Route path="/signin" element={<SignInPage />} />
          }
          <Route path="/catalog" element={<ProductPage />} />
          <Route path="/catalog/:id" element={<ProductDetails />}></Route>
        </Routes>
      </Router>
    </ProductProvider>
  );
}

export default App;
