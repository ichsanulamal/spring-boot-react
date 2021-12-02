import React from "react";

const Button = (props) => {
    const {action, children} = props;
    return (
        <button onClick={action}> {children}
        </button>
    );
}
export default Button;
