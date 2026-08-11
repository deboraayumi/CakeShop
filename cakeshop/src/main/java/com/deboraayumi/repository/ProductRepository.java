package com.deboraayumi.repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.List;

import com.deboraayumi.model.Product;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class ProductRepository{

    private static final String FILE_PATH = "CakeShop\\data\\productDB.json";
    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = new File(FILE_PATH);
    


    //read all
    public List<Product> getAllProducts(){

        if(!file.exists()){
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(file, new TypeReference<List<Product>>() {});
        } catch (IOException e){
            e.printStackTrace();
            return new ArrayList<>();
        }
    }


    public void saveProducts(List<Product> p){
                try {
                    mapper.writerWithDefaultPrettyPrinter().writeValue(file, p);
                } catch (IOException e) {
                    e.printStackTrace();
                }
    }    
    
}


