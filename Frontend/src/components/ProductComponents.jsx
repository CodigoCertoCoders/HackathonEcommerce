import { Link } from 'react-router-dom'
import styles from './css/ProductCard.module.css'
const ProductCard = ({name, price , photo , id}) =>{

    return(
        <div className={styles.allProdCatalog}>
        <Link to={`/catalog/${id}`}>
            <img src={photo} className={styles.imgProd}/>
                <div className={styles.legendaProd}>
                
                    <p>{name}</p>
                    <p><strong>R${price}</strong></p>
                </div>
        </Link>
     </div>
    )
}

export default ProductCard