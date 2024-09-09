import React, { useState, useContext } from 'react';
import backArrowImg from '../assets/back_arrow.svg';
import fonts from '../fonts/fonts.module.css';
import styles from './css/CheckoutPage.module.css';
import { useNavigate } from 'react-router-dom';
import mapMarkerIcon from '../assets/map-marker.svg';
import pixIcon from '../assets/pix-icon.svg';
import stripeIcon from '../assets/stripe-icon.svg';
import whatsappIcon from '../assets/whatsapp-icon.svg';
import GreenButton from '../components/GreenButton';
import { ProductContext } from '../context/ProductContext';

const CheckoutPage = () => {
  const { cartProd } = useContext(ProductContext);
  const navigate = useNavigate();
  const [selectedPayment, setSelectedPayment] = useState(null);

  const handleBackArrow = () => {
    navigate(-1);
  };

  const handleEdit = () => {
    // Lógica para editar endereço
  };

  const handlePaymentSelection = (paymentMethod) => {
    setSelectedPayment(paymentMethod);
  };

  const handleSubmit = () => {
    if (!selectedPayment) {
      alert('Por favor, selecione uma forma de pagamento.');
      return;
    }

    if (selectedPayment === 'whatsapp') {
      sendOrderToWhatsApp();
    } else {
      alert('Redirecionando para o pagamento via ' + selectedPayment);
    }
  };

  const sendOrderToWhatsApp = () => {
    const orderDetails = cartProd
      .map(
        (prod) =>
          `Produto: ${prod.name}, Quantidade: ${prod.qtd}, Preço: R$${prod.price}`,
      )
      .join('\n');
    const total = cartProd.reduce(
      (sum, prod) => sum + prod.qtd * prod.price,
      0,
    );
    const message = `Novo pedido:\n\n${orderDetails}\n\nTotal: R$${total.toFixed(
      2,
    )}`;

    const whatsappLink = `https://wa.me/5562994123574?text=${encodeURIComponent(
      message,
    )}`;
    window.open(whatsappLink, '_blank');
  };

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
              <div
                className={`${styles.cardPayment} ${
                  selectedPayment === 'pix' ? styles.selected : ''
                }`}
                onClick={() => handlePaymentSelection('pix')}
              >
                <img src={pixIcon} alt="Pix Icon" />
                <h3>Pix</h3>
              </div>
              <div
                className={`${styles.cardPayment} ${
                  selectedPayment === 'stripe' ? styles.selected : ''
                }`}
                onClick={() => handlePaymentSelection('stripe')}
              >
                <img src={stripeIcon} alt="Stripe Icon" />
                <h3>Stripe</h3>
              </div>
              <div
                className={`${styles.cardPayment} ${
                  selectedPayment === 'whatsapp' ? styles.selected : ''
                }`}
                onClick={() => handlePaymentSelection('whatsapp')}
              >
                <img src={whatsappIcon} alt="WhatsApp Icon" />
                <h3>Concluir pedido via WhatsApp</h3>
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
