package com.smartvending;

import java.util.*;
import java.time.LocalDateTime;

/**
 * Represents a smart vending machine that manages products and handles transactions.
 * Provides functionality to insert money, select products, and process purchases.
 */
public class VendingMachine {
    private Map<String, Product> products;
    private double currentBalance;
    private List<Transaction> transactionHistory;
    
    /**
     * Constructs a new VendingMachine with empty product list and zero balance.
     */
    public VendingMachine() {
        this.products = new HashMap<>();
        this.currentBalance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }
    
    /**
     * Adds a product to the vending machine.
     * 
     * @param product the product to add
     * @throws IllegalArgumentException if product is null
     */
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.put(product.getName(), product);
    }
    
    /**
     * Inserts money into the vending machine.
     * 
     * @param amount the amount to insert (must be positive)
     * @throws IllegalArgumentException if amount is not positive
     */
    public void insertMoney(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        currentBalance += amount;
    }
    
    /**
     * Selects a product for purchase and processes the transaction.
     * 
     * @param productName the name of the product to purchase
     * @return a Transaction object representing the completed purchase
     * @throws IllegalArgumentException if product name is null or empty
     * @throws IllegalStateException if product is not available or insufficient funds
     */
    public Transaction selectProduct(String productName) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        
        Product product = products.get(productName.trim());
        if (product == null) {
            throw new IllegalStateException("Product not found: " + productName);
        }
        
        if (!product.isAvailable()) {
            throw new IllegalStateException("Product is out of stock: " + productName);
        }
        
        if (currentBalance < product.getPrice()) {
            throw new IllegalStateException("Insufficient funds. Required: " + 
                                          product.getPrice() + ", Available: " + currentBalance);
        }
        
        // Process the transaction
        double amountPaid = product.getPrice();
        double change = currentBalance - amountPaid;
        
        // Reduce stock and update balance
        product.reduceStock();
        currentBalance = 0.0; // Reset balance after purchase
        
        // Create and record transaction
        Transaction transaction = new Transaction(productName, amountPaid, change, LocalDateTime.now());
        transactionHistory.add(transaction);
        
        return transaction;
    }
    
    /**
     * Gets the change from the current balance without making a purchase.
     * 
     * @return the current balance as change
     */
    public double getChange() {
        double change = currentBalance;
        currentBalance = 0.0;
        return change;
    }
    
    /**
     * Gets the current balance in the machine.
     * 
     * @return the current balance
     */
    public double getCurrentBalance() {
        return currentBalance;
    }
    
    /**
     * Gets a product by name.
     * 
     * @param productName the name of the product
     * @return the Product object, or null if not found
     */
    public Product getProduct(String productName) {
        return products.get(productName);
    }
    
    /**
     * Gets all available products (products with stock > 0).
     * 
     * @return a list of available products
     */
    public List<Product> getAvailableProducts() {
        List<Product> available = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.isAvailable()) {
                available.add(product);
            }
        }
        return available;
    }
    
    /**
     * Gets all products in the machine.
     * 
     * @return a list of all products
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }
    
    /**
     * Gets the transaction history.
     * 
     * @return a list of all transactions
     */
    public List<Transaction> getTransactionHistory() {
        return new ArrayList<>(transactionHistory);
    }
    
    /**
     * Gets the total number of products in the machine.
     * 
     * @return the number of products
     */
    public int getProductCount() {
        return products.size();
    }
    
    /**
     * Checks if a product exists in the machine.
     * 
     * @param productName the name of the product
     * @return true if the product exists, false otherwise
     */
    public boolean hasProduct(String productName) {
        return products.containsKey(productName);
    }
    
    /**
     * Removes a product from the machine.
     * 
     * @param productName the name of the product to remove
     * @return the removed product, or null if not found
     */
    public Product removeProduct(String productName) {
        return products.remove(productName);
    }
    
    /**
     * Clears all products from the machine.
     */
    public void clearProducts() {
        products.clear();
    }
    
    /**
     * Gets the total value of all products in stock.
     * 
     * @return the total inventory value
     */
    public double getTotalInventoryValue() {
        double total = 0.0;
        for (Product product : products.values()) {
            total += product.getPrice() * product.getStock();
        }
        return total;
    }
    
    /**
     * Returns a string representation of the vending machine.
     * 
     * @return a string containing machine status information
     */
    @Override
    public String toString() {
        return String.format("VendingMachine{products=%d, balance=%.2f, transactions=%d}", 
                           products.size(), currentBalance, transactionHistory.size());
    }
}
