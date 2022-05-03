package se.kth.iv1350.POS.controller;

import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.model.ShoppingCart;
import se.kth.iv1350.POS.model.Sale;
import se.kth.iv1350.POS.model.Item;
import se.kth.iv1350.POS.model.Cash;

/**
 * The controller for the POS-system. All calls to the model pass through this 
 * class.
 */
public class Controller {

    private final IntegrationHandler integrationHandler;
    private ShoppingCart shoppingCart;
    private Sale sale;

    public Controller(IntegrationHandler integrationHandler) {
        this.integrationHandler = integrationHandler;
    }

    /**
     * Starts a new sale. Has to be called before registering items or doing
     * anything else in the sale.
     */
    public void startSale() {
        shoppingCart = new ShoppingCart(integrationHandler.getInventorySystem());
    }

    /**
     * Registers a new item to the sale. If the item ID exists, then the 
     * specified quantity of the item is added to the sale.
     * @param itemID the ID of the item
     * @param quantity the quantity of the item to add to the sale.
     * @return the item that was registered
     */
    public Item registerItem(int itemID, int quantity) {
        Item registeredItem = shoppingCart.registerItem(itemID, quantity);
        return registeredItem;
    }

    /**
     * Registers a new item to the sale. If the item ID exists, them the item
     * is added to the sale.
     * @param itemID the ID of the item.
     * @return the item that was registered
     */
    public Item registerItem(int itemID) {
        Item registeredItem = shoppingCart.registerItem(itemID, 1);
        return registeredItem;
    }

    /**
     * Returns the total of the current shopping cart, including VAT.
     * @return the total cost of the shopping cart, including VAT
     */
    public Cash getTotalOfSale() {
        return shoppingCart.getTotal();
    }

    /**
     * Adds a payment to the current sale. Will also print a receipt on the
     * receipt printer.
     * @param amountPaid how much was paid
     */
    public void pay(Cash amountPaid) {
        sale.addPayment(amountPaid);
    }

    /**
     * Ends the sale. Has to be called before a payment can be made.
     */
    public void endSale() {
        sale = new Sale(shoppingCart, integrationHandler);
    }

}
