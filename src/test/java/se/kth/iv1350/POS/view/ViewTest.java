package se.kth.iv1350.POS.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.POS.controller.Controller;
import se.kth.iv1350.POS.integration.IntegrationHandler;

public class ViewTest {
    
    IntegrationHandler integrationHandler;
    Controller controller;
    View view;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = 
            new ByteArrayOutputStream();
    
    @BeforeEach
    public void setUp() {
        integrationHandler = new IntegrationHandler();
        controller = new Controller(integrationHandler);
        view = new View(controller);
        System.setOut(new PrintStream(outContent));
    }
    
    @AfterEach
    public void tearDown() {
        integrationHandler = null;
        controller = null;
        view = null;
        System.setOut(originalOut);
    }

    @Test
    public void testFakeScenarioPrintsItemInfo() {
        view.fakeScenario();
        String output = outContent.toString();
        boolean printsItemInfo = 
            output.contains("Registering item with ID 1 and quantity 4.");
        assertTrue(printsItemInfo, "Fake scenario doesn't print item info.");
    }
    
    @Test
    public void testFakeScenarioPrintsItemNotFoundError() {
        view.fakeScenario();
        String output = outContent.toString();
        String expected = 
                "The searched item ID 5 can not be found. Try again.";
        boolean printsNotFoundError = output.contains(expected);
        assertTrue(printsNotFoundError, 
                "Fake scenario doesn't print item not found error.");
    }
    
    @Test
    public void testFakeScenarioPrintsPayment() {
        view.fakeScenario();
        String output = outContent.toString();
        String expected = "Paying 500SEK";
        boolean printsPayment = output.contains(expected);
        assertTrue(printsPayment, 
                "Fake scenario doesn't print payment info.");
    }
    
    @Test
    public void testFakeScenarioPrintsReceipt() {
        view.fakeScenario();
        String output = outContent.toString();
        String expected = "Receipt for sale";
        boolean printsReceipt = output.contains(expected);
        assertTrue(printsReceipt, 
                "Fake scenario doesn't print receipt.");
    }
    
    @Test
    public void testFakeScenarioPrintsTotalRevenue() {
        view.fakeScenario();
        String output = outContent.toString();
        String expected = "Total revenue: 110.1 SEK";
        boolean printsTotalRevenue = output.contains(expected);
        assertTrue(printsTotalRevenue, 
                "Fake scenario doesn't print total revenue.");
    }
    
    @Test
    public void testFakeScenarioPrintsConnectionIssue() {
        view.fakeScenario();
        String output = outContent.toString();
        String expected = "There is an issue with a network connection. Try again.";
        boolean printsConnectionIssue = output.contains(expected);
        assertTrue(printsConnectionIssue, 
                "Fake scenario doesn't print connection error message.");
    }

}
