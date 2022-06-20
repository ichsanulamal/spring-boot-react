import React from "react";
import Button from "../button";
import classes from "./styles.module.css";

const Item = (props) => {
    const { id, title, price, description, category, quantity,
        qtyOnChange, handleEdit, handleDelete, handleAddToCart } = props;
    return (
        <>
        <div className={classes.item}>
            <h3>{`ID ${id}`}</h3>
            <p>{`Nama Barang: ${title}`}</p>
            <p>{`Price: ${price}`}</p>
            <p>{`Description: ${description}`}</p>
            <p>{`Category: ${category}`}</p>
            <p>{`Stok: ${quantity}`}</p>
            <Button action={handleEdit}>
                Edit
            </Button>
            <Button action={handleDelete}>
                Delete
            </Button>
            <br/>
            <input type={"number"} min={0} onChange={qtyOnChange} name="qtyToCart" />
            <Button action={handleAddToCart}>
                Add To Cart
            </Button>
        </div>

        </>
    );
};
export default Item;