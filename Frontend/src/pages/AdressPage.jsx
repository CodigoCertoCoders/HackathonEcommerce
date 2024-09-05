import React, { useEffect, useRef } from 'react';
import backArrowImg from '../assets/back_arrow.svg';
import searchIcon from '../assets/search.svg';
import targetIcon from '../assets/target.svg';
import styles from './css/AdressPage.module.css';
import mapMarkerIcon from '../assets/map-marker.svg';
import fonts from '../fonts/fonts.module.css';

const AdressPage = () => {
  const inputRef = useRef(null);

  useEffect(() => {
    const loadGoogleMapsScript = () => {
      const script = document.createElement('script');
      script.src =
        'https://maps.googleapis.com/maps/api/js?key=AIzaSyCdl7UJnQqnsVt483Qbesn7ESZGSqLfKxc&libraries=places';
      script.async = true;
      script.defer = true;
      script.onload = () => initializeAutocomplete();
      document.body.appendChild(script);
    };

    const initializeAutocomplete = () => {
      if (window.google && window.google.maps) {
        const autocomplete = new window.google.maps.places.Autocomplete(
          inputRef.current,
        );

        autocomplete.addListener('place_changed', () => {
          const place = autocomplete.getPlace();
          if (place.formatted_address) {
            console.log('Endereço Selecionado:', place.formatted_address);
          }
        });
      }
    };

    loadGoogleMapsScript();
  }, []);

  const handleBackArrow = () => {};

  return (
    <section className={styles.section}>
      <header className={styles.header}>
        <img onClick={handleBackArrow} src={backArrowImg} alt="Back Arrow" />
        <h2 className={fonts.robotoBold}>Endereço</h2>
      </header>
      <div className={styles.searchInput}>
        <input ref={inputRef} type="text" placeholder="Digite o endereço" />
      </div>
      <div className={styles.divUseCurrentlyLocation}>
        <img src={targetIcon} alt="Target Icon" />
        <h3 className={fonts.latoBold}>Usar localização atual</h3>
      </div>
      <div className={styles.myAdress}>
        <h2 className={fonts.latoBold}>Meu endereço</h2>
        <div className={styles.cardUserAdress}>
          <img src={mapMarkerIcon} alt="Map Marker Icon" />
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
