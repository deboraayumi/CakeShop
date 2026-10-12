package com.deboraayumi.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.deboraayumi.exception.CartLoadException;
import com.deboraayumi.exception.InvalidItemQuantityException;
import com.deboraayumi.exception.InvalidProductIdException;
import com.deboraayumi.model.CartItem;
import com.deboraayumi.model.Product;
import com.deboraayumi.service.ShoppingCartService;
import com.deboraayumi.utils.ConsoleUtils;
import com.deboraayumi.utils.InputValidatorUtils;


public class ShoppingCartUI {
    
    ShoppingCartService shoppingCartService = new ShoppingCartService();
    Scanner scanner = new Scanner(System.in);
    InputValidatorUtils inputValidator = new InputValidatorUtils();

    int totalWidth = 72;

    public List<CartItem> getCarItemsToList(){
        try{
            return shoppingCartService.getCartItems();
            
        } catch (CartLoadException e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }


    public void listCartItems(){
        
        List<CartItem> cartItemsToList = getCarItemsToList();

        if(cartItemsToList.size() == 0){
            System.out.println("Your cart is empty!");
            return;
        }

        
        System.out.println("Your Cart: ");
        System.out.println("_".repeat(totalWidth));
        System.out.printf("| %-2s | %-30s | %-7s | %-7s | %-8s |\n", "ID", "Name", "Price", "Quantity", "Sub Total");

        for(CartItem ci : cartItemsToList){
            System.out.printf("| %-2d | %-30s | $ %5.2f | %8d | $ %7.2f |\n",
            ci.getItem().getId(), ci.getItem().getName(), ci.getItem().getPrice(), ci.getQuantity(), ci.getSubTotal());
        }
        System.out.println("_".repeat(totalWidth));

        System.out.printf("Total Quantity: %d\n", shoppingCartService.getTotalQuantity());
        System.out.printf("Total Value: $ %.2f\n", shoppingCartService.getTotalValue());

    }

    public boolean editCartItem(){

        List<CartItem> items = shoppingCartService.getCartItems();
        int chosenId = 0;
        int quantity;
        
        System.out.println("+++ Shopping Cart -> Item Edit Page +++");
        System.out.println("-".repeat(totalWidth));

        while (true) {
            
            listCartItems();

            boolean foundId = false;
            int maxStock = 0;

            while(!foundId){

                System.out.printf("Select the ID of the Item that you want to edit the quantity: ");
       
                chosenId = inputValidator.getPositiveInt();

                for(CartItem ci : items){
                    Product p = ci.getItem();

                    if(p.getId() == chosenId){
                        maxStock = p.getStock();
                        foundId = true;
                        break;
                    }
                }

                if(!foundId){
                    System.out.println("This ID don't exist in your list. Please enter an ID that you have in your list.");
                }
            }

            while(true){
                System.out.println("How much of it do you want?");
                System.out.printf("(Max stock = %d )\n", maxStock);

                quantity = inputValidator.getPositiveInt();
                

                try{
                    // quantity <= 0 repete aqui tbm
                    shoppingCartService.editCartIem(chosenId, quantity);
                } catch (InvalidItemQuantityException e){
                    System.out.println(e.getMessage());
                    continue;
                }    
                
                break;
        
            }
            return true;
        }

    }

    public boolean deleteCartItem(){
        int chosenId;

        System.out.println("+++ Shopping Cart -> Item Delete Page +++");
        System.out.println("-".repeat(totalWidth));


        listCartItems();

        while(true){
            System.out.printf("Select the ID of the item that you want to delete: ");

            chosenId = inputValidator.getPositiveInt();

            try{
                shoppingCartService.deleteCartItem(chosenId);
            }catch (InvalidProductIdException e){
                System.out.println(e.getMessage());
                continue;
            }

            break;
        }

        return true;

    }

    public void shoppingCartPage(){

        int selection;
        boolean editSuccess = false;
        boolean deleteSuccess = false;
        boolean invalidSelection = false;
        
        while(true){

            ConsoleUtils.clearScreen();
            
            System.out.println("+++ Shopping Cart Page +++");
            System.out.println("-".repeat(totalWidth));

            if(editSuccess){
                System.out.println("Item successfully edited!");
                editSuccess = false;
            }

            if(deleteSuccess){
                System.out.println("Item successfully deleted!");
                deleteSuccess = false;
            }

            if(invalidSelection){
                System.out.println("Invalid value. Please insert an number between 1 and 0");
                invalidSelection = false;
            }

            
            listCartItems();
        
            System.out.println("1 - Edit Item Quantity");
            System.out.println("2 - Delete Item");
            System.out.println("0 - Return to the Home Menu");
            
            selection = inputValidator.getNonNegativeInt();
            

            switch (selection){

                case 0: 
                    ConsoleUtils.clearScreen();
                    return;

                case 1: 
                    ConsoleUtils.clearScreen();
                    editSuccess = editCartItem();
                    break;

                case 2:
                    ConsoleUtils.clearScreen();
                    deleteSuccess = deleteCartItem();
                    break;

                default: 
                    invalidSelection = true;
                    break;

            }

        }
    }
}
