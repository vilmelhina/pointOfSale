package se.kth.iv1350.POS.model;

/**
 * 
 */
public abstract class RegisterObserverTemplate implements RegisterObserver{

    @Override
    public void totalRevenueChanged(Cash totalRevenue) {
        showTotalIncome(totalRevenue);
    }

    private void showTotalIncome(Cash totalRevenue) {
        try {
            doShowTotalIncome(totalRevenue);
        } catch (Exception e) {
            handleErrors(e);
        }
    }

    protected abstract void doShowTotalIncome(Cash totalRevenue) 
            throws Exception;

    protected abstract void handleErrors(Exception e);
}
