package se.kth.iv1350.POS.model;

import se.kth.iv1350.POS.model.Item;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.POS.util.Cash;

public class ItemTest {
    private Item testItem; 
    private int itemID = 124;
    private String name = "Chocolate";
    private float rateOfVAT = 30;
    private String description = "Marabou Oreo 200g";
    private Cash price;
    private float cost = 23;
    private String currency = "SEK";

    @BeforeEach
    public void setUp() {
        price = new Cash(cost, currency);
        testItem = new Item(itemID, name, rateOfVAT, 
                description, price);
    }
    
    @AfterEach
    public void tearDown() {
        price = null;
        testItem = null;
    }

    @Test
    public void testItemID() {
        assertEquals(itemID, testItem.getID(), "Item did not have correct ID.");
    }
    
    @Test
    public void testName() {
        assertEquals(name, testItem.getName(), 
                "Item did not have correct name.");
    }
    
    @Test
    public void testVAT() {
        assertEquals(rateOfVAT, testItem.getVAT(), 
                "Item did not have correct VAT.");
    }
    
    @Test
    public void testDescription() {
        assertEquals(description, testItem.getDescription(), 
                "Item did not have correct ID.");
    }
    
    @Test
    public void testPrice() {
        assertEquals(price.getAmount(), testItem.getPrice().getAmount(), 
                "Item did not have correct price.");
    }
    
    @Test
    public void testCurrency() {
        assertEquals(price.getCurrency(), testItem.getPrice().getCurrency(), 
                "Item did not have correct currency.");
    }
    
    @Test
    public void testSetQuantity() {
        int quantity = 5;
        testItem.setQuantity(quantity);
        assertEquals(quantity, testItem.getQuantity(), 
                "Item did not have correct quantity when setting quantity.");
    }
    
    @Test
    public void testIncreaseQuantity() {
        int quantity = 5;
        int increaseAmount = 7;
        testItem.setQuantity(quantity);
        testItem.increaseQuantity(increaseAmount);
        assertEquals(quantity + increaseAmount, testItem.getQuantity(), 
                "Item did not have correct quantity when increasing quantity.");
    }
}
