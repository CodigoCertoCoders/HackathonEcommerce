import { useContext, useState } from 'react';
import { useParams } from 'react-router-dom';
import { ProductContext } from '../context/ProductContext';
import style from './css/ProductDetails.module.css'

const ProductDetails = () => {
  const { id } = useParams();
  const { products , addToCart } = useContext(ProductContext);
  const item = products.find((prod) => prod.id === id);
  const [ qtd , setQtd] = useState(0)

  // console.log(item);

  if (!item) {
    return <h2>Produto não encontrado</h2>;
  }

  const increment = () =>{
   setQtd(qtd + 1)
  }

  const decrement = () =>{
    
    setQtd(qtd - 1)

    if(qtd > 0){
      setQtd(qtd - 1)
    }
  }
  return (
    <div>
        <section className={style.photo}>

          <img src={item.foto}/>
        
        </section>

        <section className={style.containerButton}>
          <div>
            <button className={style.buttonGreen} onClick={() => increment()}> + </button>
            {qtd}
            <button  className={style.buttonWhite}onClick={() => decrement()}> - </button>
          </div>
          
          <div >
            <p className={style.p}>R${item.preco }</p>
          </div>
        </section>

        <section className={style.description}>
          <h3>Sobre o produto</h3>
          <p>Lorem ipsum dolor sit amet consectetur adipisicing elit. Laborum sapiente architecto aspernatur provident totam magnam quibusdam voluptates eveniet quod nesciunt dolor alias quo inventore voluptatibus, aut consequuntur perspiciatis nemo ex!</p>
        </section>

        <section className={style.containerButtonCart}>
            <button className={style.buttonAdd}onClick={() =>addToCart(item , qtd)}>
              Adicionar ao carrinho
            </button>
            <button className={style.buttonComprar}>Comprar</button>
        </section>
    </div>
  );
};

export default ProductDetails;
