import styles from './css/ProductCard.module.css'
const ProductCard = ({name, price , photo}) =>{

    return(
        <div className={styles.allProdCatalog}>
        <img src={photo} className={styles.imgProd}/>
             <div className={styles.legendaProd}>
                 <p>{name}</p>
                 <p><strong>R${price}</strong></p>
             </div>
     </div>
    )
}

export default ProductCard