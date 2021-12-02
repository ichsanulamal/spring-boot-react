import React, {Component} from "react";
import {Link} from "react-router-dom";
import ArrowBackIcon from '@mui/icons-material/ArrowBack';
import ShoppingCartCheckoutIcon from '@mui/icons-material/ShoppingCartCheckout';
import {Fab} from "@material-ui/core";
import Badge from "@material-ui/core/Badge";

import CartItem from "../../components/cartItem";
import APIConfig from "../../api/APIConfig";
import classes from "../itemlist/styles.module.css";

class Cart extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            isEmpy: true
        }

        this.handleCheckOut = this.handleCheckOut.bind(this);
    }

    componentDidMount() {
        this.loadData()
    }

    async loadData() {
        try {
            const {data} = await APIConfig.get("/cart");
            this.setState({items: data.result});
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    async handleCheckOut() {
        try {
            await APIConfig.get(`/cart/checkout`);
            this.setState({items: []});
            alert("Berhasil checkout wush!!!")
        } catch (error) {
            console.log(error)
        }

    }

    render() {

        return (
            <div className={classes.itemList}>
                <div style={{position: "fixed", top: 25, left: 25}}>
                    <Link to={"/list"}>
                        <Fab variant="extended">
                            <Badge color="secondary" badgeContent={this.state.cartItemSize}>
                                <ArrowBackIcon/>
                            </Badge>
                        </Fab>
                    </Link>
                </div>
                {(this.state.items.length > 0) ?
                    (<div style={{position: "fixed", top: 25, right: 25}}>
                        <Fab onClick={this.handleCheckOut} variant="extended">
                            <Badge color="secondary">
                                <ShoppingCartCheckoutIcon />
                            </Badge>
                        </Fab>
                    </div>)
                    :
                    (<div style={{position: "fixed", top: 200}}>
                        <h3>Belum ada item yang ditambahkan</h3>
                    </div>)

                }

                <h1 className={classes.title}>
                    Cart Items
                </h1>
                <div>
                    {this.state.items.map((item) => (
                        <CartItem key={item.id} id={item.id} title={item.item.title} price={item.item.price}
                                  jumlah={item.quantity} description={item.item.description}
                                  category={item.item.category}
                                  totalHarga={item.item.price * item.quantity}
                        />
                    ))}
                </div>
            </div>
        )
    };
}

export default Cart;