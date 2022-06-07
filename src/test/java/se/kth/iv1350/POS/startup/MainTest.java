package se.kth.iv1350.POS.startup;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author vilhelmina
 */
public class MainTest {
    
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = 
            new ByteArrayOutputStream();
    private String[] args;
    private String output;
    
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        Main.main(args);
        output = outContent.toString();
    }
    
    @AfterEach
    public void tearDown() {
        output = null;
        System.setOut(originalOut);
    }

    @Test
    public void testFakeScenarioPrintsItemInfo() {
        boolean printsItemInfo = 
            output.contains("Registering item with ID 1 and quantity 4.");
        assertTrue(printsItemInfo, "Fake scenario doesn't print item info.");
    }
    
    @Test
    public void testFakeScenarioPrintsItemNotFoundError() {
        String expected = 
                "The searched item ID 5 can not be found. Try again.";
        boolean printsNotFoundError = output.contains(expected);
        assertTrue(printsNotFoundError, 
                "Fake scenario doesn't print item not found error.");
    }
    
    @Test
    public void testFakeScenarioPrintsPayment() {
        String expected = "Paying 500SEK";
        boolean printsPayment = output.contains(expected);
        assertTrue(printsPayment, 
                "Fake scenario doesn't print payment info.");
    }
    
    @Test
    public void testFakeScenarioPrintsReceipt() {
        String expected = "Receipt for sale";
        boolean printsReceipt = output.contains(expected);
        assertTrue(printsReceipt, 
                "Fake scenario doesn't print receipt.");
    }
    
    @Test
    public void testFakeScenarioPrintsTotalRevenue() {
        String expected = "Total revenue: 110.1 SEK";
        boolean printsTotalRevenue = output.contains(expected);
        assertTrue(printsTotalRevenue, 
                "Fake scenario doesn't print total revenue.");
    }
    
    @Test
    public void testFakeScenarioPrintsConnectionIssue() {
        String expected = "There is an issue with a network connection. Try again.";
        boolean printsConnectionIssue = output.contains(expected);
        assertTrue(printsConnectionIssue, 
                "Fake scenario doesn't print connection error message.");
    }
    
}
