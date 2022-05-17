package se.kth.iv1350.POS.model;

/**
 * An instance of this class contains the information about an item
 * necessary for the view to know about when scanning items
 */
public class ItemInfoDTO {
    
    private final String description;
    private final Cash price;
    
   /**
    * Creates a new instance
    * @param item the item that 
    */ 
    public ItemInfoDTO(Item item) {
        this.description = item.getDescription();
        this.price = item.getPrice();
    }

    /**
     * Returns the description of the referred item.
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the price of the reffered item.
     * @return the price, excluding VAT
     */
    public Cash getPrice() {
        return price;
    }
    
}
