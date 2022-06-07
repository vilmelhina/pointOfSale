package se.kth.iv1350.POS.util;

import java.time.LocalDateTime;

/**
 * Logs an exception. Currently prints to System.out but should actually print
 * to a file.
 */
public class ExceptionLogger {
    
    public static void logException(Exception exception) {
        System.out.println("📄 INTENDED FOR LOG: " 
                + LocalDateTime.now() + ": " + exception.getMessage());
        exception.printStackTrace();
        System.out.print("\n");
    }
    
}
