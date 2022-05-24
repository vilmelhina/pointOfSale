package se.kth.iv1350.POS.view;

import se.kth.iv1350.POS.model.Cash;
import se.kth.iv1350.POS.model.RegisterObserverOutputTemplate;

/**
 * Observes the class Register and writes the total revenue to the console when
 * it changes.
 */
public class TotalRevenueView extends RegisterObserverOutputTemplate{

    /**
     * Shows the total revenue to the view.
     * @param totalRevenue the total revenue
     */
    @Override
    protected void doShowTotalRevenue(Cash totalRevenue){
        System.out.println("💶 Total revenue: " + totalRevenue);
    }

    /**
     * Would handle exceptions if there were any that could be thrown.
     * @param exception the exception thrown
     */
    @Override
    protected void handleErrors(Exception exception) {
        
    }


    
}
