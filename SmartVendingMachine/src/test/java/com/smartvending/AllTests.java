package com.smartvending;

import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

/**
 * Test suite that runs all test classes for the Smart Vending Machine project.
 * This suite includes tests for Product, VendingMachine, and Transaction classes.
 */
@Suite
@SuiteDisplayName("Smart Vending Machine - All Tests")
@SelectClasses({
    ProductTest.class,
    VendingMachineTest.class,
    TransactionTest.class
})
public class AllTests {
    // This class serves as a test suite container.
    // All test classes are selected via the @SelectClasses annotation.
}
