import { createContext, useContext, useEffect, useState } from "react";
import productData from '../static/produtos.json'

export const ProductContext = createContext()

export const ProductProvider = ({children})=>{
    const [products , setProducts] = useState([])
    const [productsFilter , setProductsFilter] = useState([])
    const [activateFilter , setActivateFilter] = useState(false)
    const [activateMaisPedidos , setActivateMaisPedidos] = useState(true)

    useEffect(() => {
        setProducts(productData); // Carrega os produtos do arquivo JSON
    }, [productData]);  
    

    const handleFilter=(filter) =>{

        if(filter === ''){
            setProducts(productData)
            console.log(productData)
        }else{
            const filtered = products.filter(prod=>prod.category === filter)
            setProducts(filtered)
        }

       
    }


    return(
        <ProductContext.Provider value={{products,setProducts , productsFilter , setProductsFilter , handleFilter , activateFilter , setActivateFilter, activateMaisPedidos , setActivateMaisPedidos}}>
            {children}
        </ProductContext.Provider>
    )
} 