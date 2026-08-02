package com.deboraayumi.repository;

import com.deboraayumi.model.ShoppingCart;

public class JsonShoppingCartRepository {

    private ShoppingCart currentCart;

    public JsonShoppingCartRepository(ShoppingCart s){
        this.currentCart = s;
    }

    public void setCurrentCart(ShoppingCart currentCart) {
        this.currentCart = currentCart;
    }

    public ShoppingCart getCurrentCart() {
        return currentCart;
    }


    


}

