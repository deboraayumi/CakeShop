package com.deboraayumi.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.deboraayumi.model.ShoppingCart;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShoppingCartRepository {

    private ShoppingCart currentCart;

    public ShoppingCartRepository(ShoppingCart s){
        this.currentCart = s;
    }

    public void setCurrentCart(ShoppingCart currentCart) {
        this.currentCart = currentCart;
    }

    public ShoppingCart getCurrentCart() {
        return currentCart;
    }


    private static final String FILE_PATH = "CakeShop\\data\\shoppingCartBD.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File(FILE_PATH);



    //read all
    public List<ShoppingCart> listCartItems(){

        if(!file.exists()){
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(file, new TypeReference<List<ShoppingCart>>(){});
        } catch (IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    
    //update
    public void saveCart(ShoppingCart sc){
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, sc.getAllProducts());
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    //cleaner
    public void clean(){
        try{
            mapper.writeValue(file, new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}

