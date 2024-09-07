import { useContext, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { ProductContext } from '../context/ProductContext';
import style from './css/ProductDetails.module.css';
import arrow from '../assets/arrow_back.svg';
import cart from '../assets/shopping_Cart.svg';
import { toast } from 'react-toastify';

const ProductDetails = () => {
  const { id } = useParams();
  const { products, addToCart } = useContext(ProductContext);
  const item = products.find((prod) => prod.uuid === id);
  const [qtd, setQtd] = useState(0);

  const notify = () => {
    toast.success('Adicionado com sucesso', {
      position: "bottom-right",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
      theme: "light",
      });
  };

  if (!item) {
    return <h2>Produto não encontrado</h2>;
  }

  const increment = () => {
    setQtd(qtd + 1);
  };

  const decrement = () => {
    setQtd(qtd > 1 ? qtd - 1 : 0);
  };

  return (
    <div>
      <section className={style.header}>
        <div>
          <Link className={style.headerLink} to="/catalog">
            <img src={arrow} alt="Back" />
            <p>Back</p>
          </Link>
        </div>
        <div className={style.headerLink}>
          <Link to="/cart">
            <img src={cart} alt="Cart" />
          </Link>
        </div>
      </section>
      <div className={style.containerResp}>
        <div className={style.containerPhoto}>
          <section className={style.photo}>
            <img
              src={`https://maltex-back-production.up.railway.app/assets/${item.url}`}
              alt={item.name}
              className={style.prodImg}
            />
          </section>
          <section className={style.containerButton}>
            <div>
              <button className={style.buttonGreen} onClick={increment}>
                +
              </button>
              {qtd}
              <button className={style.buttonWhite} onClick={decrement}>
                -
              </button>
            </div>
            <div>
              <p className={style.p}>R${item.price}</p>
            </div>
          </section>
        </div>
        <div className={style.containerDesc}>
          <section className={style.description}>
            <h3>Sobre o produto</h3>
            <p>{item.description}</p>
          </section>
          <section className={style.containerButtonCart}>
            <button
              className={style.buttonAdd}
              onClick={() => {
                addToCart(item, qtd);
                notify(); // Exibe o toast ao adicionar ao carrinho
              }}
            >
              Adicionar ao carrinho
            </button>
            <Link to="/cart">
              <button className={style.buttonComprar}>Comprar</button>
            </Link>
          </section>
        </div>
      </div>
    </div>
  );
};

export default ProductDetails;
