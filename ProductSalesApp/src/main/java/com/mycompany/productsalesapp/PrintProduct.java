package com.mycompany.productsalesapp;

public class PrintProduct {
    private final Product productData;

    public PrintProduct(Product productData) {
        this.productData = productData;
    }

    public void printTotalSales() {
        System.out.println("TOTAL PRODUCT SALES: " + productData.GetTotalProductsSold());
    }

    public void printAverageSales() {
        System.out.printf("AVERAGE PRODUCT SALES: %.0f%n", productData.GetAverageProductSold());
    }

    public void printMostPopularProduct() {
        String popular = productData.GetMostPopularProduct();
        int peakMonth = productData.getHighestMonthlySaleForPopularProduct();
        System.out.println("MOST POPULAR PRODUCT: " + popular + " (" + peakMonth + ")");
    }

    public void printFullReport() {
        System.out.println("\nPRODUCT SALES REPORT - 2026");
        System.out.println("*******************************************");
        printTotalSales();
        printAverageSales();
        printMostPopularProduct();
    }
}