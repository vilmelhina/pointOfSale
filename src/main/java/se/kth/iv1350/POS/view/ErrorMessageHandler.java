package se.kth.iv1350.POS.view;

import java.time.LocalDateTime;

/**
 * Shows error messages in the user interface.
 */
public class ErrorMessageHandler {
    
    void ShowErrorMessage(String message) {
        System.out.println("🛑 " + LocalDateTime.now() + " ERROR: " + message);
    }
    
}
