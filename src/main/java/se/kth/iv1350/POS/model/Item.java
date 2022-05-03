package se.kth.iv1350.POS.model;

import se.kth.iv1350.POS.util.Cash;

/**
 * Represents an item that can be bought.
 */
public class Item {

    private final int itemID;
    private final java.lang.String name;
    private final double rateOfVAT;
    private final java.lang.String description;
    private final Cash price;
    private int quantity;

    /**
     * Creates an instance.
     * @param itemID the ID of the item
     * @param name the name
     * @param rateOfVAT the VAT rate for the item, (where 1 is 100%)
     * @param description the description of the item
     * @param price the price of the item
     */
    public Item(int itemID, String name, double rateOfVAT, String description, 
            Cash price) {
        this.itemID = itemID;
        this.name = name;
        this.rateOfVAT = rateOfVAT;
        this.description = description;
        this.price = price;
    }

    /**
     * Sets the quantity of the item.
     * @param quantity the quantity to set
     */
    void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Increases the quantity of the item.
     * @param increaseAmount the amount to increase the quantity with.
     */
    void increaseQuantity(int increaseAmount) {
        this.quantity += increaseAmount;
    }

    /**
     * Get the ID of the item
     * @return the ID of the item
     */
    public int getID() {
        return itemID;
    }

    /**
     * Get the name of the item
     * @return the name of the item
     */
    public java.lang.String getName() {
        return name;
    }

    /**
     * Get the VAT rate of the item
     * @return the VAT rate of the item
     */
    public double getVAT() {
        return rateOfVAT;
    }

    /**
     * Get the description of the item
     * @return the description of the item
     */
    public java.lang.String getDescription() {
        return description;
    }

    /**
     * Get the price of the item
     * @return the price of the item
     */
    public Cash getPrice() {
        return price;
    }
    
    /**
     * Get the VAT amount (not rate)
     * @return the VAT amount
     */
    public Cash getVATAmount() {
        return price.multiply(rateOfVAT);
    }
    
    /**
     * Get the quantity of the item
     * @return the quantity of the item
     */
    public int getQuantity() {
        return quantity;
    }

    @Override
    /**
     * Formats the Item into a string for printing the receipt.
     */
    public String toString() {
        return String.format("%-3d %-16s %10s", quantity, name, price.multiply(quantity));
    }
    
}
