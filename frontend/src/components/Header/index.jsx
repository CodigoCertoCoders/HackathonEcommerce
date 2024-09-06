import logo from '../../public/images/logo.webp'

import * as s from './styles'

const Header = () => {

  return (
    <s.Header>
      <div className='container'>
        <img src={logo} alt="" />
        <s.NavbarDesktop>
          <s.SearchBar>
            <s.Select>
              <option selected>Categorias</option>
              <option value='brinquedos'>brinquedos</option>
              <option value="camisetas">camisetas</option>
              <option value="canecas">canecas</option>
              <option value='games'>games</option>
              <option value="livros">livros</option>
            </s.Select>
            <s.Input type="text" id="" placeholder='Pesquisar pelo nome'/>
            <s.Button type='button'> 
              <i className="bi bi-search"></i>
            </s.Button>
          </s.SearchBar>
          <s.HeaderActions>
            <s.ActionsProfile>
              <i className="bi bi-person"></i>
              <span>Minha conta</span>
            </s.ActionsProfile>
            <s.ActionsBag>
              <i className="bi bi-handbag"></i>
              <span>Meu Carrinho</span>
            </s.ActionsBag>
          </s.HeaderActions>
        </s.NavbarDesktop>
      </div>
    </s.Header>
  )
}

export default Header