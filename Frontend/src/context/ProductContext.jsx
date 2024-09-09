import { createContext, useContext, useEffect, useState } from 'react';

export const ProductContext = createContext();

export const ProductProvider = ({ children }) => {
  const [products, setProducts] = useState([]);
  const [productsData, setProductsData] = useState([]);
  const [productsFilter, setProductsFilter] = useState([]);
  const [activateFilter, setActivateFilter] = useState(false);
  const [activateMaisPedidos, setActivateMaisPedidos] = useState(true);
  const [category, setCategories] = useState([]);

  const [cartProd, setCartProd] = useState(() => {
    try {
      // Tenta pegar o carrinho do localStorage
      const savedCart = localStorage.getItem('cartProd');

      // Se existe e não é vazio, parseia, caso contrário, retorna um array vazio
      return savedCart ? JSON.parse(savedCart) : [];
    } catch (error) {
      console.error('Erro ao carregar o carrinho do localStorage:', error);
      return [];
    }
  });

  console.log(products);
  useEffect(() => {
    //Aqui atualizo o carrinho sempre que o estado for atualizado
    localStorage.setItem('cartProd', JSON.stringify(cartProd));
  }, [cartProd]);

  //CHAMADA DA API PARA OS PRODUTOS
  useEffect(() => {
    const url = 'https://maltex-back-production.up.railway.app/products';

    fetch(url, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json',
      },
    })
      .then((response) => response.json())
      .then((data) => {
        setProducts(data.products), setProductsData(data.products);
      })
      .catch((error) => console.error('Erro:', error));
  }, []);

  useEffect(() => {
    const uniqueCategory = [
      ...new Set(productsData.map((prod) => prod.category)),
    ];

    setCategories(uniqueCategory);
  }, [products]);

  const handleFilter = (filter) => {
    if (filter === '') {
      setProducts(productsData);
    } else {
      const filtered = products.filter((prod) => prod.category === filter);
      console.log(filtered);
      setProducts(filtered);
    }
  };

  const addToCart = (prod, qtd) => {
    setCartProd((prevcartProd) => {
      // Verifica se prevcartProd é um array
      if (!Array.isArray(prevcartProd)) {
        console.error('O estado prevcartProd não é um array:', prevcartProd);
        return []; // Retorna um array vazio ou o estado anterior se necessário
      }

      // Verifica se o produto já está no carrinho
      const existItem = prevcartProd.find((item) => item.uuid === prod.id);

      if (existItem) {
        // Se o produto já está no carrinho, atualiza a quantidade
        const novoCart = prevcartProd.map((item) =>
          item.uuid === prod.id ? { ...item, qtd: item.qtd + qtd } : item,
        );

        // console.log("Atualizando produto:", novoCart);
        return novoCart;
      } else {
        // Se o produto não está no carrinho, adiciona-o com a quantidade inicial
        if (qtd === 0) {
          alert('Insira a quantidade do produto');
        } else {
          const result = { ...prod, qtd: qtd };
          // console.log("Adicionando novo produto:", cartProd);
          return [...prevcartProd, result];
        }
      }
    });
  };
  //Função para limpar o carrinho de uma vez
  const removeCart = () => {
    setCartProd([]);
    localStorage.removeItem('cartProd');
  };

  const updateQtd = (prodId, qtd) => {
    setCartProd((prevCart) => {
      const update = prevCart
        .map((item) => (item.uuid === prodId ? { ...item, qtd: qtd } : item))
        .filter((item) => item.qtd > 0);

      localStorage.setItem('cartProd', JSON.stringify(update));
      console.log('Atualizou o valor', update);
      return update;
    });
  };

  return (
    <ProductContext.Provider
      value={{
        products,
        setProducts,
        productsFilter,
        setProductsFilter,
        handleFilter,
        activateFilter,
        setActivateFilter,
        addToCart,
        activateMaisPedidos,
        category,
        setActivateMaisPedidos,
        cartProd,
        updateQtd,
      }}
    >
      {children}
    </ProductContext.Provider>
  );
};
