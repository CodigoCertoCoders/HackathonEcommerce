import { useContext, useEffect, useState } from 'react'
import styles from '../components/css/FilterComponents.module.css'
import close from '../assets/close.svg'
import { ProductContext } from '../context/ProductContext'
import productData from '../static/produtos.json'

const FilterComponents = () =>{

    const {products,setActivateFilter ,handleFilter, activateMaisPedidos , setAtivateMaisPedidos} = useContext(ProductContext)
    const [filter , setFilter] = useState('')
    const [categories , setCategories] = useState([])
    const css  = filter
    
    useEffect(()=>{
        const uniqueCategory = [...new Set(productData.map(prod => prod.category))]

        setCategories(uniqueCategory)
    }, [products])
    

    
    return(
        
        <div className={styles.container}>
            <div className={styles.headerFilter}>
                <h2>Filtrar por</h2>
                <div>

                    <img className={styles.xFilter} src={close} onClick={() => {setActivateFilter(false) , setFilter('')}}></img>
                </div>
            </div>

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
                <button onClick={()=>{handleFilter(filter), setActivateFilter(false)}} className={styles.button}>Buscar</button>
                <button onClick={() =>{handleFilter("")
                    setFilter('') , setAtivateMaisPedidos(true)
                }} className={styles.button}>Limpar Filtro</button>
            </div>
            

           
        </div>
    )
}

export default FilterComponents