import { Link } from 'react-router-dom';
import React from 'react';
import styles from './css/ProductCard.module.css';
import { AuthContext } from '../context/AuthProvider';
import { useNavigate } from 'react-router-dom';
const ProductCard = ({ name, price, photo, id }) => {
  const { isAuthenticated } = React.useContext(AuthContext);

  const navigate = useNavigate();
  const handleGuestUser = (e) => {
    if (!isAuthenticated) {
      e.preventDefault();
      navigate('/');
    }
  };

  return (
    <div className={styles.allProdCatalog}>
      <Link to={`/catalog/${id}`} onClick={handleGuestUser}>
        <img src={photo} className={styles.imgProd} />
        <div className={styles.legendaProd}>
          <p>{name}</p>
          <p>
            <strong>R${price}</strong>
          </p>
        </div>
      </Link>
    </div>
  );
};

export default ProductCard;
