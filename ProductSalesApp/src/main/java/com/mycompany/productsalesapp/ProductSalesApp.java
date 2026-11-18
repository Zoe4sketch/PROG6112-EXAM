package com.mycompany.productsalesapp;

import java.util.Scanner;

public class ProductSalesApp {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            String[] products = {
                "Cozy Nights Monarch",
                "Restonic Barbados",
                "Sealy Phuket"
            };
            
            int[][] sales = new int[3][3];
            
            for (int i = 0; i < products.length; i++) {
                for (int month = 0; month < 3; month++) {
                    System.out.print("Enter number of sales for month " + (month + 1)
                            + " for " + products[i] + " mattress: ");
                    sales[i][month] = scanner.nextInt();
                }
                System.out.println();
            }

            Product productReport = new Product(products, sales);
            PrintProduct printer = new PrintProduct(productReport);
            printer.printFullReport();
        }
    }
}