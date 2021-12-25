import React, {Component} from "react";
import Layout from "../../components/layout";
import ItemList from "../../containers/itemlist";

class Home extends Component {
    render() {
        return (
            <Layout>
                <ItemList />
            </Layout>
        );
    }
}
export default Home;