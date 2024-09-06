import styled from "styled-components";

const SectionContainer = styled.section`
  background-color: #8D40D7;
  display: flex;
  justify-content: space-between;
  padding: 2rem;

  div {
    width: 48%;
    padding: 1rem;
    text-align: center;
    color: #FBFCF9;
  }
`;

const Section = () => {
  return (
    <SectionContainer>
        <img src="../src/public/icons/home/gem.svg" />
      <div>
        <h4>Produtos Originais</h4>
        <span>Produtos 100% originais com garantia do fabricante.</span>
      </div>
      <img src="../src/public/icons/home/lock.svg" />
      <div>
        <h4>Segurança Garantida</h4>
        <span>Transações garantidas com máxima segurança.</span>
      </div>
      <img src="../src/public/icons/home/box-seam.svg" />
      <div>
        <h4>Frete Grátis</h4>
        <span>Oferecemos frete grátis para todo o Brasil.</span>
      </div>
      <img src="../src/public/icons/home/shield-check.svg" />
      <div>
        <h4>30 Dias de Garantia</h4>
        <span>Devolva seu pedido em até 30 dias.</span>
      </div>
    </SectionContainer>
  );
};

export default Section;
