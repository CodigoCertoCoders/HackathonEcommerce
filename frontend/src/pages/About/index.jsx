export default function About() {
  return (
    <div className="flex flex-col items-center w-full min-h-screen">
      <main className="w-full">
        <section className="container px-10 py-16 mx-auto md:px-10">
          <div className="mx-20">
            <h2 className="font-poppins text-6xl font-normal text-black leading-[96px]">
              Sobre
            </h2>
            <h3 className="font-poppins text-2xl font-normal text-red-600 mt-2 text-shadow">
              Borcelle Pizza
            </h3>
            <p className="mt-1 text-lg text-gray-700">
              Conheça o sucesso da pizzaria entre os amigos!
            </p>

            <div className="flex flex-col items-center mt-8">
              <img
                src="equipe2/pizza-about.png"
                alt="Imagem de uma pizza deliciosa"
                className="w-full max-w-xl rounded-xl"
                style={{ aspectRatio: "400/380", objectFit: "cover", transform: "rotate(-0.11deg)" }}
              />

              <p className="font-poppins mt-40 text-lg text-justify font-light text-black leading-[40px]">
                O restaurante está aberto desde 1994, somos uma Pizzaria Tradicional, 
                oferecemos um ambiente para os clientes sentarem e desfrutarem das pizzas 
                no local. Normalmente possui um cardápio variado de pizzas, entradas, 
                bebidas e sobremesas, com ingredientes premium e combinações criativas, 
                muitas vezes com uma experiência gastronômica diferenciada, focando em 
                rapidez e preços acessíveis, utilizando ingredientes padronizados e 
                processos de produção acelerados. A qualidade da massa é fundamental, 
                podendo ser feita de forma artesanal, usando receitas tradicionais, ou 
                industrializada para maior praticidade. Tipos comuns incluem massa fina, 
                grossa, crocante, ou até mesmo massa integral.
              </p>
            </div>
          </div>
        </section>
      </main>
    </div>
  );
}
