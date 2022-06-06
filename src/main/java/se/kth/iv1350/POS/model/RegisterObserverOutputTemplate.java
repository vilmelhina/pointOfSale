package se.kth.iv1350.POS.model;

/**
 * A template for a register observer that outputs the total revenue when it
 * has changed.
 */
public abstract class RegisterObserverOutputTemplate 
        implements RegisterObserver {

    /**
     * Called when total revenue has been changed.
     * @param totalRevenue the new total revenue
     */
    @Override
    public void totalRevenueChanged(Cash totalRevenue) {
        showTotalRevenue(totalRevenue);
    }

    private void showTotalRevenue(Cash totalRevenue) {
        try {
            doShowTotalRevenue(totalRevenue);
        } catch (Exception exception) {
            handleErrors(exception);
        }
    }

    /**
     * Shows the total revenue.
     * @param totalRevenue the new total revenue
     * @throws Exception any exception thrown when showing the total revenue
     */
    protected abstract void doShowTotalRevenue(Cash totalRevenue) 
            throws Exception;
    
    /**
     * Error handling for any exceptions thrown when showing the total revenue.
     * @param exception the exception thrown
     */
    protected abstract void handleErrors(Exception exception);
    
}