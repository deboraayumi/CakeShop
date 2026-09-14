package com.deboraayumi.utils;

import java.util.Scanner;

public class InputValidatorUtils {
    
    Scanner scanner = new Scanner(System.in);

    public int isInputAnInt(){

        if(!scanner.hasNextInt()){
            System.out.println("Invalid value. Please insert a positive number.");
            scanner.next();
            return -1;
        }

        return scanner.nextInt();

    }
}
