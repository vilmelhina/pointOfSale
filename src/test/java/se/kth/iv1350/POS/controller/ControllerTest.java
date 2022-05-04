package se.kth.iv1350.POS.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.model.Cash;
import se.kth.iv1350.POS.model.Item;

/**
 *
 * @author vilhelmina
 */
public class ControllerTest {
    
    private IntegrationHandler integrationHandler;
    private Controller controller;
    
    @BeforeEach
    public void setUp() {
        integrationHandler = new IntegrationHandler();
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
    @Test
    public void testRegisterItem() {
        controller.startSale();
        Item registeredItem = controller.registerItem(2);
        assertNotNull(registeredItem, "Item registration did not work");
    }
    
    @Test
    public void testGetTotalOfSale() {
        controller.startSale();
        Item registeredItem1 = controller.registerItem(2);
        Item registeredItem2 = controller.registerItem(4);
        double expectedTotal = registeredItem1.getPrice().getAmount()
                + registeredItem1.getVATAmount().getAmount()
                + registeredItem2.getPrice().getAmount()
                + registeredItem2.getVATAmount().getAmount();
        double total = controller.getTotalOfSale().getAmount();
        assertEquals(total, expectedTotal, "Total of sale was incorrect"); 
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
