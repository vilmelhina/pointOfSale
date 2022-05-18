package se.kth.iv1350.POS.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.integration.InventorySystem;
import java.time.LocalDateTime;
import se.kth.iv1350.POS.integration.InventorySystemException;
import se.kth.iv1350.POS.integration.ItemNotFoundException;

/**
 * Tests the class Sale.
 */
public class SaleTest {
    
    private IntegrationHandler integrationHandler;
    private InventorySystem inventorySystem;
    private ShoppingCart shoppingCart;
    private Register register;
    private final int itemIDInCart = 0;
    private Sale sale;
    
    @BeforeEach
    public void setUp() throws InventorySystemException, ItemNotFoundException{
        integrationHandler = new IntegrationHandler();
        inventorySystem = integrationHandler.getInventorySystem();
        shoppingCart = new ShoppingCart(inventorySystem);
        register = new Register();
        shoppingCart.registerItem(itemIDInCart, 1);
        sale = new Sale(shoppingCart, integrationHandler, register);
    }
    
    @AfterEach
    public void tearDown() {
        integrationHandler = null;
        inventorySystem = null;
        shoppingCart = null;
        sale = null;
    }
    
    @Test
    public void testTimeOfSale() {
        LocalDateTime expectedTime = LocalDateTime.now().withNano(0);
        LocalDateTime actualTime = sale.getTimeOfSale().withNano(0);
        assertEquals(expectedTime, actualTime, "Time of sale was incorrect.");
        
    }
    
    @Test
    public void testGetChange() {
        try {
            Item itemInCart = inventorySystem.getItemInfo(itemIDInCart);
            Cash costOfSale =
                    itemInCart.getPrice().add(itemInCart.getVATAmount());
            Cash amountPaid = new Cash(40, "SEK");
            sale.addPayment(amountPaid);
            double expectedChange = amountPaid.subtract(costOfSale).getAmount();
            double actualChange = sale.getChange().getAmount();
            assertEquals(expectedChange, actualChange, 
                    "Change for sale is wrong.");
        } catch (ItemNotFoundException ex) {
            fail("Unexpected ItemNotFoundException.");
        } catch (InventorySystemException ex) {
            fail("Unexpected InventorySystemException.");
        }
    }
}
