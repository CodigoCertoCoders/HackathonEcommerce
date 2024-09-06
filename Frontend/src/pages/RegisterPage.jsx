import React from 'react';
import { useNavigate } from 'react-router-dom';
import { GoogleOAuthProvider, GoogleLogin } from '@react-oauth/google';
import { AuthContext } from '../context/AuthProvider';
import useViewportHeight from '../hooks/useViewportHeight';
import styles from './css/RegisterPage.module.css';
import fonts from '../fonts/fonts.module.css';
import GreenButton from '../components/GreenButton';
import WhiteButton from '../components/WhiteButton';
import GoogleIcon from '../assets/google-icon.png';
import FacebookIcon from '../assets/facebook-icon.png';
import Logo from '../components/Logo';

// Google Client ID from your .env
const googleClientId = import.meta.env.VITE_GOOGLE_CLIENT_ID;

const RegisterPage = () => {
  const viewportHeight = useViewportHeight();
  const navigate = useNavigate();
  const { login } = React.useContext(AuthContext);

  const handleGuestLoginClick = () => {
    navigate('/catalog');
  };

  // Google login success
  const handleGoogleSuccess = (credentialResponse) => {
    const token = credentialResponse.credential;
    // Processar o token do Google aqui
    console.log('Google login bem-sucedido:', token);
    login(token);
  };

  const handleGoogleFailure = (error) => {
    console.log(`Erro no login com Google: ${error}`);
  };

  // Facebook login using SDK
  const handleFacebookLogin = () => {
    window.FB.login(
      function (response) {
        if (response.authResponse) {
          console.log('Login com Facebook realizado com sucesso', response);
          const { accessToken, userID } = response.authResponse;
          // Processar o token do Facebook aqui
          login(accessToken, { userID });
        } else {
          console.log('Usuário cancelou o login ou não autorizou.');
        }
      },
      { scope: 'public_profile,email' },
    );
  };

  const handleClickAlredyHaveAccount = () => {
    navigate('/signin');
  };

  const handleClickCreateNewAccount = () => {
    navigate('/signup');
  };

  React.useEffect(() => {
    // Inicializando o SDK do Facebook
    window.fbAsyncInit = function () {
      window.FB.init({
        appId: import.meta.env.VITE_FACEBOOK_CLIENT_ID,
        cookie: true,
        xfbml: true,
        version: 'v12.0',
      });
    };

    (function (d, s, id) {
      var js,
        fjs = d.getElementsByTagName(s)[0];
      if (d.getElementById(id)) return;
      js = d.createElement(s);
      js.id = id;
      js.src = 'https://connect.facebook.net/en_US/sdk.js';
      fjs.parentNode.insertBefore(js, fjs);
    })(document, 'script', 'facebook-jssdk');
  }, []);

  return (
    <section className={styles.container} style={{ height: viewportHeight }}>
      <div className={styles.background}></div>
      <div className={`${styles.form} ${fonts.montserratMedium}`}>
        <h1 className={styles.computerText}>Welcome!</h1>
        <div className={styles.buttons}>
          <GreenButton
            onClick={handleClickAlredyHaveAccount}
            text={'Já tenho uma conta'}
          />
          <WhiteButton
            onClick={handleClickCreateNewAccount}
            text={'Criar nova conta'}
          />
        </div>
        <p style={{ cursor: 'default' }} className={fonts.latoMedium}>
          Acessar com
        </p>
        <div className={styles.logos}>
          {/* Google Login */}
          <GoogleOAuthProvider clientId={googleClientId}>
            <GoogleLogin
              onSuccess={handleGoogleSuccess}
              onError={handleGoogleFailure}
            >
              <Logo logo={GoogleIcon} width={'40px'} height={'40px'} />
            </GoogleLogin>
          </GoogleOAuthProvider>

          {/* Facebook Login */}
          <div onClick={handleFacebookLogin}>
            <Logo logo={FacebookIcon} width={'40px'} height={'40px'} />
          </div>
        </div>
        <p
          onClick={handleGuestLoginClick}
          style={{ color: '#294122' }}
          className={fonts.latoMedium}
        >
          Continuar como visitante
        </p>
      </div>
    </section>
  );
};

export default RegisterPage;
