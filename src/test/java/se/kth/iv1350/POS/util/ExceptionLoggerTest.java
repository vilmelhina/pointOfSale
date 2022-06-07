package se.kth.iv1350.POS.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ExceptionLoggerTest {

    private Exception exception;
    private String errorMessage = "The error message.";
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = 
            new ByteArrayOutputStream();
    private String output;
   
    @BeforeEach
    public void setUp() {
        exception = new Exception(errorMessage);
        System.setOut(new PrintStream(outContent));
        ExceptionLogger.logException(exception);
        output = outContent.toString();
    }
    
    @AfterEach
    public void tearDown() {
        exception = null;
        System.setOut(originalOut);
        output = null;
    }
    
    @Test
    public void testLogEntryHasTime() {
        String timeNow = LocalDateTime.now().toString().substring(0, 18);
        boolean logEntryContainsTime = output.contains(timeNow);
        assertTrue(logEntryContainsTime, "Log entry does not contain time.");
    }
    
    @Test
    public void testLogEntryErrorMessage() {
        boolean logEntryContainsErrorMessage = output.contains(errorMessage);
        assertTrue(logEntryContainsErrorMessage, 
                "Log entry does not contain error message.");
    }
    
}
