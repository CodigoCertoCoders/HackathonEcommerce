import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import RegisterPage from './pages/RegisterPage';
import ProductPage from './pages/ProductPage';
import { ProductProvider } from './context/ProductContext';

function App() {
  return (
    <ProductProvider>
      <Router>
        <Routes>
          <Route path="/" element={<RegisterPage />} />
          <Route path="/catalog" element={<ProductPage />} />
        </Routes>
      </Router>
    </ProductProvider>
  );
}

export default App;
