package se.kth.iv1350.POS.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ItemTest {
    private Item testItem; 
    private int itemID = 124;
    private String name = "Chocolate";
    private double rateOfVAT = 0.3;
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
    public void testIncreaseQuantity() {
        int quantity = 5;
        int increaseAmount = 7;
        testItem.setQuantity(quantity);
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
}
