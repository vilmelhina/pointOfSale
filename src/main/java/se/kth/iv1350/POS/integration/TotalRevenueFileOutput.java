package se.kth.iv1350.POS.integration;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import se.kth.iv1350.POS.model.Cash;
import se.kth.iv1350.POS.model.RegisterObserver;
import java.time.LocalDateTime;

/**
 * Observes the class Register and writes the total revenue to a file when 
 * it changes.
 */
public class TotalRevenueFileOutput implements RegisterObserver{

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

    /**
     * Called when total revenue has changes and updates the new revenue to 
     * a file.
     * @param totalRevenue the total revenue that should be added to the file
     */
    @Override
    public void totalRevenueChanged(Cash totalRevenue) {
        fileStream.println(LocalDateTime.now() + 
                " Total revenue: " + totalRevenue);
    }
    
}
