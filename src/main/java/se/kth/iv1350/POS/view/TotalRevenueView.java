package se.kth.iv1350.POS.view;

import se.kth.iv1350.POS.model.Cash;
import se.kth.iv1350.POS.model.RegisterObserver;

/**
 * Observes the class Register and writes the total revenue to the console when
 * it changes.
 */
public class TotalRevenueView implements RegisterObserver{

    /**
     * Called when the total revenue in the Register is changed. Writes the
     * total revenue to the console.
     * @param totalRevenue the total revenue that should be printed
     */
    @Override
    public void totalRevenueChanged(Cash totalRevenue) {
        System.out.println("💶 Total revenue: " + totalRevenue);
    }
    
}
