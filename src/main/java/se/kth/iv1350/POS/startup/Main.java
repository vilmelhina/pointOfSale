package se.kth.iv1350.POS.startup;

import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.controller.Controller;
import se.kth.iv1350.POS.view.View;

/**
 * Starts the program
 */
public class Main {
        
    /**
     * Starts the program, creates instances of needed objects.
     *
     * @param args No command line arguments.
     */
    public static void main(String[] args) {
            
        IntegrationHandler integrationHandler;
        Controller controller;
        View view;

        integrationHandler = new IntegrationHandler();
        controller = new Controller(integrationHandler);
        view = new View(controller);
        
        view.fakeScenario();
        
	}

}
