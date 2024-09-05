import { createContext, useContext, useEffect, useState } from "react";
import productData from '../static/produtos.json'

export const ProductContext = createContext()

export const ProductProvider = ({children})=>{
    const [products , setProducts] = useState([])
    const [productsFilter , setProductsFilter] = useState([])
    const [activateFilter , setActivateFilter] = useState(false)
    const [activateMaisPedidos , setActivateMaisPedidos] = useState(true)
    const [cardProd , setCardProd] = useState([])

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

    const addToCart = (prod, qtd) => {
        setCardProd((prevCardProd) => {
          // Verifica se prevCardProd é um array
          if (!Array.isArray(prevCardProd)) {
            console.error('O estado prevCardProd não é um array:', prevCardProd);
            return []; // Retorna um array vazio ou o estado anterior se necessário
          }
      
          // Verifica se o produto já está no carrinho
          const existItem = prevCardProd.find((item) => item.id === prod.id);
      
          if (existItem) {
            // Se o produto já está no carrinho, atualiza a quantidade
            const novoCart = prevCardProd.map((item) =>
              item.id === prod.id
                ? { ...item, qtd: item.qtd + qtd }
                : item
            );
      
            console.log("Atualizando produto:", novoCart);
            return novoCart;
          } else {
            // Se o produto não está no carrinho, adiciona-o com a quantidade inicial
            const result = { ...prod, qtd: qtd };
            console.log("Adicionando novo produto:", result);
            return [...prevCardProd, result];
          }
        });
      };
      
      

    return(
        <ProductContext.Provider value={{products,setProducts , productsFilter , setProductsFilter , handleFilter , activateFilter , setActivateFilter, addToCart, activateMaisPedidos , setActivateMaisPedidos}}>
            {children}
        </ProductContext.Provider>
    )
} 