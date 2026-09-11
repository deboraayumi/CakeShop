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

public class ProductsListUI {
    
    Scanner scanner = new Scanner(System.in);

    ShoppingCartService shoppingCartService = new ShoppingCartService();
    ProductService productService = new ProductService();

    public List<Product> getProductsToList(){
        try{
            return productService.getProducts();
            
        } catch (ProductLoadException e){
            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void listProducts(){

        int totalWidth = 55;
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
        int choice = 0;
        int quantity = 0;
        List<Product> productsToList = getProductsToList();

        while(true){
            ConsoleUtils.clearScreen();
            if(itemAdded){
                System.out.println("Item successfully added!");
                itemAdded = false;
            }

            listProducts();

            System.out.println("(press 0 to exit)");

            while(true){
                System.out.printf(("Add to cart the product with ID: "));
                
                if(!scanner.hasNextInt()){
                    System.out.println("Invalid value. Please enter an ID that exist in the list.");
                    scanner.next();
                    continue;
                }

                choice = scanner.nextInt();

                if(choice == 0){
                    ConsoleUtils.clearScreen();
                    return;
                }

                if(choice < 0 || choice > productsToList.size()){
                    System.out.println("Invalid ID. Please enter an ID that exist in the list");
                    
                    continue;
                }

                break;
            }

            System.out.println("How much of that product? ");

            while(true){
                if(!scanner.hasNextInt()){
                    scanner.next();
                    System.out.println("Invalid value. Please enter an integer number.");

                    continue;
                }
                
                quantity = scanner.nextInt();

                try{
                    shoppingCartService.selectProduct(choice, quantity);
                } catch(InvalidItemQuantityException e){
                    quantity = 0;
                    e.getMessage();
                    System.out.println("Please enter an number between 1 and the stock in the display.");

                    continue;
                }
                System.out.println("hello?");
                itemAdded = true;

                break;
            }
            
        }
    }
}
