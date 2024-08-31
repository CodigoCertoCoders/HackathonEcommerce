import Logo from "../components/Logo"
import ShoppingCart from '../assets/shopping_cart.svg'
import Person from '../assets/person.svg'
import Search from '../assets/search.svg'
import Adjustment from '../assets/adjustment.svg'
import styles from './css/ProductPage.module.css'
import '@fortawesome/fontawesome-free/css/all.min.css'; // Importando o Font Awesome
import { useState } from "react"
import ProductCard from "../components/ProductComponents"

const ProductPage = () =>{

    const [product , setProduct] = useState('')

    //Trata a mudança do input
    const handleChange = (event) =>{
        setProduct(event.target.value)
    }

    //Lida com o envio do formulario
    const handleSubmit = (event) =>{
        event.preventDefault();
        alert(`O produto procurado é ${product}`)
    }
    return(
        <div>
            <section className={styles.container}>
                <div>
                    <h2>Ola,</h2>
                    <h3>UserName</h3>
                </div>
                <div>
                    <img className={styles.img} src={ShoppingCart}></img>
                    <img className={styles.img} src={Person}></img>
                </div>
            </section>
            <section className={styles.containersearch}> 
                <div style={{ position: 'relative', width: '250px', marginTop:'10px' , marginBottom:'10px'}}>
                <img src={Search}
                    style={{
                    position: 'absolute',
                    left: '5px',
                    top: '45%',
                    transform: 'translateY(-50%)',
                    color:"black"
                    }}
                ></img>
                <input
                    type="text"
                    placeholder="Esta procurando por..."
                    style={{
                    width: '100%',
                    padding: '10px 10px 10px 30px', // Espaço para o ícone à esquerda
                    border: '1px solid #ccc',
                    }}
                    className={styles.input}
                />
                <i  style={{
                        position: 'absolute',
                        right: '-20px',
                        top: '50%',
                        transform: 'translateY(-50%)',
                        color: '#888',
                        cursor: 'pointer',}}className="fas fa-times"></i>
               
               
                </div>
                <img  className={styles.imgAdjustment}src={Adjustment}></img>
            </section>

            <h3>Mais pedidos</h3>
            <section className={styles.sectionMaisPedidos}>
            <div className={styles.catalogProd}>
                <div className={styles.containerImg}></div>
                <div className={styles.containerImg}></div>
                <div className={styles.containerImg}></div>
                <div className={styles.containerImg}></div>
                <div className={styles.containerImg}></div><div className={styles.containerImg}></div><div className={styles.containerImg}></div><div className={styles.containerImg}></div>
                {/* Adicione mais itens aqui se necessário */}
            </div>
        </section>

        <h3>Todas as bebidas</h3>
        <section className={styles.allProducts}>
            <ProductCard></ProductCard>
            <ProductCard></ProductCard>
            <ProductCard></ProductCard>
            <ProductCard></ProductCard>
            <ProductCard></ProductCard>
            <ProductCard></ProductCard>
            <ProductCard></ProductCard>
            <ProductCard></ProductCard>

        </section>
        </div>
        
        
    )
}


export default ProductPage