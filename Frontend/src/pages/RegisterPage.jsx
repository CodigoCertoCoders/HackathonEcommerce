import React from 'react';

import useViewportHeight from '../hooks/useViewportHeight';
import styles from './css/RegisterPage.module.css';
import fonts from '../fonts/fonts.module.css';

import GreenButton from '../components/GreenButton';
import WhiteButton from '../components/WhiteButton';

import GoogleIcon from '../assets/google-icon.png';
import FacebookIcon from '../assets/facebook-icon.png';
import Logo from '../components/Logo';
import { LoginSocialGoogle, LoginSocialFacebook } from 'reactjs-social-login';
import { useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthProvider';

const googleClientId = import.meta.env.VITE_GOOGLE_CLIENT_ID;
const facebookClientId = import.meta.env.VITE_FACEBOOK_CLIENT_ID;

const RegisterPage = () => {
  const viewportHeight = useViewportHeight();
  const navigate = useNavigate();
  const { login } = React.useContext(AuthContext);

  const handleGuestLoginClick = () => {
    navigate('/catalog');
  };

  const handleGoogleSuccess = (response) => {
    login(response.id_token, response.user);
    console.log(response);
  };

  const handleGoogleFailure = (error) => {
    console.log(`Tivemos algum problema no login com google ${error}`);
  };

  const handleFacebookSuccess = (response) => {
    const { data } = response;
    const { first_name, email, picture } = data;
    console.log('Login com facebook realizado');
    console.log(first_name, email, picture.data.url);
    // Send data to backend >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  };

  const handleFacebookFailure = (error) => {
    console.log(`Tivemos algum problema no login com Facebook ${error}`);
  };

  const handleClickAlredyHaveAccount = () => {
    navigate('/signin');
  };

  const handleClickCreateNewAccount = () => {
    navigate('/signup');
  };

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
          {' '}
          Acessar com
        </p>
        <div className={styles.logos}>
          <LoginSocialGoogle
            client_id={googleClientId}
            onResolve={handleGoogleSuccess}
            onReject={handleGoogleFailure}
            className={styles.socialButton}
          >
            <Logo logo={GoogleIcon} width={'40px'} height={'40px'} />
          </LoginSocialGoogle>
          <LoginSocialFacebook
            appId={facebookClientId}
            scope="public_profile,email"
            onResolve={handleFacebookSuccess}
            onReject={handleFacebookFailure}
          >
            <Logo logo={FacebookIcon} width={'40px'} height={'40px'} />
          </LoginSocialFacebook>
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
