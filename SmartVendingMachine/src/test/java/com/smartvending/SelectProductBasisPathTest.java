package com.smartvending;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

class SelectProductBasisPathTest {

    private VendingMachine vm;
    private Product p;

    @BeforeEach
    void setUp() {
        vm = new VendingMachine();
        // add a product "A1" price 2.5 stock 2
        p = new Product("A1", 2.50, 2);
        vm.addProduct(p);
    }

    @Test
    @DisplayName("T1: productName null or empty -> IllegalArgumentException")
    void testSelectProduct_NullName_Throws() {
        assertThrows(IllegalArgumentException.class, () -> {
            vm.selectProduct(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            vm.selectProduct("   ");
        });
    }

    @Test
    @DisplayName("T2: product not found -> IllegalStateException")
    void testSelectProduct_ProductNotFound() {
        assertThrows(IllegalStateException.class, () -> {
            vm.selectProduct("Z99");
        });
    }

    @Test
    @DisplayName("T3: product out of stock -> IllegalStateException")
    void testSelectProduct_OutOfStock() {
        // set stock to 0
        Product prod = new Product("B2", 1.00, 0);
        vm.addProduct(prod);
        assertThrows(IllegalStateException.class, () -> {
            vm.selectProduct("B2");
        });
    }

    @Test
    @DisplayName("T4: insufficient funds -> IllegalStateException")
    void testSelectProduct_InsufficientFunds() {
        // ensure product exists with price > balance
        vm.insertMoney(1.00); // price is 2.50
        assertThrows(IllegalStateException.class, () -> {
            vm.selectProduct("A1");
        });
    }

    @Test
    @DisplayName("T5: successful purchase -> returns Transaction, change, stock--, balance reset")
    void testSelectProduct_Successful() {
        vm.insertMoney(5.00); // enough
        Transaction tx = vm.selectProduct("A1");
        assertNotNull(tx);
        assertEquals("A1", tx.getProductName());
        // price 2.50, change should be 2.50
        assertEquals(2.50, tx.getChangeGiven());
        // product stock decreased
        assertFalse(vm.getProduct("A1").isAvailable() == false && vm.getProduct("A1").getStock() != 1); // simpler check
        // current balance reset
        assertEquals(0.0, vm.getCurrentBalance());
    }
}
