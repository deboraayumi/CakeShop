package com.deboraayumi.service;

import java.util.List;

import com.deboraayumi.exception.InvalidItemQuantityException;
import com.deboraayumi.exception.InvalidProductIdException;
import com.deboraayumi.model.CartItem;
import com.deboraayumi.model.Product;
import com.deboraayumi.model.ShoppingCart;
import com.deboraayumi.repository.ProductRepository;
import com.deboraayumi.repository.ShoppingCartRepository;

public class ShoppingCartService {

    private ProductRepository productRepository = new ProductRepository();
    private ShoppingCart cart = new ShoppingCart();
    private ShoppingCartRepository cartRepository = new ShoppingCartRepository();

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

                if(quantity > p.getStock() || quantity <= 0){
                    throw new InvalidItemQuantityException("The quantity can not exceed maximum or fall below the minimum stock quantity");
                }

                CartItem c = new CartItem(p, quantity);

                addItem(c);
                return;
            }
        }   
        
        throw new InvalidProductIdException("Invalid product ID.");

    }

    public List<CartItem> getCartItems(){
        return cartRepository.listCartItems();
    }

    public double getTotalValue(){
        return cart.calcTotalValue();
    }

    public void resetCart(){
        cartRepository.clean();

    }

    
}
