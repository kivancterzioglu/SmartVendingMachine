package com.smartvending;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

@DisplayName("Transaction Tests")
class TransactionTest {
    
    private Transaction transaction;
    private LocalDateTime testDate;
    
    @BeforeEach
    void setUp() {
        testDate = LocalDateTime.of(2024, 1, 15, 10, 30, 0);
        transaction = new Transaction("Coca Cola", 1.50, 0.50, testDate);
    }
    
    @Test
    @DisplayName("Constructor should create transaction with valid parameters")
    void testConstructorWithValidParameters() {
        Transaction testTransaction = new Transaction("Pepsi", 2.00, 0.25, testDate);
        assertEquals("Pepsi", testTransaction.getProductName());
    }
    
    @Test
    @DisplayName("Constructor should throw exception for null product name")
    void testConstructorWithNullProductName() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction(null, 1.50, 0.50, testDate));
    }
    
    @Test
    @DisplayName("Constructor should throw exception for negative amount paid")
    void testConstructorWithNegativeAmountPaid() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction("Coca Cola", -1.50, 0.50, testDate));
    }
    
    @Test
    @DisplayName("Constructor should throw exception for negative change given")
    void testConstructorWithNegativeChangeGiven() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction("Coca Cola", 1.50, -0.50, testDate));
    }
    
    @Test
    @DisplayName("Constructor should throw exception for null date")
    void testConstructorWithNullDate() {
        assertThrows(IllegalArgumentException.class, () -> new Transaction("Coca Cola", 1.50, 0.50, null));
    }
    
    @Test
    @DisplayName("Constructor should trim whitespace from product name")
    void testConstructorTrimsProductName() {
        Transaction testTransaction = new Transaction("  Sprite  ", 1.25, 0.75, testDate);
        assertEquals("Sprite", testTransaction.getProductName());
    }
    
    @Test
    @DisplayName("getTotalAmountInserted should return sum of amount paid and change")
    void testGetTotalAmountInserted() {
        assertEquals(2.00, transaction.getTotalAmountInserted());
    }
    
    @Test
    @DisplayName("hasChange should return true when change > 0")
    void testHasChangeWithChange() {
        assertTrue(transaction.hasChange());
    }
    
    @Test
    @DisplayName("hasChange should return false when change = 0")
    void testHasChangeWithoutChange() {
        Transaction noChangeTransaction = new Transaction("Water", 1.00, 0.0, testDate);
        assertFalse(noChangeTransaction.hasChange());
    }
    
    @Test
    @DisplayName("toString should return formatted string")
    void testToString() {
        String result = transaction.toString();
        assertTrue(result.contains("2024-01-15 10:30:00"));
    }
    
    @Test
    @DisplayName("toDetailedString should return detailed formatted string")
    void testToDetailedString() {
        String result = transaction.toDetailedString();
        assertTrue(result.contains("Purchase: Coca Cola"));
    }

    
    @ParameterizedTest
    @ValueSource(doubles = {5.00})
    @DisplayName("Constructor should accept various positive amounts paid")
    void testConstructorWithVariousAmountsPaid(double amountPaid) {
        Transaction testTransaction = new Transaction("Test", amountPaid, 0.0, testDate);
        assertEquals(amountPaid, testTransaction.getAmountPaid());
    }
    
    @ParameterizedTest
    @ValueSource(doubles = {0.75})
    @DisplayName("Constructor should accept various positive change amounts")
    void testConstructorWithVariousChangeAmounts(double changeGiven) {
        Transaction testTransaction = new Transaction("Test", 1.00, changeGiven, testDate);
        assertEquals(changeGiven, testTransaction.getChangeGiven());
    }
    
    @ParameterizedTest
    @CsvSource({"Pepsi, 2.00, 0.25"})
    @DisplayName("Constructor should work with various CSV inputs")
    void testConstructorWithCsvInputs(String productName, double amountPaid, double changeGiven) {
        Transaction testTransaction = new Transaction(productName, amountPaid, changeGiven, testDate);
        assertEquals(productName, testTransaction.getProductName());
    }
    
    @Test
    @DisplayName("Transaction with zero amount paid should be valid")
    void testTransactionWithZeroAmountPaid() {
        Transaction freeTransaction = new Transaction("Free Sample", 0.0, 0.0, testDate);
        assertEquals(0.0, freeTransaction.getAmountPaid());
    }
}
