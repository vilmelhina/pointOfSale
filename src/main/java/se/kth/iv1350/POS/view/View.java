package se.kth.iv1350.POS.view;

import se.kth.iv1350.POS.controller.Controller;
import se.kth.iv1350.POS.integration.ItemNotFoundException;
import se.kth.iv1350.POS.model.Cash;
import se.kth.iv1350.POS.controller.ConnectionIssueException;
import se.kth.iv1350.POS.model.ItemInfoDTO;

/**
 * Placeholder class for the view of the program. Contains a fake scenario that
 * demonstrates the program.
 */
public class View {

	private final Controller controller;
        private ErrorMessageHandler errorMessageHandler;
        private TotalRevenueView totalRevenueView;

        /**
         * Creates a new instance. Saves the specified Controller.
         * @param controller the Controller that the View will use.
         */
	public View(Controller controller) {
            this.controller = controller;
            totalRevenueView = new TotalRevenueView();
            controller.addRegisterObserver(totalRevenueView);
            errorMessageHandler = new ErrorMessageHandler();
	}
        
        /**
         * Executes a fake "sale" to demonstrate the program.
         */
        public void fakeScenario() {
            
            System.out.println("Starting sale.");
            controller.startSale();
            
            registerItemAndPrintInfo(1, 4);
            registerItemAndPrintInfo(2, 3);
            registerItemAndPrintInfo(1, 1);
            registerItemAndPrintInfo(3, 6);
            registerItemAndPrintInfo(5, 2);
            registerItemAndPrintInfo(-1,4);
            
            System.out.println("Ending sale.");
            controller.endSale();
            
            System.out.println("Paying 500SEK.");
            controller.pay(new Cash(500, "SEK"));
            
            System.out.println("Starting sale.");
            controller.startSale();
            System.out.println("Breaking the \"database\":");
            registerItemAndPrintInfo(404, 1);
        }
        
        private void registerItemAndPrintInfo(int itemID, int quantity) {
            System.out.println("Registering item with ID " + itemID 
                    + " and quantity " + quantity + ".");
            try {
                ItemInfoDTO registeredItem = 
                        controller.registerItem(itemID, quantity);
                System.out.println("Scanned item: " +
                        registeredItem.getDescription() + ", " +
                        registeredItem.getPrice());
                System.out.println("Running total: " +
                        controller.getTotalOfSale());
            } catch(ItemNotFoundException | ConnectionIssueException ex) {
                errorMessageHandler.showErrorMessage(ex.getMessage());
            }
        }

}
