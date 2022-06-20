import React, {Component} from "react";
import Item from "../../components/Item";
import classes from "./styles.module.css";
import APIConfig from "../../api/APIConfig";
import Button from "../../components/button";
import Modal from "../../components/modal";
import SearchBar from "../../components/search";

import Badge from "@material-ui/core/Badge";
import ShoppingCartIcon from '@mui/icons-material/ShoppingCart';
import {Fab} from "@material-ui/core";

import {Link} from "react-router-dom";

class ItemList extends Component {
    constructor(props) {
        super(props);
        this.state = {
            items: [],
            isLoading: false,
            isCreate: false,
            isEdit: false,

            id: "",
            title: "",
            price: 0,
            description: "",
            category: "",
            quantity: 0,

            cartItems: [],
            cartItemSize:0,
            qtyToCart: 0
        };

        this.handleAddItem = this.handleAddItem.bind(this);
        this.handleCancel = this.handleCancel.bind(this);
        this.handleChangeField = this.handleChangeField.bind(this);
        this.handleClickLoading = this.handleClickLoading.bind(this);
        this.handleEditItem = this.handleEditItem.bind(this);
        this.handleDeleteItem = this.handleDeleteItem.bind(this);
        this.handleSubmitItem = this.handleSubmitItem.bind(this);
        this.handleSubmitEditItem = this.handleSubmitEditItem.bind(this);
        this.handleChangeSearch = this.handleChangeSearch.bind(this);
        this.handleAddToCart = this.handleAddToCart.bind(this);
    }

    shouldComponentUpdate(nextProps, nextState) {
        console.log("shouldComponentUpdate()");
        return true;
    }

    handleClickLoading() {
        const currentLoading = this.state.isLoading;
        this.setState({isLoading: !currentLoading});
        console.log(this.state.isLoading);
    }

    componentDidMount() {
        this.loadData()
    }

    async loadData() {
        try {
            let {data} = await APIConfig.get("/item");
            this.setState({items: data.result});

            data = await APIConfig.get("/cart");
            this.setState({cartItems: data.data.result})
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    handleChangeField(event) {
        const {name, value} = event.target;
        this.setState({[name]: value});
    }

    handleAddItem() {
        this.setState({isCreate: true});
    }

    async handleSubmitItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.post("/item", data);
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }

    handleCancel(event) {
        event.preventDefault();
        this.setState({

        })
        this.setState({
            isCreate: false,
            isEdit: false,
            title: "", price: 0, description: "", category: "", quantity: 0});
    }

    handleEditItem(item) {
        this.setState({
            isEdit: true,
            id: item.id,
            title: item.title,
            price: item.price,
            description: item.description,
            category: item.category,
            quantity: item.quantity
        })
    }

    async handleSubmitEditItem(event) {
        event.preventDefault();
        try {
            const data = {
                title: this.state.title,
                price: this.state.price,
                description: this.state.description,
                category: this.state.category,
                quantity: this.state.quantity
            };
            await APIConfig.put(`/item/${this.state.id}`, data);
            this.setState({
                id: "", title: "",
                price: 0, description: "", category: "", quantity: 0
            })
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
        this.handleCancel(event);
    }

    async handleDeleteItem(item) {
        try {
            const data = {
                id: item.id,
            };
            await APIConfig.delete(`/item/${item.id}`, data);
            this.loadData();
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    async handleChangeSearch(event) {
        try {
            const {data} = await APIConfig.get("/item", {
                params: {
                    title: event.target.value
                }
            });
            this.setState({items: data.result});
        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    async handleAddToCart(item) {
        try {

            const qtyToCart = this.state.qtyToCart;
            if (qtyToCart <1 ) {
                alert("Minimal perlu menambahkan 1");
                return;
            }

            for (const tesKey in this.state.cartItems) {
                if (item.id === this.state.cartItems[tesKey].item.id) {
                    if ((parseInt(qtyToCart) + parseInt(this.state.cartItems[tesKey].quantity)) > parseInt(item.quantity)) {
                        alert(`Gagal menambahkan ke keranjang, anda sudah memiliki ${this.state.cartItems[tesKey].quantity}`
                        +
                        ` item di keranjang, dan stok yang tersedia hanya tinggal ${parseInt(item.quantity) -
                        parseInt(this.state.cartItems[tesKey].quantity)}`);
                        return;
                    }
                }
            }

            const data = {
                idItem: item.id,
                quantity: qtyToCart
            };
            await APIConfig.post(`/cart/`, data);
            alert(`Berhasil menambahkan item ${item.title} ke keranjang sebanyak ${qtyToCart}`);
            this.loadData();

        } catch (error) {
            alert("Oops terjadi masalah pada server");
            console.log(error);
        }
    }

    render() {
        return (
            <div className={classes.itemList}>
                <h1 className={classes.title}>
                    All Items
                </h1>
                <div style={{ position: "fixed", top: 25, right: 25 }}>
                    <Link to="/cart">
                        <Fab variant="extended">
                            <Badge color="secondary" badgeContent={this.state.cartItems.length}>
                                <ShoppingCartIcon />
                            </Badge>
                        </Fab>
                    </Link>
                </div>

                <SearchBar onChange={this.handleChangeSearch} />

                <br/>
                <Button action={this.handleAddItem}>
                    Add Item
                </Button>
                <div>
                    {this.state.items.map((item) => (
                        <Item key={item.id} id={item.id} title={item.title}
                              price={item.price} description={item.description} category={item.category}
                              quantity={item.quantity}
                              handleEdit={() => this.handleEditItem(item)}
                              handleDelete={() => this.handleDeleteItem(item)}

                              qtyOnChange={this.handleChangeField}
                              handleAddToCart={() => this.handleAddToCart(item)}
                        />
                    ))}
                </div>
                <Modal show={this.state.isCreate || this.state.isEdit} handleCloseModal={this.handleCancel}
                       modalTitle={this.state.isCreate
                           ? "Add Item"
                           : `Edit Item ID ${this.state.id}`}
                >
                    <form><input className={classes.textField} type="text" placeholder="Nama Item" name="title"
                                 value={this.state.title} onChange={this.handleChangeField}/> <input
                        className={classes.textField} type="number" placeholder="Harga" name="price"
                        value={this.state.price} onChange={this.handleChangeField}/> <textarea
                        className={classes.textField} placeholder="Deskripsi" name="description" rows="4"
                        value={this.state.description} onChange={this.handleChangeField}/>
                        <input
                            className={classes.textField} type="text" placeholder="Kategori" name="category"
                            value={this.state.category} onChange={this.handleChangeField}/> <input
                            className={classes.textField} type="number" placeholder="qty" name="quantity"
                            value={this.state.quantity} onChange={this.handleChangeField}/>
                        <Button action={this.state.isCreate
                            ? this.handleSubmitItem
                            : this.handleSubmitEditItem}
                        >
                            Create
                        </Button>
                        <Button action={this.handleCancel}>
                            Cancel
                        </Button>
                    </form>
                </Modal>
            </div>
        );
    }
}

export default ItemList;