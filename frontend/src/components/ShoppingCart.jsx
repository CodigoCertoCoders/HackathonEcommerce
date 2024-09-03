const ShoppingCart = () => {

  const Simulateproducts = [
  {
    name: 'pizza muçarela',
    adicionais: ['borda com recheio', 'coca cola'],
    preco: '80'
  },
  {
    name: 'pizza portuguesa',
    adicionais: ['milho verde','manjericão', 'sprite'],
    preco: '50'
  },
  {
    name: 'pizza Marguerita',
    adicionais: ['coco ralado', 'fanta'],
    preco: '34'
  },
];

  const handleSimulateToRenderItems = () => {

    return Simulateproducts?.map((product, i) => (
        <>
      <div key={i}>
        <p>{product.name}</p>
        <p>{product.adicionais}</p>
        <p>{product.preco}</p>
      </div>

        </>
    ));
  };

  return (
    <div
      tabIndex={0}
      className="card card-compact dropdown-content bg-base-100 z-[1] mt-3 w-full shadow overflow-y-auto"
    >
      <div className="card-body">
        <span className="text-lg font-bold">8 Itens</span>
        {handleSimulateToRenderItems()}
        <span className="text-info">Total: $999</span>
        <div className="card-actions">
          <button className="btn btn-primary btn-block">
            Finalizar pedido
          </button>
        </div>
      </div>
    </div>
  );
};

export default ShoppingCart;
