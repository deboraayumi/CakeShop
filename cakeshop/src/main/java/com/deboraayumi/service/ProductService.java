package com.deboraayumi.service;

import java.util.ArrayList;
import java.util.List;

import com.deboraayumi.exception.InvalidTagException;
import com.deboraayumi.model.CartItem;
import com.deboraayumi.model.Product;
import com.deboraayumi.repository.ProductRepository;
import com.deboraayumi.repository.ShoppingCartRepository;

public class ProductService {
    
    private ProductRepository productRepository = new ProductRepository();
    private ShoppingCartRepository shoppingCartRepository = new ShoppingCartRepository();


    public List<Product> getProducts(){
        return productRepository.getAllProducts();
    }

    public List<Product> searchByTag(String keywords){

        List<String> keyword = List.of(keywords.split(" "));

        List<Product.Tag> tags = new ArrayList<>();

        for(String word : keyword){
            try{
                Product.Tag tag = Product.Tag.valueOf(word.toUpperCase());
                tags.add(tag);

                } catch (InvalidTagException e){
                throw new InvalidTagException("The tag " + word + " don't exist.");
            }
        }

        List<Product> results = new ArrayList<>();

        List<Product> products = this.getProducts();

        for(Product p : products){
            for(Product.Tag tag : tags){
                if(p.getTags().contains(tag)){
                    results.add(p);
                    break;
                }
            }
        }

        return results;
    }


    public void stockDecreaceAfterPurchace(){
        List<CartItem> items = shoppingCartRepository.listCartItems();

        if(items.size() != 0){

            List<Product> products = this.getProducts();

            for(Product p : products){
                for(CartItem ci : items){
                    if(p == ci.getItem()){
                        p.setStock(p.getStock() - ci.getQuantity());

                        items.remove(ci);
                        break;
                    }
                }
            }
        }

    }



}
