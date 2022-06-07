package se.kth.iv1350.POS.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.POS.model.Cash;

public class TotalRevenueViewTest {
    
    TotalRevenueView totalRevenueView;
    Cash totalRevenue;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = 
            new ByteArrayOutputStream();
    private String output;
    
   @BeforeEach
    public void setUp() {
        totalRevenueView = new TotalRevenueView();
        totalRevenue = new Cash(23, "SEK");
        System.setOut(new PrintStream(outContent));
        totalRevenueView.totalRevenueChanged(totalRevenue);
        output = outContent.toString();
    }
    
    @AfterEach
    public void tearDown() {
        totalRevenueView = null;
        totalRevenue = null;
        System.setOut(originalOut);
        output = null;
    }

    @Test
    public void testTotalRevenueShown() {
        boolean totalRevenueShown = output.contains(totalRevenue.toString());
        assertTrue(totalRevenueShown, "Total revenue was not printed.");
    }

    
}
