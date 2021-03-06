package se.kth.iv1350.POS.controller;

import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.integration.InventorySystemException;
import se.kth.iv1350.POS.integration.ItemNotFoundException;
import se.kth.iv1350.POS.model.ShoppingCart;
import se.kth.iv1350.POS.model.Sale;
import se.kth.iv1350.POS.model.Cash;
import se.kth.iv1350.POS.model.ItemInfoDTO;
import se.kth.iv1350.POS.model.Register;
import se.kth.iv1350.POS.model.RegisterObserver;
import se.kth.iv1350.POS.util.ExceptionLogger;

/**
 * The controller for the POS-system. All calls to the model pass through this 
 * class.
 */
public class Controller {

    private final IntegrationHandler integrationHandler;
    private final Register register;
    private ShoppingCart shoppingCart;
    private Sale sale;

    public Controller(IntegrationHandler integrationHandler) {
        this.integrationHandler = integrationHandler;
        this.register = new Register();
        addRegisterObserver(integrationHandler.
                getTotalRevenueFileOutput());
    }

    /**
     * Starts a new sale. Has to be called before registering items or doing
     * anything else in the sale.
     */
    public void startSale() {
        shoppingCart = new ShoppingCart(integrationHandler.getInventorySystem());
    }

    /**
     * Registers a new item to the sale.If the item ID exists, then the 
     * specified quantity of the item is added to the sale.
     * @param itemID the ID of the item
     * @param quantity the quantity of the item to add to the sale.
     * @return the item that was registered
     * @throws ItemNotFoundException if no Item was found with that ID
     * @throws ConnectionIssueException if database connection failed
     */
    public ItemInfoDTO registerItem(int itemID, int quantity) 
            throws ItemNotFoundException {
        try {
            ItemInfoDTO registeredItem = 
                shoppingCart.registerItem(itemID, quantity);
        return registeredItem;
        } catch (InventorySystemException exception) {
            ExceptionLogger.logException(exception);
            throw new ConnectionIssueException();
        }
    }

    /**
     * Registers a new item to the sale.If the item ID exists, them the item 
     * is added to the sale.
     * @param itemID the ID of the item.
     * @return the item that was registered
     * @throws ItemNotFoundException if no Item was found with that ID
     * @throws ConnectionIssueException if database connection failed
     */
    public ItemInfoDTO registerItem(int itemID) throws ItemNotFoundException,
                                                    ConnectionIssueException {
        return registerItem(itemID, 1);
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
        sale = new Sale(shoppingCart, integrationHandler, register);
    }
    
    /**
     * Adds an observer that will observe the Register and be updated when the
     * total revenue changes.
     * @param observer the observer that observes the Register
     */
    public void addRegisterObserver(RegisterObserver observer) {
        register.addRegisterObserver(observer);
    }

}
