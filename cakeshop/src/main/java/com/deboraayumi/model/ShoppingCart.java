package com.deboraayumi.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Product> products;

    public ShoppingCart(){
        this.products = new ArrayList<Product>();
    }


    public double calcTotalValue(){
        double totalValue = 0;

        for(Product p : this.products){
            totalValue += p.getPrice();
        }

        return totalValue;
    }


    public int calcTotalQuantity(){
        return this.products.size();
    }

    
}
