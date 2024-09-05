import React from 'react';
import backArrowImg from '../assets/back_arrow.svg';
import searchIcon from '../assets/search.svg';
import targetIcon from '../assets/target.svg';
import styles from './css/AdressPage.module.css';
import mapMarkerIcon from '../assets/map-marker.svg';
import fonts from '../fonts/fonts.module.css';

const AdressPage = () => {
  const handleBackArrow = () => {};

  return (
    <section className={styles.section}>
      <header className={styles.header}>
        <img onClick={handleBackArrow} src={backArrowImg}></img>
        <h2 className={fonts.robotoBold}>Endereço</h2>
      </header>
      <div className={styles.searchInput}>
        <input type="text"></input>
      </div>
      <div className={styles.divUseCurrentlyLocation}>
        <img src={targetIcon}></img>
        <h3 className={fonts.latoBold}>Usar localização atual</h3>
      </div>
      <div className={styles.myAdress}>
        <h2 className={fonts.latoBold}>Meu endereço</h2>
        <div className={styles.cardUserAdress}>
          <img src={mapMarkerIcon}></img>
          <div className={styles.cardUserAdressInfo}>
            <h3 className={fonts.latoBold}>Av, abobrinha roxa</h3>
            <h4 className={fonts.latoMedium}>Vila dos chaveiros</h4>
          </div>
        </div>
      </div>
    </section>
  );
};

export default AdressPage;
