import x from '../../public/icons/home/x-icon.svg'
import whatsapp from '../../public/icons/home/icon-whats.svg'
import facebook from '../../public/icons/home/header-facebook.svg'
import instagram from '../../public/icons/home/icon-instagram.svg'

import * as s from './styles'

function Banner() {
  return (
    <s.Banner>
      <div className='container'>
        <s.DiscountText>
          <span>
            Descontos de até 50% por tempo limitado!
          </span>
        </s.DiscountText>
        <s.Shippment>
          Frete grátis para todo o Brasil
          <i className="bi bi-truck"></i>
        </s.Shippment>
        <s.Socials>
          <img src={x} alt="Ícone rede social X" />
          <img src={whatsapp} alt="Ícone whatsapp" />
          <img src={facebook} alt="Ícone facebook" />
          <img src={instagram} alt="ícone instagram" />
        </s.Socials>
      </div>
    </s.Banner>
  )
}

export default Banner
