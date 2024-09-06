import styled from "styled-components"

export const Header = styled.header`
  width: 100%;
  background-color: var(--color-text-white);
  padding: 30px 0;
`

export const NavbarDesktop = styled.nav`
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 40px;
`

export const SearchBar = styled.div`
  height: 42px;
  display: flex;
  align-items: center;
  justify-content: end;
`

export const Select = styled.select`
  height: 100%;
  padding: 0 4px 0 16px;
  background-color: rgba(73,80,87,0.1);
  border: 1px solid var(--color-border);
  border-radius: 8px 0 0 8px;
  font-size: 16px;
  color: var(--color-text-gray-dark);
  font-weight: 400;
`

export const Input = styled.input`
  width: 600px;
  height: 100%;
  padding: 0 0 0 10px;
  font-size: 16px;
  font-weight: 300;
  border: 1px solid var(--color-border);

  &::placeholder {
    font-size: 16px;
    font-weight: 100;
  }
`

export const Button = styled.button`
  height: 42px;
  background-color: var(--color--green);
  padding: 12px;
  font-size: 18px;
  border: none;
  border-radius: 0 8px 8px 0;
  color: var(--color--white);
`

export const HeaderActions = styled.div`
  display: flex;
  align-items: center;
  justify-content: end;
  gap: 10px;
  color: var(--color-text-gray-dark);

  i {
    font-size: 28px;
  }
`

export const ActionsProfile = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  flex-direction: column;
  gap: 4px;
  cursor: pointer;

  &:hover {
    color: var(--color-purple);
  }

  span {
    font-size: 10px;
    font-weight: 400;
  }
`

export const ActionsBag = styled.div`
  display: flex;
  align-items: center;
  justify-content: start;
  flex-direction: column;
  gap: 4px;
  cursor: pointer;

  &:hover {
    color: var(--color-purple);
  }

  span {
    font-size: 10px;
    font-weight: 400;
  }
`