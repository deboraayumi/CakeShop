package com.deboraayumi.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<CartItem> items;

    public ShoppingCart(){
        this.items = new ArrayList<CartItem>();
    }

    public List<CartItem> getAllItems(){
        return new ArrayList<>(this.items);
    }

    public void addCartItem(CartItem c){
        this.items.add(c);
    }


    public double calcTotalValue(){
        double totalValue = 0;

        for(CartItem c : this.items){
            totalValue += c.getSubTotal();
        }

        return totalValue;
    }


    public int calcTotalQuantity(){
        int totalQuantity = 0;
        
        for(CartItem c : this.items){
            totalQuantity += c.getQuantity();
        }
        return totalQuantity;
    }

    
}
