package se.kth.iv1350.POS.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.integration.InventorySystem;
import se.kth.iv1350.POS.model.Cash;
import se.kth.iv1350.POS.model.Item;
import se.kth.iv1350.POS.model.ItemInfoDTO;

/**
 *
 * @author vilhelmina
 */
public class ControllerTest {
    
    private IntegrationHandler integrationHandler;
    private InventorySystem inventorySystem;
    private Controller controller;
    
    @BeforeEach
    public void setUp() {
        integrationHandler = new IntegrationHandler();
        inventorySystem = integrationHandler.getInventorySystem();
        controller = new Controller(integrationHandler);
    }
    
    @AfterEach
    public void tearDown() {
        integrationHandler = null;
        controller = null;
    }
    
    @Test
    public void testStartSale() {
        controller.startSale();
        try {
            controller.registerItem(0);
        } catch (NullPointerException e) {
            fail("No instance of ShoppingCart was created in startSale");
        }
    }
    
    // When there is exception handling in the inventory system, change this to 
    // make sure it either gives exception or gives back Item (!= null)
    @Disabled //TODO: FIX
    public void testRegisterItem() {
        controller.startSale();
        ItemInfoDTO registeredItem = controller.registerItem(2);
        assertNotNull(registeredItem, "Item registration did not work");
    }
    
    @Test
    public void testGetTotalOfSale() {
        controller.startSale();
        
        int quantity1 = 2;
        int itemID1 = 2;
        Item registeredItem1 = inventorySystem.getItemInfo(itemID1);
        controller.registerItem(itemID1,quantity1);
        
        int itemID2 = 4;
        Item registeredItem2 = inventorySystem.getItemInfo(itemID2);
        controller.registerItem(itemID2);
        
        double expectedTotal = quantity1 * (registeredItem1.getPrice().getAmount()
                + registeredItem1.getVATAmount().getAmount())
                + registeredItem2.getPrice().getAmount()
                + registeredItem2.getVATAmount().getAmount();
        double total = controller.getTotalOfSale().getAmount();
        assertEquals(expectedTotal, total, "Total of sale was incorrect"); 
    }
    
    @Test
    public void testEndSale() {
        controller.startSale();
        controller.endSale();
        try {
            controller.pay(new Cash(100, "SEK"));
        } catch (NullPointerException e) {
            fail("No instance of Sale was created in endSale");
        }
    }

}
