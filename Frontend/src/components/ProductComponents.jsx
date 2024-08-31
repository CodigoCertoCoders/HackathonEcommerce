import styles from './css/ProductCard.module.css'
const ProductCard = () =>{

    return(
        <div className={styles.allProdCatalog}>
        <figure className={styles.imgProd}></figure>
             <div className={styles.legendaProd}>
                 <p>Nome do produto</p>
                 <p><strong>Preço</strong></p>
             </div>
     </div>
    )
}

export default ProductCard