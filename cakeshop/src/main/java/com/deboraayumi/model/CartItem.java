package com.deboraayumi.model;

public class CartItem {

    private Product item;
    private int quantity;

    public CartItem(Product p, int quantity){
        this.item = p;
        this.quantity = quantity;
    }

    public Product getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }
}
