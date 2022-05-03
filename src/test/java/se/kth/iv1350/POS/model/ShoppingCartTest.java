package se.kth.iv1350.POS.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.integration.InventorySystem;

/**
 *
 * @author vilhelmina
 */
public class ShoppingCartTest {
    private IntegrationHandler integrationHandler;
    private InventorySystem inventorySystem;
    private ShoppingCart testCart;
    int itemID = 2;
    int otherItemID = 3;
    
    @BeforeEach
    public void setUp() {
        integrationHandler = new IntegrationHandler();
        inventorySystem = integrationHandler.getInventorySystem();
        testCart = new ShoppingCart(inventorySystem);
    }
    
    @AfterEach
    public void tearDown() {
        testCart = null;
    }

    @Test
    public void testNewItem() {
        testCart.registerItem(itemID, 3);
        int expectedNumberOfItems = 1;
        int numberOfItems = testCart.getNumberOfItems();
        assertEquals(expectedNumberOfItems, numberOfItems,
                "Item was not added to cart.");
    }
    
    @Test
    public void testSameItemAgain() {
        int quantity1 = 3;
        int quantity2 = 7;
        testCart.registerItem(itemID, quantity1);
        Item testItem = testCart.registerItem(itemID, quantity2);
        assertEquals(quantity1 + quantity2, testItem.getQuantity(),
                "Scanning the same item again does not give the right "
                        + "quantity");
    }
    
    @Test
    public void testWrongItemID() {
        testCart.registerItem(6, 1);
    }
    
    @Test
    public void testGetTotal() {
        Item testItem1 = testCart.registerItem(itemID, 1);
        Item testItem2 = testCart.registerItem(otherItemID, 1);
        Cash price1 = testItem1.getPrice();
        Cash price2 = testItem2.getPrice();
        Cash vat1 = price1.multiply(testItem1.getVAT());
        Cash vat2 = price2.multiply(testItem2.getVAT());
        
        Cash expectedTotal = price1.add(price2).add(vat1).add(vat2);
        System.out.println(expectedTotal.getAmount());
        assertEquals(expectedTotal.getAmount(), testCart.getTotal().getAmount(),
                "Shopping cart total is incorrect.");
    }
}
