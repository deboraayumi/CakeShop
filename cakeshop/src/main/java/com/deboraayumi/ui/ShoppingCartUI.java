package com.deboraayumi.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.deboraayumi.exception.CartLoadException;
import com.deboraayumi.exception.InvalidItemQuantityException;
import com.deboraayumi.model.CartItem;
import com.deboraayumi.model.Product;
import com.deboraayumi.service.ShoppingCartService;
import com.deboraayumi.utils.ConsoleUtils;
import com.deboraayumi.utils.InputValidatorUtils;


public class ShoppingCartUI {
    
    ShoppingCartService shoppingCartService = new ShoppingCartService();
    Scanner scanner = new Scanner(System.in);
    InputValidatorUtils inputValidator = new InputValidatorUtils();

    int totalWidth = 69;

    public List<CartItem> getCarItemsToList(){
        try{
            return shoppingCartService.getCartItems();
            
        } catch (CartLoadException e){
            System.out.println("Error: " + e.getMessage());
            return new ArrayList<>();
        }
    }


    public void listCartItems(){

        ConsoleUtils.clearScreen();
        
        List<CartItem> cartItemsToList = getCarItemsToList();

        if(cartItemsToList.size() == 0){
            System.out.println("Your cart is empty!");
            return;
        }


        System.out.println("_".repeat(totalWidth));
        System.out.printf("| %-2s | %-30s | %-5s | %-8s | %-9s |\n", "ID", "Name", "Price", "Quantity", "Sub Total");

        for(CartItem ci : cartItemsToList){
            System.out.printf("| %-2d | %-30s | %-5.2f | %8d | %9.2f |\n",
            ci.getItem().getId(), ci.getItem().getName(), ci.getItem().getPrice(), ci.getQuantity(), ci.getSubTotal());
        }

        System.out.printf("Total: %.2f", shoppingCartService.getTotalValue());

        System.out.println("_".repeat(totalWidth));
    }

    public void editCartItem(){

        List<CartItem> items = shoppingCartService.getCartItems();
        int chosenId;
        int quantity;

        while (true) {
            
            System.out.println("+++ Shopping Cart -> Item Edit Page +++");
            System.out.println("-".repeat(totalWidth));

            listCartItems();

            System.out.printf("Select the ID of the Item that you want to edit the quantity: ");

            while (true) {
                
                chosenId = inputValidator.getPositiveInt();
                

                boolean foundId = false;

                for(CartItem ci : items){
                    Product p = ci.getItem();

                    if(p.getId() == chosenId){
                        foundId = true;

                        System.out.println("How much of it do you want?");
                        System.out.printf("(Max stock = %d )\n", p.getStock());

                        break;
                    }
                }

                if(foundId == false){
                    System.out.println("This ID don't exist in your list. Please enter an ID that you have in your list.");
                    continue;
                }

                quantity = inputValidator.getPositiveInt();
                

                try{
                    // quantity <= 0 repete aqui tbm
                    shoppingCartService.editCartIem(chosenId, quantity);
                } catch (InvalidItemQuantityException e){
                    e.getMessage();
                    continue;
                }                
            }

            // testa o app pra ver oq falta fazer
            // inputvalidator incompleto
        }

    }


    public void shoppingCartMenu(){

        int selection = -1;
        
        while(true){
            
            System.out.println("+++ Shopping Cart Page +++");
            System.out.println("-".repeat(totalWidth));
            
            listCartItems();
        
            System.out.println("(press 0 to exit)");
            System.out.println("1 - Edit Item Quantity");
            System.out.println("2 - Delete Item");

            while (selection < 0) {
                selection = inputValidator.getNonNegativeInt();
            }

            switch (selection){

                case 0: 
                    ConsoleUtils.clearScreen();
                    return;

                case 1: 
                    editCartItem();
                    break;
                case 2:

                default: 
                    System.out.println("Invalid value. Please, enter a number between 1 and 5.");
                    break;

            }

        }
    }
}
