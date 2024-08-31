import { StrictMode } from 'react';
import { createRoot } from 'react-dom/client';
import App from './App.jsx';
import './fonts/fonts.module.css';
import './global.css';
import { GoogleOAuthProvider } from '@react-oauth/google';

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <GoogleOAuthProvider clientId="623403355141-v51r8a9ks2kmt49018gs6dvdcl0qlqff.apps.googleusercontent.com">
      <App />
    </GoogleOAuthProvider>
  </StrictMode>,
);
