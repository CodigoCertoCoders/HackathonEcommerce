import React, { useContext, useState } from 'react';
import ProductCard from '../components/ProductComponents';
import FilterComponents from '../components/FilterComponents';
import { ProductContext } from '../context/ProductContext';
import ShoppingCart from '../assets/shopping_cart.svg';
import Person from '../assets/person.svg';
import Search from '../assets/search.svg';
import Adjustment from '../assets/adjustment.svg';
import styles from './css/ProductPage.module.css';
import productData from '../static/produtos.json';
import { Link, useNavigate } from 'react-router-dom';
import { AuthContext } from '../context/AuthProvider';

const ProductPage = () => {
  const {
    products,
    setProducts,
    activateFilter,
    setActivateFilter,
    activateMaisPedidos,
    setActivateMaisPedidos,
    cartProd,
    category,
    handleFilter,
  } = useContext(ProductContext);
  const [search, setSearch] = useState('');
  const [isModalOpen, setIsModalOpen] = useState(false);
  const { logout } = React.useContext(AuthContext);
  const itemCount = cartProd.length;

  const handleSearch = (event) => {
    setSearch(event.target.value);
  };

  const handleSubmit = (event) => {
    if (search === '') {
      setProducts(products);
    } else {
      const filtered = products.filter(
        (product) =>
          product.name.toLowerCase().includes(search.toLowerCase()) ||
          product.category.toLowerCase().includes(search.toLowerCase()),
      );
      setProducts(filtered);
    }

    setSearch('');
    setActivateMaisPedidos(false);
    event.preventDefault();
  };

  const toggleModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  const navigate = useNavigate();
  const handleClickAdress = () => {
    navigate('/adress');
  };

  return (
    <div>
      <section className={styles.container}>
        <div>
          <h2>Olá,</h2>
          <h3>UserName</h3>
        </div>

        <div
          className={styles.mediaQuery}
          style={{
            position: 'relative',
            width: '250px',
            marginTop: '10px',
            marginBottom: '10px',
          }}
        >
          <img
            src={Search}
            style={{
              position: 'absolute',
              left: '5px',
              top: '45%',
              transform: 'translateY(-50%)',
              color: 'black',
            }}
            alt="Ícone de busca"
          />
          <form className={styles.input} onSubmit={handleSubmit}>
            <input
              type="text"
              placeholder="Está procurando por..."
              value={search}
              onChange={handleSearch}
              style={{
                width: '100%',
                padding: '10px 10px 10px 30px',
                border: '1px solid #ccc',
              }}
              className={styles.input}
            />
          </form>

          <i
            style={{
              position: 'absolute',
              right: '-20px',
              top: '50%',
              transform: 'translateY(-50%)',
              color: '#888',
              cursor: 'pointer',
            }}
            className="fas fa-times"
          ></i>
          <select className={styles.select} name="Filtre">
            <option onClick={() => handleFilter('')}>Filtro</option>
            {category.map((cat, index) => (
              <option onClick={() => handleFilter(cat)} key={index} value={cat}>
                {cat}
              </option>
            ))}
          </select>
        </div>

        <div>
          <div className={styles.cartIconContainer}>
            <img
              onClick={toggleModal}
              className={styles.img}
              src={Person}
              alt="Ícone de pessoa"
              style={{ cursor: 'pointer' }}
            />
            {isModalOpen && (
              <div className={styles.modalContainer}>
                <div className={styles.modalBalloon}>
                  <button
                    className={styles.modalItem}
                    onClick={handleClickAdress}
                  >
                    Endereço
                  </button>
                  <button className={styles.modalItem} onClick={() => logout()}>
                    Logout
                  </button>
                </div>
                <div className={styles.modalArrow}></div>
              </div>
            )}
            <div className={styles.itemCount}>
              <p>{itemCount}</p>
            </div>
            <Link to={'/cart'}>
              <img
                className={styles.img}
                src={ShoppingCart}
                alt="Carrinho de compras"
              />
            </Link>
          </div>
        </div>
      </section>
      <section className={styles.containersearch}>
        <div
          style={{
            position: 'relative',
            width: '250px',
            marginTop: '10px',
            marginBottom: '10px',
          }}
        >
          <img
            src={Search}
            style={{
              position: 'absolute',
              left: '5px',
              top: '45%',
              transform: 'translateY(-50%)',
              color: 'black',
            }}
            alt="Ícone de busca"
          />
          <form onSubmit={handleSubmit}>
            <input
              type="text"
              placeholder="Está procurando por..."
              value={search}
              onChange={handleSearch}
              style={{
                width: '100%',
                padding: '10px 10px 10px 30px',
                border: '1px solid #ccc',
              }}
              className={styles.input}
            />
          </form>

          <i
            style={{
              position: 'absolute',
              right: '-20px',
              top: '50%',
              transform: 'translateY(-50%)',
              color: '#888',
              cursor: 'pointer',
            }}
            className="fas fa-times"
          ></i>
        </div>
        <img
          onClick={() => {
            setActivateFilter(true), setActivateMaisPedidos(false);
          }}
          className={styles.imgAdjustment}
          src={Adjustment}
          alt="Ícone de ajuste"
        />
      </section>
      <div
        className={`${''} ${
          activateMaisPedidos === true ? '' : styles.notFilterPage
        }`}
      >
        <h3>Mais pedidos</h3>
        <section className={styles.sectionMaisPedidos}>
          <div className={styles.catalogProd}>
            {products.slice(0, 5).map((prod) => (
              <Link
                to={`/catalog/${prod.uuid}`}
                className={styles.img}
                key={prod.uuid}
              >
                <img
                  src={`https://maltex-back-production.up.railway.app/assets/${prod.url}`}
                  className={styles.imgProd}
                  alt={prod.name}
                />
              </Link>
            ))}
          </div>
        </section>
      </div>

      <h3>Todas as bebidas</h3>

      <section className={styles.allProducts}>
        {products.map((prod) => (
          <ProductCard
            key={prod.uuid}
            name={prod.name}
            price={prod.price}
            photo={prod.url}
            id={prod.uuid}
          />
        ))}
      </section>
      <div
        className={`${styles.filterPage} ${
          activateFilter === true ? styles.filterPage : styles.notFilterPage
        }`}
      >
        <FilterComponents />
      </div>
    </div>
  );
};

export default ProductPage;
