package com.deboraayumi.model;

import java.util.List;

import com.deboraayumi.exception.InvalidProductArgumentException;

public class Product {


        static int idCounter = 1;

        private int id;
        private String name;
        private double price;
        private int stock;
        private List<Tag> tags;
        

        public enum Tag {
            CHOCOLATE,
            VANNILA,
            STRAWBERRY,
            CAKE, COOKIE,
            PIE,
            DRINK,
            BITTER,
            FRUIT,
            VEGAN,
            GLUTEN_FREE,
            LACTOSE_FREE
        }

        public Product(){}


        public Product(String name, double price, int stock, List<Tag> tag) {
                stockZeroChecker(stock);
                priceZeroChecker(price);
                nameEmptyChecker(name);
                this.id = idCounter++;
                this.name = name;
                this.price = price;
                this.stock = stock;
                this.tags = tag;
        }

        public int getId(){
            return this.id;
        }

        public List<Tag> getTags() {
            return tags;
        }

    /*-------------------------Name-------------------------*/

        public String getName(){
            return this.name;
        }

        public void setName(String name) {
            nameEmptyChecker(name);
            this.name = name;
        }
        
        private void nameEmptyChecker(String name){
            if(name.isEmpty()){
                throw new InvalidProductArgumentException( "Product name must have at least one word");
            }
        }

    /*-------------------------Price-------------------------*/

        public double getPrice(){
            return this.price;
        }

        public void setPrice(double price) {
            priceZeroChecker(price);
            this.price = price;
        }

        private void priceZeroChecker(double price){
            if(price <=0.0){
                throw new InvalidProductArgumentException( "Invalid Price. Insert a value bigger than zero");
            }
        }



    /*-------------------------Stock-------------------------*/

        public int getStock(){
            return this.stock;
        }

        public void setStock(int stock){
            this.stock = stock;
        }

        public void addStock(int stock){
            this.stock += stock;
        }

        public void subtractStock(int stock) {
            stockZeroChecker(stock);
            this.stock -= stock;
        }    

        private void stockZeroChecker(int stock){
            if(stock <0){
                throw new InvalidProductArgumentException("Invalid Stock. Insert a value bigger than zero");
            }
        }


    /*-------------------------Test Print-------------------------*/

        public void printData(){
            System.out.printf("Product Name: %s", this.name);
            System.out.printf("Product Price: %f", this.price);
            System.out.printf("Product Stock: %d", this.stock);
        }
    }
