package com.deboraayumi.model;

public class CartItem {

    private Product item;
    private int quantity;
    private double subTotal;

    public CartItem(){}

    public CartItem(Product p, int quantity){
        this.item = p;
        this.quantity = quantity;
        this.subTotal = p.getPrice() * quantity;
    }

    public Product getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubTotal(){
        return this.subTotal;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
