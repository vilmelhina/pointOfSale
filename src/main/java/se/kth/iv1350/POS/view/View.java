package se.kth.iv1350.POS.view;

import se.kth.iv1350.POS.controller.Controller;
import se.kth.iv1350.POS.util.Cash;

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
            controller.startSale();
            controller.registerItem(1, 4);
            System.out.println(controller.getTotalOfSale());
            controller.registerItem(2,3);
            System.out.println(controller.getTotalOfSale());
            controller.registerItem(1);
            System.out.println(controller.getTotalOfSale());
            controller.registerItem(3,6);
            System.out.println(controller.getTotalOfSale());
            controller.endSale();
            controller.pay(new Cash(500, "SEK"));
        }

}
