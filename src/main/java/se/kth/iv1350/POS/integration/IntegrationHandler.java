package se.kth.iv1350.POS.integration;

/**
 * Creates all external system handlers: AccountingSyste, DiscountDatabase, 
 * InventorySystem, SaleLog, Register.
 */
public class IntegrationHandler {

    private AccountingSystem accountingSystem;
    private InventorySystem inventorySystem;
    private SaleLog saleLog;
    private Register register;
    private ReceiptPrinter receiptPrinter;

    /**
     * Creates an instance, which in turn creates instances of the external
     * system handlers.
     */
    public IntegrationHandler() {
        accountingSystem = new AccountingSystem();
        inventorySystem = new InventorySystem();
        saleLog = new SaleLog();
        register = new Register();
        receiptPrinter = new ReceiptPrinter();
    }

    /**
     * Returns the created instance of the accounting system handler.
     * @return the accounting system handler
     */
    public AccountingSystem getAccountingSystem() {
        return accountingSystem;
    }

    /**
     * Returns the created instance of the inventory system handler.
     * @return the inventory system handler
     */
    public InventorySystem getInventorySystem() {
        return inventorySystem;
    }

    /**
     * Returns the created instance of the sale log handler.
     * @return the sale log handler
     */
    public SaleLog getSaleLog() {
        return saleLog;
    }
    
    /**
     * Returns the created instance of the register handler.
     * @return the register handler
     */
    public Register getRegister() {
        return register;
    }
    
    /**
     * Returns the created instance of the receipt Printer.
     * @return the register handler
     */
    public ReceiptPrinter getReceiptPrinter() {
        return receiptPrinter;
    }

}
