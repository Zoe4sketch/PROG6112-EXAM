package com.mycompany.productsalesapp;

public class Product implements IProduct {
    private final String[] products;
    private final int[][] sales;

    public Product(String[] products, int[][] sales) {
        this.products = products;
        this.sales = sales;
    }

    @Override
    public int GetTotalProductsSold() {
        int total = 0;
        for (int[] sale : sales) {
            for (int j = 0; j < sale.length; j++) {
                total += sale[j];
            }
        }
        return total;
    }

    @Override
    public double GetAverageProductSold() {
        int total = GetTotalProductsSold();
        int count = 0;
        for (int[] sale : sales) {
            count += sale.length;
        }
        
        return (double) total / count;
    }

    @Override
    public String GetMostPopularProduct() {
        int highestTotal = -1;
        int bestIndex = 0;

        for (int i = 0; i < sales.length; i++) {
            int productTotal = 0;
            for (int j = 0; j < sales[i].length; j++) {
                productTotal += sales[i][j];
            }
            if (productTotal > highestTotal) {
                highestTotal = productTotal;
                bestIndex = i;
            }
        }
        return products[bestIndex];
    }
    
    public int getHighestMonthlySaleForPopularProduct() {
        String popular = GetMostPopularProduct();
        int bestIndex = 0;
        for (int i = 0; i < products.length; i++) {
            if (products[i].equals(popular)) {
                bestIndex = i;
                break;
            }
        }
        int maxMonth = sales[bestIndex][0];
        for (int j = 1; j < sales[bestIndex].length; j++) {
            if (sales[bestIndex][j] > maxMonth) {
                maxMonth = sales[bestIndex][j];
            }
        }
        return maxMonth;
    }
}