package se.kth.iv1350.POS.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.integration.InventorySystem;

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
        testCart.registerItem(itemID, 2);
        testCart.registerItem(itemID, 7);
        int numberOfItems = testCart.getNumberOfItems();
        assertEquals(1, numberOfItems,
                "Scanning the same item again does not add to the same item.");
    }
    
    // Will be used when exceptions are added to the code.
    @Disabled
    public void testWrongItemID() {
        testCart.registerItem(6, 1);
    }
    
    @Test
    public void testGetTotal() {
        int quantity1 = 2;
        Item testItem1 = inventorySystem.getItemInfo(itemID);
        testCart.registerItem(itemID, quantity1);
        Cash price1 = testItem1.getPrice();
        Cash vat1 = price1.multiply(testItem1.getVAT());
        
        int quantity2 = 3;
        Item testItem2 = inventorySystem.getItemInfo(otherItemID);
        testCart.registerItem(otherItemID, quantity2);
        Cash price2 = testItem2.getPrice();
        Cash vat2 = price2.multiply(testItem2.getVAT());
        
        double expTotal = quantity1 * (price1.getAmount() + vat1.getAmount())
                + quantity2 * (price2.getAmount() + vat2.getAmount());
        assertEquals(expTotal, testCart.getTotal().getAmount(),
                "Shopping cart total is incorrect.");
    }
    
    @Test
    public void testGetSubTotal() {
        int quantity1 = 2;
        Item testItem1 = inventorySystem.getItemInfo(itemID);
        testCart.registerItem(itemID, quantity1);
        Cash price1 = testItem1.getPrice().multiply(quantity1);
        
        int quantity2 = 3;
        Item testItem2 = inventorySystem.getItemInfo(otherItemID);
        testCart.registerItem(otherItemID, quantity2);
        Cash price2 = testItem2.getPrice().multiply(quantity2);
        
        double expectedSubtotal = price1.add(price2).getAmount();
        double actualSubtotal = testCart.getSubTotal().getAmount();
        assertEquals(expectedSubtotal, actualSubtotal,
                "Shopping cart subtotal is incorrect.");
    }
    
    @Test
    public void testGetVAT() {
        int quantity1 = 2;
        Item testItem1 = inventorySystem.getItemInfo(itemID);
        testCart.registerItem(itemID, quantity1);
        Cash vat1 = testItem1.getVATAmount().multiply(quantity1);
        
        int quantity2 = 3;
        Item testItem2 = inventorySystem.getItemInfo(otherItemID);
        testCart.registerItem(otherItemID, quantity2);
        Cash vat2 = testItem2.getVATAmount().multiply(quantity2);
        
        double expectedVAT = vat1.add(vat2).getAmount();
        double actualVAT = testCart.getVAT().getAmount();
        assertEquals(expectedVAT, actualVAT,
                "Shopping cart VAT is incorrect.");
    }
}
