package se.kth.iv1350.POS.model;

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.integration.InventorySystem;
import java.time.LocalDateTime;

/**
 * Tests the class Sale.
 */
public class SaleTest {
    
    private IntegrationHandler integrationHandler;
    private InventorySystem inventorySystem;
    private ShoppingCart shoppingCart;
    private Sale sale;
    
    @BeforeEach
    public void setUp() {
        integrationHandler = new IntegrationHandler();
        inventorySystem = integrationHandler.getInventorySystem();
        shoppingCart = new ShoppingCart(inventorySystem);
        sale = new Sale(shoppingCart, integrationHandler);
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

}
