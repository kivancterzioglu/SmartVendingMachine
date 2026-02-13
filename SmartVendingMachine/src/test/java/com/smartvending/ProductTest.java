package com.smartvending;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Product Tests")
class ProductTest {
    
    private Product product;
    
    @BeforeEach
    void setUp() {
        product = new Product("Coca Cola", 1.50, 10);
    }
    
    @Test
    @DisplayName("Constructor should create product with valid parameters")
    void testConstructorWithValidParameters() {
        Product testProduct = new Product("Pepsi", 2.00, 5);
        assertAll("Product constructor validation",
            () -> assertEquals("Pepsi", testProduct.getName()),
            () -> assertEquals(2.00, testProduct.getPrice()),
            () -> assertEquals(5, testProduct.getStock())
        );
    }
    
    @Test
    @DisplayName("Constructor should throw exception for negative price")
    void testConstructorWithNegativePrice() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Test", -1.0, 5));
    }
    
    @Test
    @DisplayName("Constructor should throw exception for negative stock")
    void testConstructorWithNegativeStock() {
        assertThrows(IllegalArgumentException.class, () -> new Product("Test", 1.0, -1));
    }
    
    @Test
    @DisplayName("Constructor should throw exception for null name")
    void testConstructorWithNullName() {
        assertThrows(IllegalArgumentException.class, () -> new Product(null, 1.0, 5));
    }
    
    @Test
    @DisplayName("Constructor should trim whitespace from name")
    void testConstructorTrimsName() {
        Product testProduct = new Product("  Sprite  ", 1.25, 8);
        assertEquals("Sprite", testProduct.getName());
    }
    
    @Test
    @DisplayName("reduceStock should decrease stock by 1")
    void testReduceStock() {
        int initialStock = product.getStock();
        product.reduceStock();
        assertEquals(initialStock - 1, product.getStock());
    }
    
    @Test
    @DisplayName("reduceStock should throw exception when out of stock")
    void testReduceStockWhenOutOfStock() {
        Product emptyProduct = new Product("Empty", 1.0, 0);
        assertThrows(IllegalStateException.class, emptyProduct::reduceStock);
    }
    
    @Test
    @DisplayName("reduceStock should make product unavailable when stock reaches 0")
    void testReduceStockMakesUnavailable() {
        Product singleStockProduct = new Product("Single", 1.0, 1);
        singleStockProduct.reduceStock();
        assertFalse(singleStockProduct.isAvailable());
    }
    
    @Test
    @DisplayName("restock should increase stock by specified amount")
    void testRestock() {
        int initialStock = product.getStock();
        product.restock(5);
        assertEquals(initialStock + 5, product.getStock());
    }
    
    @Test
    @DisplayName("restock should throw exception for negative quantity")
    void testRestockWithNegativeQuantity() {
        assertThrows(IllegalArgumentException.class, () -> product.restock(-5));
    }
    
    @Test
    @DisplayName("isAvailable should return true when stock > 0")
    void testIsAvailableWithStock() {
        assertTrue(product.isAvailable());
    }
    
    @Test
    @DisplayName("isAvailable should return false when stock = 0")
    void testIsAvailableWithoutStock() {
        Product emptyProduct = new Product("Empty", 1.0, 0);
        assertFalse(emptyProduct.isAvailable());
    }
    
    @Test
    @DisplayName("setPrice should update price with valid value")
    void testSetPriceWithValidValue() {
        product.setPrice(2.50);
        assertEquals(2.50, product.getPrice());
    }
    
    @Test
    @DisplayName("setPrice should throw exception for negative price")
    void testSetPriceWithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> product.setPrice(-1.0));
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {5.50})
    @DisplayName("setPrice should accept various positive prices")
    void testSetPriceWithVariousValues(double price) {
        product.setPrice(price);
        assertEquals(price, product.getPrice());
    }
    
    @Test
    @DisplayName("toString should return formatted string")
    void testToString() {
        String result = product.toString();
        assertTrue(result.contains("Coca Cola"));
    }
    
    @Test
    @DisplayName("equals should return true for same product name")
    void testEqualsWithSameName() {
        Product product2 = new Product("Coca Cola", 2.00, 5);
        assertEquals(product, product2);
    }
    
    @Test
    @DisplayName("equals should return false for different product names")
    void testEqualsWithDifferentNames() {
        Product product2 = new Product("Pepsi", 1.50, 10);
        assertNotEquals(product, product2);
    }

    
    @ParameterizedTest
    @CsvSource({"Water, 1.00, 20"})
    @DisplayName("Constructor should work with various CSV inputs")
    void testConstructorWithCsvInputs(String name, double price, int stock) {
        Product testProduct = new Product(name, price, stock);
        assertEquals(name, testProduct.getName());
    }
}

