package com.smartvending;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("VendingMachine Tests")
class VendingMachineTest {
    
    private VendingMachine vendingMachine;
    private Product coke;
    
    @BeforeEach
    void setUp() {
        vendingMachine = new VendingMachine();
        coke = new Product("Coca Cola", 1.50, 10);
        Product pepsi = new Product("Pepsi", 2.00, 5);
        vendingMachine.addProduct(coke);
        vendingMachine.addProduct(pepsi);
    }
    
    @Test
    @DisplayName("Constructor should create empty vending machine")
    void testConstructor() {
        VendingMachine newMachine = new VendingMachine();
        assertEquals(0, newMachine.getProductCount());
    }
    
    @Test
    @DisplayName("addProduct should add product to machine")
    void testAddProduct() {
        Product water = new Product("Water", 1.00, 15);
        vendingMachine.addProduct(water);
        assertTrue(vendingMachine.hasProduct("Water"));
    }
    
    @Test
    @DisplayName("addProduct should throw exception for null product")
    void testAddProductWithNull() {
        assertThrows(IllegalArgumentException.class, () -> vendingMachine.addProduct(null));
    }
    
    @Test
    @DisplayName("insertMoney should increase balance")
    void testInsertMoney() {
        vendingMachine.insertMoney(5.00);
        assertEquals(5.00, vendingMachine.getCurrentBalance());
    }
    
    @Test
    @DisplayName("insertMoney should accumulate multiple insertions")
    void testInsertMoneyMultiple() {
        vendingMachine.insertMoney(2.00);
        vendingMachine.insertMoney(1.50);
        assertEquals(3.50, vendingMachine.getCurrentBalance());
    }
    
    @Test
    @DisplayName("insertMoney should throw exception for negative amount")
    void testInsertMoneyWithNegative() {
        assertThrows(IllegalArgumentException.class, () -> vendingMachine.insertMoney(-1.0));
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {10.00})
    @DisplayName("insertMoney should accept various positive amounts")
    void testInsertMoneyWithVariousAmounts(double amount) {
        vendingMachine.insertMoney(amount);
        assertEquals(amount, vendingMachine.getCurrentBalance());
    }
    
    @Test
    @DisplayName("selectProduct should complete successful transaction")
    void testSelectProductSuccessful() {
        vendingMachine.insertMoney(2.00);
        Transaction transaction = vendingMachine.selectProduct("Coca Cola");
        assertNotNull(transaction);
        assertEquals(0.50, transaction.getChangeGiven());
        assertEquals(9, coke.getStock());
    }
    
    @Test
    @DisplayName("selectProduct should throw exception for null product name")
    void testSelectProductWithNullName() {
        vendingMachine.insertMoney(2.00);
        assertThrows(IllegalArgumentException.class, () -> vendingMachine.selectProduct(null));
    }
    
    @Test
    @DisplayName("selectProduct should throw exception for non-existent product")
    void testSelectProductWithNonExistentProduct() {
        vendingMachine.insertMoney(2.00);
        assertThrows(IllegalStateException.class, () -> vendingMachine.selectProduct("Non-existent"));
    }
    
    @Test
    @DisplayName("selectProduct should throw exception for out of stock product")
    void testSelectProductWithOutOfStock() {
        Product emptyProduct = new Product("Empty", 1.00, 0);
        vendingMachine.addProduct(emptyProduct);
        vendingMachine.insertMoney(2.00);
        assertThrows(IllegalStateException.class, () -> vendingMachine.selectProduct("Empty"));
    }
    
    @Test
    @DisplayName("selectProduct should throw exception for insufficient funds")
    void testSelectProductWithInsufficientFunds() {
        vendingMachine.insertMoney(1.00);
        assertThrows(IllegalStateException.class, () -> vendingMachine.selectProduct("Coca Cola"));
    }
    
    @Test
    @DisplayName("getChange should return current balance and reset it")
    void testGetChange() {
        vendingMachine.insertMoney(3.00);
        double change = vendingMachine.getChange();
        assertEquals(3.00, change);
        assertEquals(0.0, vendingMachine.getCurrentBalance());
    }

    @Test
    @DisplayName("getAvailableProducts should return only products with stock")
    void testGetAvailableProducts() {
        Product emptyProduct = new Product("Empty", 1.00, 0);
        vendingMachine.addProduct(emptyProduct);
        assertEquals(2, vendingMachine.getAvailableProducts().size());
    }
    
    @Test
    @DisplayName("hasProduct should return true for existing product")
    void testHasProductWithExisting() {
        assertTrue(vendingMachine.hasProduct("Coca Cola"));
    }
    
    @Test
    @DisplayName("hasProduct should return false for non-existing product")
    void testHasProductWithNonExisting() {
        assertFalse(vendingMachine.hasProduct("Non-existent"));
    }
    
    @Test
    @DisplayName("removeProduct should remove and return product")
    void testRemoveProduct() {
        vendingMachine.removeProduct("Coca Cola");
        assertFalse(vendingMachine.hasProduct("Coca Cola"));
    }
    
    @Test
    @DisplayName("clearProducts should remove all products")
    void testClearProducts() {
        vendingMachine.clearProducts();
        assertEquals(0, vendingMachine.getProductCount());
    }
    
    @Test
    @DisplayName("getTotalInventoryValue should calculate correct total")
    void testGetTotalInventoryValue() {
        // Coke: 1.50 * 10 = 15.00, Pepsi: 2.00 * 5 = 10.00, Total = 25.00
        assertEquals(25.00, vendingMachine.getTotalInventoryValue());
    }
    
    @Test
    @DisplayName("toString should return formatted string")
    void testToString() {
        String result = vendingMachine.toString();
        assertTrue(result.contains("2")); // product count
    }
    
    @ParameterizedTest
    @CsvSource({"Pepsi, 3.00, 1.00"})
    @DisplayName("selectProduct should work with various CSV inputs")
    void testSelectProductWithCsvInputs(String productName, double amountInserted, double expectedChange) {
        vendingMachine.insertMoney(amountInserted);
        Transaction transaction = vendingMachine.selectProduct(productName);
        assertEquals(expectedChange, transaction.getChangeGiven());
    }
}
