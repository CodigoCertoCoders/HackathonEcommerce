    import { useContext } from "react"
import { useParams } from "react-router-dom"
import { ProductContext } from "../context/ProductContext"



    const ProductDetails = () =>{
        const {id} = useParams()
        const {products} = useContext(ProductContext)
        const item = products.find((prod) => prod.id === id)

        console.log(products)

        if(!item){
            return<h2>Produto não encontrado</h2>
        }
        return(
            <div>
                <h2>Detalhes dos produtos</h2>
            </div>
        )
    }


    export default ProductDetails