import { useContext, useEffect, useState } from 'react';
import { ProductContext } from '../context/ProductContext';
import CartComponents from '../components/CartComponents';
import style from './css/CartPage.module.css'
import bag from '../assets/shopping_bag.svg'
import line from '../assets/horizontal_rule.svg'
import arrow  from '../assets/arrow_back.svg'
import { Link } from 'react-router-dom';


const CartPage = () =>{
    const { cartProd, updateQtd } = useContext(ProductContext);
    const [ total , setValorTotal] = useState(0)
    const [opacity , setOpacity] = useState(1)
    const [isActivate , setActivate] = useState(false)
    
    const frete = parseFloat(4.50)

    useEffect(() => {
       
        const totalPrice = cartProd.map(item => item.qtd * item.preco);
        const sum = totalPrice.reduce((total, value) => total + value, 0);
        setValorTotal(parseFloat(sum.toFixed(2)))
        
        
    }, [cartProd]); // Recalcula o total quando cartProd mudar


    const handleActivate = () =>{
        if(isActivate === true){
            setActivate(false)
        }else{
            setActivate(true)
        }

      
    }

    return(
        <div className={style.container}>

            <div >
                <Link to={"/catalog"} className={style.headerCart}  >
                    <img src={arrow}/> <p>Back</p>
                </Link>
            </div>
            <h2>Meu carrinho</h2>
            <section className={style.sectionCart}>
                {cartProd.length > 0 ? (<div>
                    {cartProd.map((itens)=>(
                        <CartComponents 
                        nome={itens.nome}
                        qtd={itens.qtd}
                        foto={itens.foto}
                        preco={itens.preco}
                        id={itens.id}/>
                    ))}
                </div>): <p>O carrinho esta vazio</p>}
              
        </section>
        <div onClick={() => handleActivate()} className={`${style.control} ${isActivate === false ? style.control : style.noControl} `}>

            <img src={line}/>
        </div>
        <section className={`${style.sectionCardTotalVisivel} ${isActivate=== true ? style.sectionCardTotalVisivel : style.sectionCardTotalInvisivel}`}>

            <div onClick={() => handleActivate()} className={`${style.control} ${isActivate === true ? style.control : style.noControl} `}>

                <img src={line}/>
            </div>
            <div className={style.sectionValue}>
                <p>Subtotal</p>
                R${total}
            </div>

            <div className={style.sectionValue}>
                <p>Total de entrega</p>
                <p>R$4,50</p>
            </div>

            <div className={style.sectionTotal}>
                <p>Total</p>
                <p>R${total + frete}</p>
            </div>
            
            <div className={style.divButton}>
                <button className={style.button}>
                    <img src={bag}/>
                    Checkout
                </button>
            </div>
        </section>
        </div>
    )
}

export default CartPage