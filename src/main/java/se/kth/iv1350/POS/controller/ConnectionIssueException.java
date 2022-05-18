package se.kth.iv1350.POS.controller;

/**
 * Thrown when there is an issue with a network connection, for example to a
 * database.
 */
public class ConnectionIssueException extends RuntimeException{
    
    /**
     * Creates an instance with a message of what went wrong.
     */
    public ConnectionIssueException() {
        super("There is an issue with a network connection. Try again.");
    }
    
}
