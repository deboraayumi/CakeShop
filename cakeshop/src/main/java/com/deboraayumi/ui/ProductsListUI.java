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
    int totalWidth = 55;

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
        System.out.printf("| %-2s | %-30s | %-5s | %-5s |\n", "ID", "Name", "Price", "Stock");

        for(Product p : productsToList){
            System.out.printf("| %-2d | %-30s | %-5.2f | %-5d |\n",
             p.getId(), p.getName(), p.getPrice(), p.getStock());
        }


        System.out.println("_".repeat(totalWidth));

    }


    public void productsListPage(){
        boolean itemAdded = false;
        List<Product> productsToList = getProductsToList();
        
        while(true){
            int chosenId = -1;
            int quantity = -1;

            ConsoleUtils.clearScreen();

            System.out.println("+++ Product List Page +++");
            System.out.println("-".repeat(totalWidth));

            if(itemAdded){
                System.out.println("Item successfully added!");
                itemAdded = false;
            }

            listProducts();

            System.out.println("(press 0 to exit)");

            while(true){
                chosenId = -1;
                System.out.printf(("Add to cart the product with ID: "));
                
                while(chosenId < 0){
                    chosenId = inputValidator.isInputAnInt();
                }

                if(chosenId == 0){
                    ConsoleUtils.clearScreen();
                    return;
                }

                if(chosenId < 0 || chosenId > productsToList.size()){
                    System.out.println("Invalid ID. Please enter an ID that exist in the list");
                    
                    continue;
                }

                break;
            }

            System.out.println("How much of that product? ");

            while(true){
                quantity = -1;

                while (quantity < 0) {
                    quantity = inputValidator.isInputAnInt();
                }
                
                try{
                    shoppingCartService.selectProduct(chosenId, quantity);
                } catch(InvalidItemQuantityException e){
                    e.getMessage();
                    System.out.println("Please enter an number between 1 and the stock in the display.");

                    continue;
                }
                itemAdded = true;

                break;
            }
            
        }
    }
}
