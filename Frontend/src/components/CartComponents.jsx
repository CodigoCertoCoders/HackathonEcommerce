import style from './css/CartComponents.module.css'

const CartComponents = ({foto , nome, qtd , preco}) =>{

    return(
        <>
            <div className={style.container}>
            <section  >
                <img className={style.img} src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQdWLJMG-XuQjyntqvv-9zkuVubhgndVXdCMw&s"/>
            </section>

            <section className={style.containerP}>
                <p><strong>{nome}</strong></p>
                <p>R${preco}</p>

            </section>

            <section className={style.containerButtons}>
                <button className={style.buttonIncrement}>+</button>
                <p>{qtd}</p>
                <button className={style.buttonDecrement}>-</button>
            </section>
           
            </div>
            <div>
            
            </div>
        </>
       

    )
}

export default CartComponents