import React, { useContext, useEffect } from "react";
import ProductCard from "../components/ProductComponents";
import productsData from '../static/produtos.json';
import FilterComponents from "../components/FilterComponents";
import { ProductContext } from "../context/ProductContext";
import ShoppingCart from '../assets/shopping_cart.svg';
import Person from '../assets/person.svg';
import Search from '../assets/search.svg';
import Adjustment from '../assets/adjustment.svg';
import styles from './css/ProductPage.module.css';

const ProductPage = () => {
  const { products } = useContext(ProductContext);

 console.log("ProductPage" , products)

  return (
    <div>
      <section className={styles.container}>
        <div>
          <h2>Ola,</h2>
          <h3>UserName</h3>
        </div>
        <div>
          <img className={styles.img} src={ShoppingCart} alt="Carrinho de compras" />
          <img className={styles.img} src={Person} alt="Ícone de pessoa" />
        </div>
      </section>
      <section className={styles.containersearch}>
        <div style={{ position: 'relative', width: '250px', marginTop: '10px', marginBottom: '10px' }}>
          <img src={Search}
            style={{
              position: 'absolute',
              left: '5px',
              top: '45%',
              transform: 'translateY(-50%)',
              color: "black"
            }}
            alt="Ícone de busca"
          />
          <input
            type="text"
            placeholder="Esta procurando por..."
            style={{
              width: '100%',
              padding: '10px 10px 10px 30px',
              border: '1px solid #ccc',
            }}
            className={styles.input}
          />
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
        <img className={styles.imgAdjustment} src={Adjustment} alt="Ícone de ajuste" />
      </section>

      <h3>Mais pedidos</h3>
      <section className={styles.sectionMaisPedidos}>
        <div className={styles.catalogProd}>
          {products.map((prod) => (
            <img key={prod.nome} src={prod.foto} className={styles.imgProd} alt={prod.nome} />
          ))}
        </div>
      </section>

      <h3>Todas as bebidas</h3>
        
      <section className={styles.allProducts}>
        {products.map((prod) => (
          <ProductCard
            key={prod.id}
            name={prod.nome}
            price={prod.preco}
            photo={prod.foto}
          />
        ))}

      </section>
      <div className={styles.filterPage}>
          <FilterComponents />
        </div>
    </div>
  );
}

export default ProductPage;
