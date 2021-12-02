import React from "react";
import classes from "./styles.module.css";

const CartItem = (props) => {
    const { id, title, price, jumlah, description, category, totalHarga} = props;
    return (
        <>
            <div className={classes.item}>
                <h3>{`ID ${id}`}</h3>
                <p>{`Nama Barang: ${title}`}</p>
                <p>{`Price: ${price}`}</p>
                <p>{`Jumlah: ${jumlah}`}</p>
                <p>{`Description: ${description}`}</p>
                <p>{`Category: ${category}`}</p>
                <p><b>Total Harga: ${totalHarga}</b></p>
            </div>

        </>
    );
};
export default CartItem;