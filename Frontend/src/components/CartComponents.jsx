import { useContext, useEffect } from 'react';
import style from './css/CartComponents.module.css'
import { ProductContext } from '../context/ProductContext';

const CartComponents = ({foto , nome, qtd , preco, id}) =>{
    const {  updateQtd } = useContext(ProductContext);

    return(
        <>
            <div className={style.container}>
            <section  >
                <img className={style.img} src={foto}/>
            </section>

            <section className={style.containerP}>
                <p><strong>{nome}</strong></p>
                <p>R${preco}</p>

            </section>

            <section className={style.containerButtons}>
                <button onClick={() => updateQtd(id , qtd + 1 )} className={style.buttonIncrement}>+</button>
                <p>{qtd}</p>
                <button onClick={() => updateQtd(id , qtd - 1 )} className={style.buttonDecrement}>-</button>
            </section>
           
            </div>
            <div>
            
            </div>
        </>
       

    )
}

export default CartComponents