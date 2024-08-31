import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import RegisterPage from './pages/RegisterPage';
import ProductPage from './pages/ProductPage';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<RegisterPage />} />
        <Route path="/catalog" element={<ProductPage />} />
      </Routes>
    </Router>
  );
}

export default App;
