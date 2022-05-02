package se.kth.iv1350.POS.model;

import se.kth.iv1350.POS.integration.Register;
import se.kth.iv1350.POS.integration.AccountingSystem;
import se.kth.iv1350.POS.integration.SaleLog;
import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.util.Amount;
import java.time.LocalDateTime;

/**
 * Represents the sale (transaction) of all items in a specified shoppingCart.
 */
public class Sale {

	private java.time.LocalDateTime saleTime;
	private final ShoppingCart shoppingCart;
        private Amount amountPaid;
        
	private final AccountingSystem accountingSystem;
	private final SaleLog saleLog;
        private final Register register;
        private final IntegrationHandler integrationHandler;

        /**
         * Creates an instance of a sale
         * @param shoppingCart the shoppingCart with the items to be sold
         * @param integrationHandler the integration handler
         */
	public Sale(ShoppingCart shoppingCart, IntegrationHandler integrationHandler) {
            this.shoppingCart = shoppingCart;
            this.accountingSystem = integrationHandler.getAccountingSystem();
            this.saleLog = integrationHandler.getSaleLog();
            this.register = integrationHandler.getRegister();
            setTimeOfSale();
            this.integrationHandler = integrationHandler;
	}

	private void logSale() {
            saleLog.logSale(this);
            accountingSystem.updateAccounting(this);
            shoppingCart.updateInventory();
	}
        
        /**
         * Adds a payment to the sale. Triggers updates of external systems.
         * Returns a receipt with all information about the sale.
         * @param amountPaid the amount that was paid
         */
	public void addPayment(Amount amountPaid) {
            this.amountPaid = amountPaid;
            register.addToRegister(amountPaid);
            logSale();
            createReceipt().printReceipt();
	}
        
        private Receipt createReceipt() {
            Receipt receipt = new Receipt(this, integrationHandler);
            return receipt;
        }

	private void setTimeOfSale() {
            saleTime = LocalDateTime.now();
	}
        
        ShoppingCart getShoppingCart() {
            return shoppingCart;
        }
        
        LocalDateTime getTimeOfSale() {
            return saleTime;
        }
       
        Amount getAmountPaid() {
            return amountPaid;
        }
        
        Amount getChange() {
            return amountPaid.subtract(shoppingCart.getTotal());
        }
}
