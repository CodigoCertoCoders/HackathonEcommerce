import React, { useState } from 'react';
import backArrowImg from '../assets/back_arrow.svg';
import searchIcon from '../assets/search.svg';
import targetIcon from '../assets/target.svg';
import mapMarkerIcon from '../assets/map-marker.svg';
import styles from './css/AdressPage.module.css';
import fonts from '../fonts/fonts.module.css';
import { useNavigate } from 'react-router-dom';

const AdressPage = () => {
  const [bairro, setBairro] = useState('');
  const [logradouro, setLogradouro] = useState('');
  const [numero, setNumero] = useState('');
  const [complemento, setComplemento] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const [isEditing, setIsEditing] = useState(false);
  const [cep, setCep] = useState('');
  const [addressToSave, setAddressToSave] = useState({}); // New state to hold the address before confirmation

  const fetchAddressFromCep = async (cep) => {
    const response = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
    const responseJson = await response.json();
    if (responseJson.erro) {
      alert('CEP não encontrado');
      return;
    }
    setAddressToSave({
      bairro: responseJson.bairro,
      logradouro: responseJson.logradouro,
      numero: '',
      complemento: '',
    });
    setIsModalOpen(true);
  };

  const handleChangeCep = async ({ target }) => {
    const inputCep = target.value;
    setCep(inputCep);
    if (inputCep.length === 8) {
      await fetchAddressFromCep(inputCep);
    }
  };

  const handleUseCurrentLocation = async () => {
    if (navigator.geolocation) {
      navigator.geolocation.getCurrentPosition(
        async (position) => {
          const { latitude, longitude } = position.coords;

          // Obtém o endereço a partir das coordenadas
          const response = await fetch(
            `https://nominatim.openstreetmap.org/reverse?lat=${latitude}&lon=${longitude}&format=json&addressdetails=1`,
          );
          const responseJson = await response.json();
          const address = responseJson.address;

          // Preenche o estado com os dados obtidos
          setAddressToSave({
            bairro:
              address.suburb ||
              address.neighbourhood ||
              address.city ||
              'Localização não encontrada',
            logradouro: address.road || 'Rua não encontrada',
            numero: address.house_number || 'Número não encontrado',
            complemento: address.additional_info || '',
          });

          setIsModalOpen(true);
        },
        (error) => {
          console.error('Erro ao obter localização:', error);
          alert('Erro ao obter localização: ' + error.message);
        },
      );
    } else {
      alert('Geolocalização não é suportada pelo seu navegador.');
    }
  };

  const handleSubmit = (event) => {
    event.preventDefault();
    setBairro(addressToSave.bairro);
    setLogradouro(addressToSave.logradouro);
    setNumero(addressToSave.numero);
    setComplemento(addressToSave.complemento);
    setIsModalOpen(false);
    setIsEditing(false);
  };

  const handleEdit = () => {
    setIsEditing(true);
    setIsModalOpen(true); // Open modal for editing
  };

  const navigate = useNavigate();
  const handleBackArrow = () => {
    navigate(-1);
  };

  return (
    <section className={styles.section}>
      <header className={styles.header}>
        <img onClick={handleBackArrow} src={backArrowImg} alt="Back Arrow" />
        <h2 className={fonts.robotoBold}>Endereço</h2>
      </header>

      <div className={styles.searchInput}>
        <input
          value={cep}
          onChange={handleChangeCep}
          placeholder="Buscar cep"
          maxLength="8"
        />
      </div>

      <article className={styles.article}>
        <div
          className={styles.divUseCurrentlyLocation}
          onClick={handleUseCurrentLocation}
        >
          <img src={targetIcon} alt="Target Icon" />
          <h3 className={fonts.latoBold}>Usar localização atual</h3>
        </div>

        {bairro && logradouro && complemento && numero && (
          <div className={styles.myAdress}>
            <h2 className={fonts.latoBold}>Meu endereço</h2>
            <div className={styles.cardUserAdress}>
              <img src={mapMarkerIcon} alt="Map Marker Icon" />
              <div className={styles.cardUserAdressInfo}>
                <h3 className={fonts.latoBold}>{bairro}</h3>
                <h4 className={fonts.latoMedium}>{logradouro}</h4>
              </div>
              <p onClick={handleEdit} className={styles.editText}>
                Editar
              </p>
            </div>
          </div>
        )}
      </article>

      {/* Modal */}
      {isModalOpen && (
        <div className={styles.modal}>
          <div className={styles.modalContent}>
            <h2 className={fonts.latoBold}>
              {isEditing ? 'Editar Endereço' : 'Confirme seu Endereço'}
            </h2>
            <form onSubmit={handleSubmit}>
              <label>
                Bairro:
                <input
                  type="text"
                  value={addressToSave.bairro || bairro}
                  onChange={(e) =>
                    setAddressToSave((prev) => ({
                      ...prev,
                      bairro: e.target.value,
                    }))
                  }
                  readOnly={!isEditing}
                  required
                />
              </label>
              <label>
                Logradouro:
                <input
                  type="text"
                  value={addressToSave.logradouro || logradouro}
                  onChange={(e) =>
                    setAddressToSave((prev) => ({
                      ...prev,
                      logradouro: e.target.value,
                    }))
                  }
                  readOnly={!isEditing}
                  required
                />
              </label>
              <label>
                Número:
                <input
                  type="text"
                  value={addressToSave.numero || numero}
                  onChange={(e) =>
                    setAddressToSave((prev) => ({
                      ...prev,
                      numero: e.target.value,
                    }))
                  }
                  required
                />
              </label>
              <label>
                Complemento:
                <input
                  type="text"
                  value={addressToSave.complemento || complemento}
                  onChange={(e) =>
                    setAddressToSave((prev) => ({
                      ...prev,
                      complemento: e.target.value,
                    }))
                  }
                />
              </label>
              <button type="submit" className={styles.submitButton}>
                Confirmar
              </button>
              <button
                type="button"
                onClick={() => setIsModalOpen(false)}
                className={styles.cancelButton}
              >
                Cancelar
              </button>
            </form>
          </div>
        </div>
      )}
    </section>
  );
};

export default AdressPage;
