import React from 'react';
import backArrowImg from '../assets/back_arrow.svg';
import fonts from '../fonts/fonts.module.css';
import styles from './css/CheckoutPage.module.css';
import { useNavigate } from 'react-router-dom';
import mapMarkerIcon from '../assets/map-marker.svg';
import pixIcon from '../assets/pix-icon.svg';
import stripeIcon from '../assets/stripe-icon.svg';
import whatsappIcon from '../assets/whatsapp-icon.svg';
import GreenButton from '../components/GreenButton';

const CheckoutPage = () => {
  const navigate = useNavigate();
  const handleBackArrow = () => {
    navigate(-1);
  };

  const handleEdit = () => {};

  const handleSubmit = () => {};

  return (
    <section>
      <header className={styles.header}>
        <img onClick={handleBackArrow} src={backArrowImg} alt="Back Arrow" />
        <h2 className={fonts.robotoBold}>Checkout</h2>
      </header>
      <div className={styles.centralizeDiv}>
        <article className={styles.article}>
          <h3>Entregar no endereço:</h3>
          <div className={styles.cardUserAdress}>
            <img src={mapMarkerIcon} alt="Map Marker Icon" />
            <div className={styles.cardUserAdressInfo}>
              <h3 className={fonts.latoBold}>Bairro</h3>
              <h4 className={fonts.latoMedium}>Logradouro</h4>
            </div>
            <p onClick={handleEdit} className={styles.editText}>
              Editar
            </p>
          </div>

          <div>
            <h3>Formas de pagamento</h3>
            <div>
              <div className={styles.cardPayment}>
                <img src={pixIcon} />
                <h3>Pix</h3>
              </div>
              <div className={styles.cardPayment}>
                <img src={stripeIcon} />
                <h3>Stripe</h3>
              </div>
              <div className={styles.cardPayment}>
                <img src={whatsappIcon} />
                <h3>Concluir pedido via whatsapp</h3>
              </div>
            </div>
          </div>
          <GreenButton
            classButton={styles.buttonSubmit}
            text={'Continuar'}
            onClick={handleSubmit}
          />
        </article>
      </div>
    </section>
  );
};

export default CheckoutPage;
