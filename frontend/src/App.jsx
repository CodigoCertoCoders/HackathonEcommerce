import { Route, BrowserRouter, Routes } from "react-router-dom";

import Home from "./pages/Home/Home";
import Header from "./components/Header";
import About from "./pages/About";

function App() {
  return (
    <>
      <BrowserRouter>
        <Header />
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/about" element={<About />} />
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
