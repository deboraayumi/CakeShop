package com.deboraayumi.ui;

import java.util.Scanner;

import com.deboraayumi.service.ShoppingCartService;
import com.deboraayumi.utils.InputValidatorUtils;


public class MenuUI {

    Scanner scanner = new Scanner(System.in);

    ShoppingCartService shoppingCartService = new ShoppingCartService();
    InputValidatorUtils inputValidator = new InputValidatorUtils();
    ProductsListUI productsListUI = new ProductsListUI();
    ShoppingCartUI shoppingCartUI = new ShoppingCartUI();

    public void printLogo(){
        System.out.printf("%60s \n", "=".repeat(70));
        System.out.printf("%60s \n", "    ______         __              ______   __                       ");
        System.out.printf("%60s \n", "  .' ___  |       [  |  _        .' ____ \\ [  |                     ");
        System.out.printf("%60s \n", " / .'   \\_| ,--.   | | / ] .---. | (___ \\_| | |--.   .--.   _ .--. ");
        System.out.printf("%60s \n", " | |       `'_\\ :  | '' < / /__\\\\ _.____`.  | .-. |/ .'`\\ \\[ '/'`\\ \\");
        System.out.printf("%60s \n", " \\ `.___.'\\// | |, | |`\\ \\| \\__.,| \\____) | | | | || \\__. | | \\__/ |");
        System.out.printf("%60s \n", "  `.____ .'\\'-;__/[__|  \\_]'.__.' \\______.'[___]|__]'.__.'  | ;.__/ ");
        System.out.printf("%60s \n", "                                                           [__|     ");
        System.out.printf("%60s \n", "=".repeat(70));
    }


    public void welcomePage(){

        printLogo();
        System.out.printf("%s \n \n", "Welcome!");


    }

    public void printMenu(){

        System.out.println("-".repeat(35));
        System.out.println("1 - Products List");
        System.out.println("2 - Search for the Product");
        System.out.println("3 - Shopping Cart");
        System.out.println("4 - Checkout");
        System.out.println("0 - Exit");
        System.out.println("-".repeat(35));

    }

    public void selectPage(){
        while(true){

            printMenu();

            System.out.print("Choose a page to go: ");

            int pageSelection = -1;

            while(pageSelection < 0){
                pageSelection = inputValidator.isInputAnInt();
            }

            
            
            switch (pageSelection){
                case 1:
                    productsListUI.productsListPage();
                    break;
                
                case 2:
                    System.out.println("search");
                    break;
                
                case 3:
                    shoppingCartUI.listCartItems();
                    break;
                
                case 4:
                    System.out.println("check");
                    break;
                
                case 0:
                    shoppingCartService.resetCart();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid value. Please, enter a number between 1 and 5.");
                    break;
            }
            
                
            

        }
    }

    

}
