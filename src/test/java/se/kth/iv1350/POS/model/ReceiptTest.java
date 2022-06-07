package se.kth.iv1350.POS.model;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.POS.integration.IntegrationHandler;
import se.kth.iv1350.POS.integration.InventorySystem;
import se.kth.iv1350.POS.integration.InventorySystemException;
import se.kth.iv1350.POS.integration.ItemNotFoundException;

public class ReceiptTest {
    
    private IntegrationHandler integrationHandler;
    private InventorySystem inventorySystem;
    private ShoppingCart shoppingCart;
    private Register register;
    private final int itemIDInCart = 0;
    private Sale sale;
    private Receipt receipt;
    private final PrintStream originalOut = System.out;
    private final ByteArrayOutputStream outContent = 
            new ByteArrayOutputStream();
    String receiptOutput;
    
    @BeforeEach
    public void setUp() throws ItemNotFoundException, InventorySystemException {
        integrationHandler = new IntegrationHandler();
        inventorySystem = integrationHandler.getInventorySystem();
        shoppingCart = new ShoppingCart(inventorySystem);
        register = new Register();
        shoppingCart.registerItem(itemIDInCart, 1);
        sale = new Sale(shoppingCart, integrationHandler, register);
        sale.addPayment(new Cash(200, "SEK"));
        receipt = new Receipt(sale, integrationHandler);
        
        System.setOut(new PrintStream(outContent));
        receipt.printReceipt();
        receiptOutput = outContent.toString();
    }
    
    @AfterEach
    public void tearDown() {
        integrationHandler = null;
        inventorySystem = null;
        shoppingCart = null;
        register = null;
        shoppingCart = null;
        sale = null;
        receipt = null;
        
        System.setOut(originalOut);
    }

    @Test
    public void testReceiptHasTimeOfSale() {
        String time = sale.getTimeOfSale().toString();
        boolean receiptContainsTime = receiptOutput.contains(time);
        assertTrue(receiptContainsTime, 
                "Receipt does not contain or has the wrong time of sale.");
    }
    
    @Test
    public void testReceiptHasItem() {
        String item = shoppingCart.getItemAtIndex(0).toString();
        boolean receiptContainsItem = receiptOutput.contains(item);
        assertTrue(receiptContainsItem, 
                "Receipt does not contain or has the wrong item in sale.");
    }
    
    @Test
    public void testReceiptHasSubtotal() {
        String subtotal = shoppingCart.getSubTotal().toString();
        boolean receiptContainsSubtotal = receiptOutput.contains(subtotal);
        assertTrue(receiptContainsSubtotal, 
                "Receipt does not contain or has the wrong subtotal.");
    }
    
    @Test
    public void testReceiptHasVAT() {
        String vat = shoppingCart.getVAT().toString();
        boolean receiptContainsVAT = receiptOutput.contains(vat);
        assertTrue(receiptContainsVAT, 
                "Receipt does not contain or has the wrong VAT.");
    }
    
    @Test
    public void testReceiptHasTotal() {
        String total = shoppingCart.getTotal().toString();
        boolean receiptContainsTotal = receiptOutput.contains(total);
        assertTrue(receiptContainsTotal, 
                "Receipt does not contain or has the wrong total.");
    }
    
    @Test
    public void testReceiptHasPaidAmount() {
        String amountPaid = sale.getAmountPaid().toString();
        boolean receiptContainsAmoundPaid = receiptOutput.contains(amountPaid);
        assertTrue(receiptContainsAmoundPaid, 
                "Receipt does not contain or has the wrong paid amount.");
    }
    
    @Test
    public void testReceiptHasChange() {
        String change = sale.getChange().toString();
        boolean receiptContainsChange = receiptOutput.contains(change);
        assertTrue(receiptContainsChange, 
                "Receipt does not contain or has the wrong change.");
    }
   
}
