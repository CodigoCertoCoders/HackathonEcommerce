import useViewportHeight from '../hooks/useViewportHeight';
import styles from './css/RegisterPage.module.css';
import fonts from '../fonts/fonts.module.css';

import GreenButton from '../components/GreenButton';
import WhiteButton from '../components/WhiteButton';

import GoogleIcon from '../assets/google-icon.png';
import FacebookIcon from '../assets/facebook-icon.png';
import Logo from '../components/Logo';
import { LoginSocialGoogle, LoginSocialFacebook } from 'reactjs-social-login';

const googleClientId = import.meta.env.VITE_GOOGLE_CLIENT_ID;
const facebookClientId = import.meta.env.VITE_FACEBOOK_CLIENT_ID;

const RegisterPage = () => {
  const viewportHeight = useViewportHeight();

  const handleGuestLoginClick = () => {};

  const handleGoogleSuccess = (response) => {
    console.log('Google login successful:', response);
  };

  const handleGoogleFailure = (error) => {
    console.error('Google Login failed:', error);
  };

  const handleFacebookSuccess = (response) => {
    console.log('Facebook login successful:', response);
    getUserProfile(response.data.accessToken);
  };

  const handleFacebookFailure = (error) => {
    console.error('Facebook Login failed:', error);
  };

  const handleClickAlredyHaveAccount = () => {
    console.log('Already have an account');
  };

  const handleClickCreateNewAccount = () => {
    console.log('Create a new account');
  };

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
          <LoginSocialGoogle
            client_id={googleClientId}
            onResolve={handleGoogleSuccess}
            onReject={handleGoogleFailure}
            className={styles.socialButton} // Use classes to style the button
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
