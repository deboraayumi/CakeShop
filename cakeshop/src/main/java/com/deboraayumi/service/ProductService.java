package com.deboraayumi.service;

import java.util.ArrayList;
import java.util.List;

import com.deboraayumi.exception.InvalidTagException;
import com.deboraayumi.model.Product;
import com.deboraayumi.repository.ProductRepository;

public class ProductService {
    
    private ProductRepository productRepository = new ProductRepository();
    private List<Product> products = productRepository.getAllProducts();


    public List<Product> getProducts(){
        return this.products;
    }

    public List<Product> searchByTag(String[] keyword){

        List<Product.Tag> tags = new ArrayList<>();

        for(String word : keyword){
            try{
                Product.Tag tag = Product.Tag.valueOf(word.toUpperCase());
                tags.add(tag);

                } catch (InvalidTagException e){
                throw new InvalidTagException("This tag don't exist");
            }
        }

        List<Product> results = new ArrayList<>();

        for(Product p : products){
            for(Product.Tag tag : tags){
                if(p.getTags().contains(tag)){
                    results.add(p);
                }
            }
        }

        return results;
    }



}
