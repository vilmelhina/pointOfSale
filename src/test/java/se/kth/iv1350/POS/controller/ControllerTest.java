package se.kth.iv1350.POS.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.integration.InventorySystem;
import se.kth.iv1350.POS.integration.ItemNotFoundException;
import se.kth.iv1350.POS.model.Cash;
import se.kth.iv1350.POS.model.Item;
import se.kth.iv1350.POS.model.ItemInfoDTO;

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
        } catch (ItemNotFoundException ex) {
            // this exception is OK in this test
        }
    }
    
    @Test
    public void testRegisterItemNotNull() {
        controller.startSale();
        ItemInfoDTO registeredItem;
        try {
            registeredItem = controller.registerItem(2);
            assertNotNull(registeredItem, "Item registration did not work");
        } catch (ItemNotFoundException ex) {
            // this exception is OK in this test
        }
        
    }
    
    @Test
    public void testWrongItemID() {
        controller.startSale();
        try {
            controller.registerItem(-2);
        } catch (ItemNotFoundException ex) {
            return;
        }
        fail("No exception was thrown when wrong item ID was entered.");
    }
    
    @Test
    public void testConnectionIssueException() {
        controller.startSale();
        try {
            controller.registerItem(404);
        } catch (ConnectionIssueException exception) {
            return;
        } catch (ItemNotFoundException ex) {
            fail("Wrong exception thrown, should be connection issue.");
        }
        fail("No connection issue exception thrown.");
    }
    
    @Test
    public void testGetTotalOfSale() {
        try {
            controller.startSale();
            
            int quantity1 = 2;
            int itemID1 = 2;
            Item registeredItem1 = inventorySystem.getItemInfo(itemID1);
            controller.registerItem(itemID1,quantity1);
            
            int itemID2 = 4;
            Item registeredItem2 = inventorySystem.getItemInfo(itemID2);
            controller.registerItem(itemID2);
            
            double expectedTotal = quantity1 * 
                    (registeredItem1.getPrice().getAmount()
                    + registeredItem1.getVATAmount().getAmount())
                    + registeredItem2.getPrice().getAmount()
                    + registeredItem2.getVATAmount().getAmount();
            double total = controller.getTotalOfSale().getAmount(); 
            assertEquals(expectedTotal, total, "Total of sale was incorrect");
        } catch (ItemNotFoundException ex) {
            fail("Item in database gave ItemNotFoundException");
        }
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
