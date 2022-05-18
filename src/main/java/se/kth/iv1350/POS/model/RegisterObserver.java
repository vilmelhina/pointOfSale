package se.kth.iv1350.POS.model;

/**
 * An observer interface for receiving notifications when the total revenue in
 * the Register changes. 
 */
public interface RegisterObserver {
    void totalRevenueChanged(Cash totalRevenue);
}
