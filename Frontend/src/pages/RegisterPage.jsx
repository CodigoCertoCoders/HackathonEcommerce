import useViewportHeight from '../hooks/useViewportHeight';
import styles from './css/RegisterPage.module.css';
import fonts from '../fonts/fonts.module.css';

import GreenButton from '../components/GreenButton';
import WhiteButton from '../components/WhiteButton';

import FacebookIcon from '../assets/facebook-icon.png';
import Logo from '../components/Logo';
import { GoogleLogin } from '@react-oauth/google';
import { LoginSocialFacebook } from 'reactjs-social-login';

const googleClientId = import.meta.env.VITE_GOOGLE_CLIENT_ID;
const facebookClientId = import.meta.env.VITE_FACEBOOK_CLIENT_ID;

const RegisterPage = () => {
  const viewportHeight = useViewportHeight();

  const handleGuestLoginClick = () => {};

  const onSuccess = (response) => {
    console.log(response);
  };

  const onFailure = () => {
    console.log('Login failed');
  };

  const handleClickAlredyHaveAccount = () => {
    console.log('tem conta');
  };

  const handleClickCreateNewAccount = () => {
    console.log('nao tem conta');
  };

  const handleLoginWithFacebook = () => {};

  const getUserProfile = async (accessToken) => {
    try {
      const response = await fetch(
        `https://graph.facebook.com/me?fields=email,name&access_token=${accessToken}`,
      );
      const data = await response.json();
      console.log(data);
    } catch (error) {
      console.error('Error fetching data from Facebook API:', error);
    }
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
        <p className={fonts.latoMedium}> Acessar com</p>
        <div className={styles.logos}>
          <GoogleLogin
            clientId={googleClientId}
            onSuccess={onSuccess}
            onError={onFailure}
          />
          <LoginSocialFacebook
            appId={facebookClientId}
            scope="public_profile,email"
            onResolve={(response) => {
              console.log(response);
              getUserProfile(response.data.accessToken);
            }}
            onReject={(error) => {
              console.log(error);
            }}
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
