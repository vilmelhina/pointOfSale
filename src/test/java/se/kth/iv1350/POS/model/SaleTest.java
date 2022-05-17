package se.kth.iv1350.POS.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.integration.InventorySystem;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Disabled;

/**
 * Tests the class Sale.
 */
public class SaleTest {
    
    private IntegrationHandler integrationHandler;
    private InventorySystem inventorySystem;
    private ShoppingCart shoppingCart;
    private Item itemInCart;
    private Sale sale;
    
    @BeforeEach //TODO: FIX
    public void setUp() {
        integrationHandler = new IntegrationHandler();
        inventorySystem = integrationHandler.getInventorySystem();
        shoppingCart = new ShoppingCart(inventorySystem);
        itemInCart = null;//shoppingCart.registerItem(0, 1);
        sale = new Sale(shoppingCart, integrationHandler);
    }
    
    @AfterEach
    public void tearDown() {
        integrationHandler = null;
        inventorySystem = null;
        shoppingCart = null;
        sale = null;
    }
    
    @Disabled //TODO: FIX
    public void testTimeOfSale() {
        LocalDateTime expectedTime = LocalDateTime.now().withNano(0);
        LocalDateTime actualTime = sale.getTimeOfSale().withNano(0);
        assertEquals(expectedTime, actualTime, "Time of sale was incorrect.");
        
    }
    
    @Disabled //TODO: FIX
    public void testGetChange() {
        Cash costOfSale = 
                itemInCart.getPrice().add(itemInCart.getVATAmount());
        Cash amountPaid = new Cash(40, "SEK");
        sale.addPayment(amountPaid);
        double expectedChange = amountPaid.subtract(costOfSale).getAmount();
        double actualChange = sale.getChange().getAmount();
        assertEquals(expectedChange, actualChange, "Change for sale is wrong.");
    }
}
