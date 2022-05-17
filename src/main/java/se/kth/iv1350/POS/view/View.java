package se.kth.iv1350.POS.view;

import se.kth.iv1350.POS.controller.Controller;
import se.kth.iv1350.POS.model.Cash;
import se.kth.iv1350.POS.model.ItemInfoDTO;

/**
 * Placeholder class for the view of the program. Contains a fake scenario that
 * demonstrates the program.
 */
public class View {

	private final Controller controller;

        /**
         * Creates a new instance. Saves the specified Controller.
         * @param controller the Controller that the View will use.
         */
	public View(Controller controller) {
            this.controller = controller;
	}
        
        /**
         * Executes a fake "sale" to demonstrate the program.
         */
        public void fakeScenario() {
            
            System.out.println("Starting sale.");
            controller.startSale();
            
            System.out.println("Registering item with ID 1 and quantity 4.");
            registerItemAndPrintInfo(1, 4);
            System.out.println("Registering item with ID 2 and quantity 3.");
            registerItemAndPrintInfo(2, 3);
            System.out.println("Registering item with ID 1 and quantity 1.");
            registerItemAndPrintInfo(1, 1);
            System.out.println("Registering item with ID 3 and quantity 6.");
            registerItemAndPrintInfo(3, 6);
            controller.endSale();
            controller.pay(new Cash(500, "SEK"));
        }
        
        private void registerItemAndPrintInfo(int itemID, int quantity) {
            ItemInfoDTO registeredItem = controller.registerItem(itemID, quantity);
            System.out.println("Scanned item: " + 
                    registeredItem.getDescription() + ", " +
                    registeredItem.getPrice());
            System.out.println("Running total: " + 
                    controller.getTotalOfSale());
            
            
        }

}
