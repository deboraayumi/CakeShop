package com.deboraayumi.utils;

import java.util.Scanner;

public class InputValidatorUtils {
    
    Scanner scanner = new Scanner(System.in);
    int number = 0;

    public int getPositiveInt(){

        while(true){
            if(!scanner.hasNextInt()){
                System.out.println("Invalid value. Please insert a positive number.");
                scanner.next();
                continue;
            }

            number = scanner.nextInt();

            if(number<=0){
                System.out.println("Invalid value. Please insert a positive number.");
                continue;
            }

            break;
        }
        return number;
        
    }

    public int getNonNegativeInt(){

        while(true){
            if(!scanner.hasNextInt()){
                System.out.println("Invalid value. Please insert a positive number.");
                scanner.next();

                continue;
            }

            number = scanner.nextInt();

            if(number<0){
               System.out.println("Invalid value. Please insert a positive number.");
                continue;
            }

            break;
        }
        return number;
        
    }

}
