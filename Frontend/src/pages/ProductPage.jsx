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
import { Link } from 'react-router-dom';
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
  const [isModalOpen, setIsModalOpen] = useState(false); // Estado do modal
  const itemCount = cartProd.length;

  const { logout } = React.useContext(AuthContext);

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

    setSearch(''); // Limpa o campo de pesquisa
    setActivateMaisPedidos(false); // Volta a mostrar os produtos mais vendidos
    event.preventDefault(); // Impede o recarregamento da página
  };

  // Função para abrir/fechar o modal
  const toggleModal = () => {
    setIsModalOpen(!isModalOpen);
  };

  return (
    <div>
      <section className={styles.container}>
        <div>
          <h2>Ola,</h2>
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
            {/* Ícone de usuário com clique para abrir modal */}
            <img
              className={styles.img}
              src={Person}
              alt="Ícone de pessoa"
              onClick={toggleModal}
              style={{ cursor: 'pointer' }}
            />

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

        {isModalOpen && (
          <div className={styles.modalOverlay} onClick={toggleModal}>
            <div
              className={styles.modalContent}
              onClick={(e) => e.stopPropagation()}
            >
              <h4>Opções de Conta</h4>
              <ul>
                <li>
                  <Link to="/adress">Endereço</Link>
                </li>
                <li>
                  <button
                    onClick={() => {
                      logout();
                    }}
                  >
                    Log Out
                  </button>
                </li>
              </ul>
            </div>
          </div>
        )}
      </section>

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
    </div>
  );
};

export default ProductPage;
