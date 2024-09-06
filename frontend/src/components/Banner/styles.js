import styled from "styled-components"

export const Banner = styled.section`
  width: 100%;
  background-color: var(--color-purple);
  color: var(--color-text-white);
  font-weight: 600;
  font-size: 14px;
  padding: 20px 0;
`

export const DiscountText = styled.div`
  display: flex;
  align-items: center;
  justify-content: start;
  text-align: start;
`

export const Shippment = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 4px;

  i {
    font-size: 18px;
  }
`

export const Socials = styled.div`
  display: flex;
  align-items: center;
  justify-content: end;
  gap: 10px;
  font-size: 24px;

  img {
    cursor: pointer;
    
    &:hover {
      opacity: 0.7;
    }
  }


`