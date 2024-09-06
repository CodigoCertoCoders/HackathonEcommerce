import { createContext, useContext, useEffect, useState } from "react";
import productData from '../static/produtos.json'

export const ProductContext = createContext()

export const ProductProvider = ({children})=>{
    const [products , setProducts] = useState([])
    const [productsFilter , setProductsFilter] = useState([])
    const [activateFilter , setActivateFilter] = useState(false)
    const [activateMaisPedidos , setActivateMaisPedidos] = useState(true)
    const [cartProd , setCartProd] = useState(() =>{
      try {
        // Tenta pegar o carrinho do localStorage
        const savedCart = localStorage.getItem("cartProd");
        
        // Se existe e não é vazio, parseia, caso contrário, retorna um array vazio
        return savedCart ? JSON.parse(savedCart) : [];
      } catch (error) {
        console.error("Erro ao carregar o carrinho do localStorage:", error);
        return [];
      }
    })

    useEffect(() => {
        setProducts(productData); // Carrega os produtos do arquivo JSON
    }, [productData]);  

    useEffect(() => {
      //Aqui atualizo o carrinho sempre que o estado for atualizado
      localStorage.setItem('cartProd' , JSON.stringify(cartProd));
  }, [cartProd]);  


    //CHAMADA DA API PARA OS PRODUTOS
  //   useEffect(() => {
  //     const url = "https://maltex-back-production.up.railway.app/products"

  //     fetch(url)
  //     .then(response =>{
  //       if(!response){
  //         throw new Error("Produto nao encontrado")
  //       }
  //       setProducts(response.json())
  //       return response.json()
  //     })
  // }, [products]);  

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
        setCartProd((prevcartProd) => {
          // Verifica se prevcartProd é um array
          if (!Array.isArray(prevcartProd)) {
            console.error('O estado prevcartProd não é um array:', prevcartProd);
            return []; // Retorna um array vazio ou o estado anterior se necessário
          }
      
          // Verifica se o produto já está no carrinho
          const existItem = prevcartProd.find((item) => item.id === prod.id);

      
          if (existItem) {
            // Se o produto já está no carrinho, atualiza a quantidade
            const novoCart = prevcartProd.map((item) =>
              item.id === prod.id
                ? { ...item, qtd: item.qtd + qtd }
                : item
            );
      
            // console.log("Atualizando produto:", novoCart);
            return novoCart;
          } else {
            // Se o produto não está no carrinho, adiciona-o com a quantidade inicial
            if(qtd === 0 ){
              alert("Insira a quantidade do produto")
            }else{
              const result = { ...prod, qtd: qtd };
              // console.log("Adicionando novo produto:", cartProd);
              return [...prevcartProd, result];
            }
            
          }
        });
      };
      //Função para limpar o carrinho de uma vez
      const removeCart = () =>{
        setCartProd([])
        localStorage.removeItem('cartProd')
      }
      
      

    return(
        <ProductContext.Provider value={{products,setProducts , productsFilter , setProductsFilter , handleFilter , activateFilter , setActivateFilter, addToCart, activateMaisPedidos , setActivateMaisPedidos , cartProd}}>
            {children}
        </ProductContext.Provider>
    )
} 