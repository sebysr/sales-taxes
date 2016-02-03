package me.buggin;

import java.util.Scanner;

/**
 * Created by abuggin on 2/3/16.
 */
public class Main {
    public static void main(String args[]){
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            System.out.println(new Parser().parse(scanner.nextLine()));
        }
    }
}
