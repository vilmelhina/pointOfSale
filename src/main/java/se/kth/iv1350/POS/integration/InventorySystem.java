package se.kth.iv1350.POS.integration;

import se.kth.iv1350.POS.model.Item;
import se.kth.iv1350.POS.model.ShoppingCart;
import se.kth.iv1350.POS.model.Cash;

/**
 * Communicates with an inventory system.
 */
public class InventorySystem {

        private final Item[] fakeItemList = new Item[5];
        private final int FAKEDATABASEPROBLEM = 404;
    
        /**
         * Creates an instance. Currently creates instances of fake "items" to
         * demonstrate the program.
         */
	InventorySystem() {
            fakeItemList[0] = new Item(0, "Chocolate", 0.2, 
                    new Cash(15, "SEK"));
            fakeItemList[1] = new Item(1, "Milk", 0.15, 
                    new Cash(12, "SEK"));
            fakeItemList[2] = new Item(2, "Carrot", 0.1, 
                    new Cash(2, "SEK"));
            fakeItemList[3] = new Item(3, "Banana", 0.15, 
                    new Cash(5, "SEK"));
            fakeItemList[4] = new Item(4, "Pepsi", 0.2, 
                    new Cash(25, "SEK"));
	}

        /**
         * Placeholder for a method that takes an item ID and returns an 
         * Item containing available information from the inventory system.
         * @param itemID
         * @return the item that was requested.
         * @throws ItemNotFoundException if there is no matching Item
         * @throws InventorySystemException if there is a database problem
         */
	public Item getItemInfo(int itemID) throws ItemNotFoundException, 
                                                InventorySystemException {
            if(itemID == FAKEDATABASEPROBLEM) {
                throw new InventorySystemException("Super detailed message"
                        + " about the database connection problem");
            }
            else if (itemID < 0 || itemID >= fakeItemList.length) {
                throw new ItemNotFoundException(itemID);
            }
            return fakeItemList[itemID];
            
	}

        /**
         * Placeholder for a class that updates the inventory based on a 
         * finished shopping cart.
         * @param shoppingCart the ShoppingCart that the information will be
         * taken from.
         */
	public void updateInventory(ShoppingCart shoppingCart) {

	}

}
