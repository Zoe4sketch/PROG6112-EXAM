package com.mycompany.productsalesapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class ProductTest {

    private final String[] testProducts = {
        "Cozy Nights Monarch",
        "Restonic Barbados",
        "Sealy Phuket"
    };

    private final int[][] testSales;

    public ProductTest() {
        this.testSales = new int[][]{{25, 18, 20}, // Total: 63
            {15, 10, 12}, // Total: 37
            {18, 15, 20}  // Total: 53
};  }

    @Test
    public void GetTotalProductSales_WithValidData_ReturnsCorrectValue() {
        Product product = new Product(testProducts, testSales);
        int expected = 153;
        int actual = product.GetTotalProductsSold();
        assertEquals(expected, actual);
    }

    @Test
    public void GetAverageSales_WithValidData_ReturnsCorrectValue() {
        Product product = new Product(testProducts, testSales);
        double expected = 17.0;
        double actual = product.GetAverageProductSold();
        assertEquals(expected, actual, 0.01);
    }
}