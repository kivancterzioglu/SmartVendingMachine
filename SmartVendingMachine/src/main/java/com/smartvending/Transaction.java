package com.smartvending;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a completed transaction in the vending machine.
 * Contains information about the product purchased, amount paid, change given, and timestamp.
 */
public class Transaction {
    private String productName;
    private double amountPaid;
    private double changeGiven;
    private LocalDateTime date;
    
    /**
     * Constructs a new Transaction with the specified details.
     * 
     * @param productName the name of the product purchased
     * @param amountPaid the amount paid for the product
     * @param changeGiven the change returned to the customer
     * @param date the date and time of the transaction
     * @throws IllegalArgumentException if any parameter is invalid
     */
    public Transaction(String productName, double amountPaid, double changeGiven, LocalDateTime date) {
        if (productName == null || productName.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        if (amountPaid < 0) {
            throw new IllegalArgumentException("Amount paid cannot be negative");
        }
        if (changeGiven < 0) {
            throw new IllegalArgumentException("Change given cannot be negative");
        }
        if (date == null) {
            throw new IllegalArgumentException("Transaction date cannot be null");
        }
        
        this.productName = productName.trim();
        this.amountPaid = amountPaid;
        this.changeGiven = changeGiven;
        this.date = date;
    }
    
    /**
     * Gets the name of the product purchased.
     * 
     * @return the product name
     */
    public String getProductName() {
        return productName;
    }
    
    /**
     * Gets the amount paid for the product.
     * 
     * @return the amount paid
     */
    public double getAmountPaid() {
        return amountPaid;
    }
    
    /**
     * Gets the change returned to the customer.
     * 
     * @return the change given
     */
    public double getChangeGiven() {
        return changeGiven;
    }
    
    /**
     * Gets the date and time of the transaction.
     * 
     * @return the transaction date
     */
    public LocalDateTime getDate() {
        return date;
    }
    
    /**
     * Gets the total amount inserted (amount paid + change given).
     * 
     * @return the total amount inserted
     */
    public double getTotalAmountInserted() {
        return amountPaid + changeGiven;
    }
    
    /**
     * Checks if this transaction resulted in change being given.
     * 
     * @return true if change was given, false otherwise
     */
    public boolean hasChange() {
        return changeGiven > 0;
    }
    
    /**
     * Returns a formatted string representation of the transaction.
     * 
     * @return a formatted string containing transaction details
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("Transaction{product='%s', amountPaid=%.2f, changeGiven=%.2f, date=%s}", 
                           productName, amountPaid, changeGiven, date.format(formatter));
    }
    
    /**
     * Returns a detailed string representation of the transaction for display.
     * 
     * @return a detailed formatted string
     */
    public String toDetailedString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("Purchase: %s | Amount Paid: $%.2f | Change: $%.2f | Date: %s", 
                           productName, amountPaid, changeGiven, date.format(formatter));
    }
    
    /**
     * Checks if this transaction is equal to another object.
     * Transactions are considered equal if they have the same product name, amount paid, and date.
     * 
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */
}

