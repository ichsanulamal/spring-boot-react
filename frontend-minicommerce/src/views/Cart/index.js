import React, {Component} from "react";
import Layout from "../../components/layout";
import Cart from "../../containers/cart";

class CartIndex extends Component {
    render() {
        return (
            <Layout>
                <Cart />
            </Layout>
        );
    }
}
export default CartIndex;