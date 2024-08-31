import { useContext, useEffect, useState } from 'react'
import styles from '../components/css/FilterComponents.module.css'
import { ProductContext } from '../context/ProductContext'
import productData from '../static/produtos.json'

const FilterComponents = () =>{

    const {products,handleFilter} = useContext(ProductContext)
    const [filter , setFilter] = useState('')
    const [categories , setCategories] = useState([])
    const css  = filter
    
    useEffect(()=>{
        const uniqueCategory = [...new Set(productData.map(prod => prod.category))]

        setCategories(uniqueCategory)
    }, [products])
    

    
    return(
        <div className={styles.container}>
            <h2>Filtrar por</h2>
            <div className={styles.list}>
                {categories.map((category)=>(
                    <p key={`${category}`} 
                    onClick={() => setFilter(category)  }
                    className={`${styles.listDetail} ${css === category ? styles.listDetail2 : styles.listDetail}`}>
                        {category}
                    </p>
                ))}
            </div>
            
            <div className={styles.containerButton}>
                <button onClick={() =>handleFilter(filter)} className={styles.button}>Buscar</button>
                <button onClick={() =>{ handleFilter("")
                    setFilter('')
                }} className={styles.button}>Limpar Filtro</button>
            </div>
            

           
        </div>
    )
}

export default FilterComponents