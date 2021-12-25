import React from 'react';

const SearchBar = (props) => {
    const BarStyling = {width:"20rem",background:"#F2F1F9", border:"none", padding:"0.5rem"};
    const {onChange} = props
    return (
        <input
            style={BarStyling}
            placeholder={"Search item..."}
            onChange={onChange}
        />
    );
}

export default SearchBar