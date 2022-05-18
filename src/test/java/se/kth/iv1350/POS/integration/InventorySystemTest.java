package se.kth.iv1350.POS.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InventorySystemTest {
    
    private InventorySystem inventorySystem;
    
    @BeforeEach
    public void setUp() {
        inventorySystem = new InventorySystem();
    }
    
    @AfterEach
    public void tearDown() {
        inventorySystem = null;
    }

    @Test
    public void testWrongItemID() {
        try {
            inventorySystem.getItemInfo(-2);
        } catch (ItemNotFoundException ex) {
            return;
        }
        fail("No exception was thrown when wrong item ID was entered.");
    }
    
    @Test
    public void testConnectionIssueException() {
        try {
            inventorySystem.getItemInfo(404);
        } catch (InventorySystemException exception) {
            return;
        } catch (ItemNotFoundException ex) {
            fail("Wrong exception thrown, should be connection issue.");
        }
        fail("No connection issue exception thrown.");
    }
}
