package se.kth.iv1350.POS.model;

import java.util.ArrayList;
import se.kth.iv1350.POS.integration.InventorySystem;
import se.kth.iv1350.POS.integration.InventorySystemException;
import se.kth.iv1350.POS.integration.ItemNotFoundException;

/**
 * Represents a shopping cart containing all items that are being bought.
 */
public class ShoppingCart {

	private final ArrayList<Item> items;
	private final InventorySystem inventorySystem;

        /**
         * Creates a new instance.
         * @param inventorySystem the inventory system that will be used.
         */
	public ShoppingCart(InventorySystem inventorySystem) {
            items = new ArrayList<>();
            this.inventorySystem = inventorySystem;
	}

        /**
         * Registers an item.Checks if it exists in the inventory system. 
         * Adds it to the cart if it does.
         * @param itemID the ID of the item to be registered
         * @param quantity the quantity of the item
         * @return the registered item
         * @throws ItemNotFoundException if the item ID doesn't match any item
         */
	public ItemInfoDTO registerItem(int itemID, int quantity) 
                throws ItemNotFoundException, InventorySystemException {
            Item registeredItem = searchForItemInSale(itemID);
            if(registeredItem != null)
                registeredItem.increaseQuantity(quantity);
            else
                registeredItem = registerNewItem(itemID, quantity);
            return new ItemInfoDTO(registeredItem);
	}
        
        private Item registerNewItem(int itemID, int quantity) 
                throws ItemNotFoundException, InventorySystemException {
            Item registeredItem = inventorySystem.getItemInfo(itemID);
            registeredItem.setQuantity(quantity);
            addItem(registeredItem);
            return registeredItem;
        }

        /**
         * Get the total cost of the sale.
         * @return total cost of the sale
         */
	public Cash getTotal() {
            return getSubTotal().add(getVAT());
	}
        
        Cash getSubTotal() {
            Cash total = new Cash(0, getCurrencyOfCart());
            for (Item item : items) {
                total = total.add(item.getPrice().multiply(item.getQuantity()));
            }
            return total;
        }
        
        Cash getVAT() {
            Cash totalVAT = new Cash(0, getCurrencyOfCart());
            for (Item item : items) {
                totalVAT = totalVAT.add(item.getVATAmount()
                        .multiply(item.getQuantity()));
            }
            return totalVAT;
        }
        
        /**
         * Gets the currency of the first item in the cart.
         * @return a String representing the currency
         */
        private String getCurrencyOfCart() {
            if(items.isEmpty())
                return "SEK";
            else
                return items.get(0).getPrice().getCurrency();
        }

	private void addItem(Item scannedItem) {
            items.add(scannedItem);
	}

	private Item searchForItemInSale(int itemID) {
            for (Item item : items) {
                if (item.getID() == itemID)  return item;
            }
            return null;
	}

        /**
         * Updates inventory system with the information in the shopping cart.
         * Should be called once all items have been added to the shopping cart.
         */
	public void updateInventory() {
            inventorySystem.updateInventory(this);
	}
        
        int getNumberOfItems() {
            return items.size();
        }
        
        Item getItemAtIndex(int i) {
            return items.get(i);
        }

}
