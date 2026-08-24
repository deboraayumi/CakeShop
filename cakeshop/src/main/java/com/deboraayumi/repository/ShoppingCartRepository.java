package com.deboraayumi.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.deboraayumi.model.CartItem;
import com.deboraayumi.model.ShoppingCart;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ShoppingCartRepository {
    
    private static final String FILE_PATH = "data/shoppingCartBD.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File(FILE_PATH);



    //read all
    public List<CartItem> listCartItems(){

        if(!file.exists()){
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(file, new TypeReference<List<CartItem>>(){});
        } catch (IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public void saveCart(ShoppingCart sc){
        try{
            File folder = file.getParentFile();
            if (folder != null && !folder.exists()) {
                folder.mkdirs();
            }

            mapper.writerWithDefaultPrettyPrinter().writeValue(file, sc.getAllItems());
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

