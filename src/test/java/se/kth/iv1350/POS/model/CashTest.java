package se.kth.iv1350.POS.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author vilhelmina
 */
public class CashTest {
    
    private Cash cash1;
    private double amount1 = 114.8;
    private Cash cash2;
    private double amount2 = 43.6;
    private String currency = "SEK";
    private double multiplier = 0.4;
    
    @BeforeEach
    public void setUp() {
        cash1 = new Cash(amount1, currency);
        cash2 = new Cash(amount2, currency);
    }
    
    @AfterEach
    public void tearDown() {
        cash1 = null;
        cash2 = null;
    }

    @Test
    public void testAdd() {
        double sum = cash1.add(cash2).getAmount();
        double expectedSum = amount1 + amount2;
        assertEquals(expectedSum, sum, "Sum of Cash was wrong.");
    }
    
    @Test
    public void testSubtract() {
        double diff = cash1.subtract(cash2).getAmount();
        double expectedDiff = amount1 - amount2;
        assertEquals(expectedDiff, diff, "Difference of Cash was wrong.");
    }
    
    @Test
    public void testMultiply() {
        double product = cash1.multiply(multiplier).getAmount();
        double expectedProduct = amount1 * multiplier;
        assertEquals(expectedProduct, product, "Product of Cash was wrong.");
    }
    
}
