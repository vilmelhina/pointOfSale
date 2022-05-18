package se.kth.iv1350.POS.integration;

/**
 * Thrown when the searched item ID does not exist in the database.
 */
public class ItemNotFoundException extends Exception{

    /**
     * Creates a new instance with a message including what ID was searched for.
     * @param itemID the item ID that was searched for
     */
    public ItemNotFoundException(int itemID) {
        super("No item with the ID " + itemID + " was found.");
    }
    
}
