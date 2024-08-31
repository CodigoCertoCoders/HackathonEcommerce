import useViewportHeight from '../hooks/useViewportHeight';
import styles from './css/RegisterPage.module.css';
import fonts from '../fonts/fonts.module.css';

import GreenButton from '../components/GreenButton';
import WhiteButton from '../components/WhiteButton';

import FacebookIcon from '../assets/facebook-icon.png';
import Logo from '../components/Logo';
import { GoogleLogin } from '@react-oauth/google';

const clientId = 'process.env.REACT_APP_GOOGLE_CLIENT_ID';

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
