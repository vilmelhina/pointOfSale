package se.kth.iv1350.POS.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item testItem; 
    private final int itemID = 124;
    private final String description = "Chocolate";
    private final double rateOfVAT = 0.3;
    private Cash price;
    private final float cost = 23;
    private final String currency = "SEK";
    private int quantity = 5;

    @BeforeEach
    public void setUp() {
        price = new Cash(cost, currency);
        testItem = new Item(itemID, description, rateOfVAT, price);
        testItem.setQuantity(quantity);
    }
    
    @AfterEach
    public void tearDown() {
        price = null;
        testItem = null;
    }

    @Test
    public void testIncreaseQuantity() {
        int increaseAmount = 7;
        testItem.increaseQuantity(increaseAmount);
        assertEquals(quantity + increaseAmount, testItem.getQuantity(), 
                "Item did not have correct quantity when increasing quantity.");
    }
    
    @Test
    public void testVATAmount() {
        double expectedVATAmount = cost * rateOfVAT;
        double actualVATAmount = testItem.getVATAmount().getAmount();
        assertEquals(expectedVATAmount, actualVATAmount, 
                "VAT amount was not right.");
    }
    
    @Test
    public void testToString() {
        String result = testItem.toString();
        System.out.println(testItem);
        System.out.println(result);
        boolean containsItemID = result.contains(Integer.toString(itemID));
        boolean containsQuantity = result.contains(Integer.toString(quantity));
        boolean containsName = result.contains(description);
        boolean containsPrice = 
                result.contains(Double.toString(cost));
        assertTrue(containsItemID && containsQuantity && containsName && 
                containsPrice, "String does not have all information");
    }   
}
