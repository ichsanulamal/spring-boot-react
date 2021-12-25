import React from 'react';
import {Redirect, Route, Switch} from 'react-router-dom';
import Home from "./views/Home";
import CartIndex from "./views/Cart";

export const AppRoutes = () => {
    return (
        <div>
            <Switch>
                <Route exact path="/list" component={Home} />
                <Route exact path="/cart" component={CartIndex} />
                <Route exact path="/">
                    <Redirect to="/list" />
                </Route>
            </Switch>
        </div>
    );
};