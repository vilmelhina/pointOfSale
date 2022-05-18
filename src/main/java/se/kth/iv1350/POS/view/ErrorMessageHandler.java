package se.kth.iv1350.POS.view;

import java.time.LocalDateTime;

/**
 * Shows error messages in the user interface.
 */
public class ErrorMessageHandler {
    
    /**
     * Prints error message to UI.
     * @param message the error message to be printed.
     */
    void showErrorMessage(String message) {
        System.out.println("🛑 INTENDED FOR UI:  " + LocalDateTime.now() + " ERROR: " + message);
    }
    
}
