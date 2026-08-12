package com.deboraayumi.service;

import java.util.List;

import com.deboraayumi.exception.InvalidProductIdException;
import com.deboraayumi.model.Product;
import com.deboraayumi.model.ShoppingCart;
import com.deboraayumi.repository.ProductRepository;
import com.deboraayumi.repository.ShoppingCartRepository;

public class ShoppingCartService {

    ProductRepository productRepository;
    ShoppingCart cart = new ShoppingCart();
    ShoppingCartRepository cartRepository;

    private List<Product> products = productRepository.getAllProducts();


    public void save(){
        cartRepository.saveCart(cart);
    }


    public void addItem(Product p){

        cart.addProduct(p);
        save();
    }


    public void selectProduct(){

        int selectedID = 0; //test value. recieve here the UI input

        for(Product p : products){
            if(selectedID == p.getId()){
                cart.addProduct(p);
                save();
                return;
            }
        }   
        
        throw new InvalidProductIdException("Invalid product ID.");

    }

    

    
}
