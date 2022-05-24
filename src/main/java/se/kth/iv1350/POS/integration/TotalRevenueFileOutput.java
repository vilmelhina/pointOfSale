package se.kth.iv1350.POS.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import se.kth.iv1350.POS.model.Cash;
import se.kth.iv1350.POS.model.RegisterObserverOutputTemplate;
import java.time.LocalDateTime;

/**
 * Observes the class Register and writes the total revenue to a file when 
 * it changes.
 */
public class TotalRevenueFileOutput extends RegisterObserverOutputTemplate{

    private PrintWriter fileStream;
    
    /**
     * Creates a new instance.
     */ 
    public TotalRevenueFileOutput() {
        try {
            fileStream = new PrintWriter(new FileWriter("revenue.txt"), true);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // TODO: comment
    @Override
    protected void doShowTotalRevenue(Cash totalRevenue) throws Exception {
        fileStream.println(LocalDateTime.now() + 
                " Total revenue: " + totalRevenue);
    }

    // TODO: comment
    @Override
    protected void handleErrors(Exception ex) {
        ex.printStackTrace();
    }
    
}
