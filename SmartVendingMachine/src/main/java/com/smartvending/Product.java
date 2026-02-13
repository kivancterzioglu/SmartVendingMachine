package com.smartvending;

/**
 * Represents a product in the vending machine.
 * Contains product information including name, price, and stock quantity.
 * Provides methods to manage stock levels and check availability.
 */
public class Product {
    private String name;
    private double price;
    private int stock;
    
    /**
     * Constructs a new Product with the specified name, price, and initial stock.
     * 
     * @param name the name of the product
     * @param price the price of the product (must be positive)
     * @param stock the initial stock quantity (must be non-negative)
     * @throws IllegalArgumentException if price is negative or stock is negative
     */
    public Product(String name, double price, int stock) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be null or empty");
        }
        
        this.name = name.trim();
        this.price = price;
        this.stock = stock;
    }
    
    /**
     * Reduces the stock quantity by 1.
     * 
     * @throws IllegalStateException if the product is out of stock
     */
    public void reduceStock() {
        if (stock <= 0) {
            throw new IllegalStateException("Cannot reduce stock: product is out of stock");
        }
        stock--;
    }
    
    /**
     * Restocks the product with the specified quantity.
     * 
     * @param quantity the amount to add to stock (must be positive)
     * @throws IllegalArgumentException if quantity is not positive
     */
    public void restock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Restock quantity must be positive");
        }
        stock += quantity;
    }
    
    /**
     * Checks if the product is available (has stock > 0).
     * 
     * @return true if the product is available, false otherwise
     */
    public boolean isAvailable() {
        return stock > 0;
    }
    
    /**
     * Gets the name of the product.
     * 
     * @return the product name
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gets the price of the product.
     * 
     * @return the product price
     */
    public double getPrice() {
        return price;
    }
    
    /**
     * Gets the current stock quantity.
     * 
     * @return the stock quantity
     */
    public int getStock() {
        return stock;
    }
    
    /**
     * Sets the price of the product.
     * 
     * @param price the new price (must be non-negative)
     * @throws IllegalArgumentException if price is negative
     */
    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }
    
    /**
     * Returns a string representation of the product.
     * 
     * @return a string containing product name, price, and stock
     */
    @Override
    public String toString() {
        return String.format("Product{name='%s', price=%.2f, stock=%d}", 
                           name, price, stock);
    }
    
    /**
     * Checks if this product is equal to another object.
     * Products are considered equal if they have the same name.
     * 
     * @param obj the object to compare
     * @return true if the objects are equal, false otherwise
     */

}
