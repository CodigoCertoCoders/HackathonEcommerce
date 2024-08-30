import useViewportHeight from '../hooks/useViewportHeight';
import styles from './RegisterPage.module.css';
import fonts from '../fonts/fonts.module.css';

import GreenButton from '../components/GreenButton';
import WhiteButton from '../components/WhiteButton';

import GoogleIcon from '../assets/google-icon.png';
import FacebookIcon from '../assets/facebook-icon.png';
import Logo from '../components/Logo';

const RegisterPage = () => {
  const viewportHeight = useViewportHeight();

  return (
    <>
      <section className={styles.container} style={{ height: viewportHeight }}>
        <div className={styles.form}>
          <div className={styles.buttons}>
            <GreenButton text={'Já tenho uma conta'} />
            <WhiteButton text={'Criar nova conta'} />
          </div>
          <p className={fonts.latoMedium}> Acessar com</p>
          <div className={styles.logos}>
            <Logo logo={GoogleIcon} />
            <Logo logo={FacebookIcon} />
          </div>
          <p style={{ color: '#294122' }} className={fonts.latoMedium}>
            Continuar como visitante
          </p>
        </div>
      </section>
    </>
  );
};

export default RegisterPage;
