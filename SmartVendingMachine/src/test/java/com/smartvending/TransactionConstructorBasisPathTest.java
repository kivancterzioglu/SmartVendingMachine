package com.smartvending;

import org.junit.jupiter.api.*;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

/**
 * TransactionConstructorBasisPathTest
 *
 * This test class implements the white-box basis path testing for the
 * Transaction class constructor:
 * public Transaction(String productName, double amountPaid, double changeGiven, LocalDateTime date)
 *
 * Cyclomatic Complexity (V(G)) is 5, requiring 5 independent paths.
 * We define 6 paths to fully cover the logical OR condition in the first check.
 */
class TransactionConstructorBasisPathTest {

    private final LocalDateTime validDate = LocalDateTime.now();
    private final double validAmountPaid = 2.00;
    private final double validChangeGiven = 0.50;
    private final String validProductName = "Cola";

    // --- Path 1 (P1): productName == null ---
    @Test
    @DisplayName("P1: Throws IllegalArgumentException when productName is null (First Check - Condition 1)")
    void path_1_nullProductName_throwsException() {
        // Test: productName = null (P1)
        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction(null, validAmountPaid, validChangeGiven, validDate);
        }, "Path P1 failed: Should throw exception for null product name.");
    }

    // --- Path 2 (P2): productName.trim().isEmpty() ---
    @Test
    @DisplayName("P2: Throws IllegalArgumentException when productName is empty/blank (First Check - Condition 2)")
    void path_2_blankProductName_throwsException() {
        // Test: productName = " " (P2)
        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction("   ", validAmountPaid, validChangeGiven, validDate);
        }, "Path P2 failed: Should throw exception for blank product name.");
    }

    // --- Path 3 (P3): amountPaid < 0 ---
    @Test
    @DisplayName("P3: Throws IllegalArgumentException when amountPaid is negative (Second Check)")
    void path_3_negativeAmountPaid_throwsException() {
        // Ensure previous checks pass, and this check fails (P3)
        // productName valid, amountPaid invalid.
        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction(validProductName, -1.00, validChangeGiven, validDate);
        }, "Path P3 failed: Should throw exception for negative amount paid.");
    }

    // --- Path 4 (P4): changeGiven < 0 ---
    @Test
    @DisplayName("P4: Throws IllegalArgumentException when changeGiven is negative (Third Check)")
    void path_4_negativeChangeGiven_throwsException() {
        // Ensure previous checks pass (P1, P2, P3), and this check fails (P4)
        // productName valid, amountPaid valid, changeGiven invalid.
        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction(validProductName, validAmountPaid, -0.50, validDate);
        }, "Path P4 failed: Should throw exception for negative change given.");
    }

    // --- Path 5 (P5): date == null ---
    @Test
    @DisplayName("P5: Throws IllegalArgumentException when date is null (Fourth Check)")
    void path_5_nullDate_throwsException() {
        // Ensure previous checks pass (P1-P4), and this check fails (P5)
        // productName valid, amountPaid valid, changeGiven valid, date invalid.
        assertThrows(IllegalArgumentException.class, () -> {
            new Transaction(validProductName, validAmountPaid, validChangeGiven, null);
        }, "Path P5 failed: Should throw exception for null date.");
    }

    // --- Path 6 (P6): Successful Construction ---
    @Test
    @DisplayName("P6: Successfully constructs Transaction when all parameters are valid")
    void path_6_successfulConstruction() {
        // Ensure all checks pass (P1-P5)
        Transaction transaction = assertDoesNotThrow(() -> {
            return new Transaction(validProductName, validAmountPaid, validChangeGiven, validDate);
        }, "Path P6 failed: Should successfully construct the transaction.");

        // Verification of state after successful construction
        assertNotNull(transaction);
        assertEquals(validProductName, transaction.getProductName());
        assertEquals(validAmountPaid, transaction.getAmountPaid());
        assertEquals(validChangeGiven, transaction.getChangeGiven());
        assertEquals(validDate, transaction.getDate());
    }
}
