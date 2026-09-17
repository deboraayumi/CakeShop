package com.deboraayumi.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.deboraayumi.exception.InvalidItemQuantityException;
import com.deboraayumi.exception.ProductLoadException;
import com.deboraayumi.model.Product;
import com.deboraayumi.service.ProductService;
import com.deboraayumi.service.ShoppingCartService;
import com.deboraayumi.utils.ConsoleUtils;
import com.deboraayumi.utils.InputValidatorUtils;

public class ProductsListUI {
    
    Scanner scanner = new Scanner(System.in);
    int totalWidth = 61;

    ShoppingCartService shoppingCartService = new ShoppingCartService();
    ProductService productService = new ProductService();
    InputValidatorUtils inputValidator = new InputValidatorUtils();

    public List<Product> getProductsToList(){
        try{
            return productService.getProducts();
            
        } catch (ProductLoadException e){
            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void listProducts(){

        List<Product> productsToList = getProductsToList();

        System.out.println("_".repeat(totalWidth));
        System.out.printf("| %-2s | %-30s | %-9s | %-7s |\n", "ID", "Name", "Price", "Stock");

        for(Product p : productsToList){
            System.out.printf("| %-2d | %-30s | $ %-7.2f | %-7d |\n",
             p.getId(), p.getName(), p.getPrice(), p.getStock());
            }
            
            
            System.out.println("_".repeat(totalWidth));
            
    }
    
    public boolean addProduct(){
        List<Product> productsToList = getProductsToList();

        int chosenId = 0;
        int quantity = 0;

        System.out.println("+++ Product List -> Item Add Page +++");
        System.out.println("-".repeat(totalWidth));

        listProducts();


        while(true){
                System.out.printf(("Add to cart the product with ID: "));
                
                chosenId = inputValidator.getPositiveInt();


                if(chosenId > productsToList.size()){
                    System.out.println("Invalid ID. Please enter an ID that exist in the list");
                    
                    continue;
                }

                break;
            }

            System.out.println("How much of that product? ");

            while(true){
                
                quantity = inputValidator.getPositiveInt();
                
                
                try{
                    shoppingCartService.selectProduct(chosenId, quantity);
                    System.out.println("cheguei");
                } catch(InvalidItemQuantityException e){
                    e.getMessage();
                    System.out.println("Please enter an number between 1 and the stock in the display.");

                    continue;
                }
                    System.out.println("cheguei2");
                return true;

            }
            

    }

    public void productsListPage(){
        boolean itemAdded = false;
        boolean invalidSelection = false;
        int selection;
        
        while(true){

            ConsoleUtils.clearScreen();

            System.out.println("+++ Product List Page +++");
            System.out.println("-".repeat(totalWidth));

            if(itemAdded){
                System.out.println("Item successfully added!");
                itemAdded = false;
            }

            if(invalidSelection){
                System.out.println("Invalid value. Please insert an number between 1 and 0");
                invalidSelection = false;
            }

            listProducts();

            System.out.println("1 - Add Item");
            System.out.println("0 - Return to the Home Menu");
            System.out.printf("Choose a page to go: ");

            
            selection = scanner.nextInt();
            
            switch (selection) {
            case 1:
                ConsoleUtils.clearScreen();
                itemAdded = addProduct();
                break;

            case 0:
                ConsoleUtils.clearScreen();
                return;
                
            default:
                invalidSelection = true;
                break;
            }
                            
        }
    }
}
