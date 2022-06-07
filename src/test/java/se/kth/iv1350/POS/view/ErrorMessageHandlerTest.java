package se.kth.iv1350.POS.view;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ErrorMessageHandlerTest {

    private ErrorMessageHandler errorMessageHandler;
    private Exception exception;
    private String userMessage = "The UI error message.";
    private String developerMessage = "The hidden error message.";
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = 
            new ByteArrayOutputStream();
    private String output;
    
    @BeforeEach
    public void setUp() {
        errorMessageHandler = new ErrorMessageHandler();
        exception = new Exception(developerMessage);
        System.setOut(new PrintStream(outContent));
        errorMessageHandler.showErrorMessage(userMessage);
        output = outContent.toString();
    }
    
    @AfterEach
    public void tearDown() {
        exception = null;
        System.setOut(originalOut);
        output = null;
    }
    
    @Test
    public void testTimeIsPrinted() {
        String timeNow = LocalDateTime.now().toString().substring(0, 18);
        boolean timeIsPrinted = output.contains(timeNow);
        assertTrue(timeIsPrinted, "Time was not printed.");
    }
    
    @Test
    public void testErrorMessageIsPrinted() {
        boolean errorMessageIsPrinted = output.contains(userMessage);
        assertTrue(errorMessageIsPrinted, "Error message was not printed.");
    }
    
    @Test
    public void testDeveloperMessageIsNotPrinted() {
        boolean developerMessageIsPrinted = output.contains(developerMessage);
        assertFalse(developerMessageIsPrinted, 
                "Message intended for developer is printed to user.");
    }

}
