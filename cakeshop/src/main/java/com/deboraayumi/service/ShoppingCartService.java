package com.deboraayumi.service;

import java.util.List;

import com.deboraayumi.exception.InvalidProductIdException;
import com.deboraayumi.model.CartItem;
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


    public void addItem(CartItem c){

        cart.addCartItem(c);
        save();
    }


    public void selectProduct(int selectedID, int quantity){

        for(Product p : products){
            if(selectedID == p.getId()){

                CartItem c = new CartItem(p, quantity);

                addItem(c);
                return;
            }
        }   
        
        throw new InvalidProductIdException("Invalid product ID.");

    }


    

    
}
