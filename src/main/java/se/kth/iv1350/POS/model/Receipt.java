package se.kth.iv1350.POS.model;

import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.integration.ReceiptPrinter;

/**
 * Represents a receipt. Belongs to a sale with a specified shopping cart which
 * it gets information from. Prints the receipt on a specified receiptPrinter.
 */
public class Receipt {

    private final Sale sale;
    private final ShoppingCart shoppingCart;
    private final ReceiptPrinter receiptPrinter;
    private String receipt;
    
    /**
     * Creates a new receipt
     * @param sale the sale that the receipt proves
     * @param integrationHandler the integration handler that contains the
     * receipt printer to be used.
     */
    Receipt(Sale sale, IntegrationHandler integrationHandler) {
        this.sale = sale;
        receiptPrinter = integrationHandler.getReceiptPrinter();
        shoppingCart = sale.getShoppingCart();
        generateReceiptAsString();
    }

    private void generateReceiptAsString() {
        receipt = "\nReceipt for sale at " + sale.getTimeOfSale()
                + "\n------\n";
        for(int i = 0; i < shoppingCart.getNumberOfItems(); i++) {
            receipt += shoppingCart.getItemAtIndex(i) + "\n";
        }
        receipt += String.format("\n%9s %9s\n", "subtotal:", shoppingCart.getSubTotal());
        receipt += String.format("%9s %9s\n", "VAT:", shoppingCart.getVAT());
        receipt += String.format("%9s %9s\n", "total:", shoppingCart.getTotal());
        receipt += String.format("%9s %9s\n", "paid:", sale.getAmountPaid());
        receipt += String.format("%9s %9s\n", "change:", sale.getChange());
    }
    
    /**
     * Prints the receipt on the specified receipt printer.
     */
    public void printReceipt() {
        receiptPrinter.printReceipt(receipt);
    }

}
