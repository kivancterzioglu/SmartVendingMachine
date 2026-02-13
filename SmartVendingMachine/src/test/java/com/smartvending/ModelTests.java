    package com.smartvending;

    import org.junit.platform.suite.api.SelectClasses;
    import org.junit.platform.suite.api.Suite;
    import org.junit.platform.suite.api.SuiteDisplayName;

    @Suite
    @SuiteDisplayName("Vending Machine - Model Classes Tests")
    @SelectClasses({
        ProductTest.class,
        TransactionTest.class
    })
    public class ModelTests {

    }
    
