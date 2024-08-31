import useViewportHeight from '../hooks/useViewportHeight';
import styles from './RegisterPage.module.css';
import fonts from '../fonts/fonts.module.css';

import GreenButton from '../components/GreenButton';
import WhiteButton from '../components/WhiteButton';

import GoogleIcon from '../assets/google-icon.png';
import FacebookIcon from '../assets/facebook-icon.png';
import Logo from '../components/Logo';
import { GoogleLogin } from '@react-oauth/google';

const clientId =
  '932081329445-ipn89airdnpivl04sp01090cvtems6fv.apps.googleusercontent.com';

const RegisterPage = () => {
  const viewportHeight = useViewportHeight();

  const handleGuestLoginClick = () => {};

  const onSuccess = (response) => {
    console.log(response);
  };

  const onFailure = () => {
    console.log('Login failed');
  };

  const handleLoginWithFacebook = () => {};

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
          <GoogleLogin
            clientId={clientId}
            onSuccess={onSuccess}
            onError={onFailure}
          />
          <Logo
            onClick={handleLoginWithFacebook}
            logo={FacebookIcon}
            width={'40px'}
            height={'40px'}
          />
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
