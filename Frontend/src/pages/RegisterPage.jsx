import useViewportHeight from '../hooks/useViewportHeight';
import styles from './css/RegisterPage.module.css';
import fonts from '../fonts/fonts.module.css';

import GreenButton from '../components/GreenButton';
import WhiteButton from '../components/WhiteButton';

import GoogleIcon from '../assets/google-icon.png';
import FacebookIcon from '../assets/facebook-icon.png';
import Logo from '../components/Logo';
import { useGoogleLogin, googleLogout } from '@react-oauth/google';
import { LoginSocialFacebook } from 'reactjs-social-login';
import { FacebookLoginButton } from 'react-social-login-buttons';

const RegisterPage = () => {
  const viewportHeight = useViewportHeight();

  const handleGuestLoginClick = () => {};

  const login = useGoogleLogin({
    onSuccess: (tokenResponse) => console.log(tokenResponse),
  });

  return (
    <section className={styles.container} style={{ height: viewportHeight }}>
      <div className={styles.background}></div>
      <div className={`${styles.form} ${fonts.montserratMedium}`}>
        <h1 className={styles.computerText}>Welcome!</h1>
        <div className={styles.buttons}>
          <GreenButton text={'Já tenho uma conta'} />
          <WhiteButton text={'Criar nova conta'} />
        </div>
        <p className={fonts.latoMedium}> Acessar com</p>
        <div className={styles.logos}>
          <button
            style={{ background: 'none', border: 'none' }}
            onClick={() => login()}
          >
            <Logo logo={GoogleIcon} width={'40px'} height={'40px'} />
          </button>
          <LoginSocialFacebook
            appId="874984757295899"
            onResolve={(response) => {
              console.log(response);
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
