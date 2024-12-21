package controller;

import exceptions.*;
import model.Order;
import model.Product;
import model.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import structures.*;

import java.util.ArrayList;
import java.util.List;

public class ToyStoreControllerTest {

    private ToyStoreController controller;

    //Setups
    public void defaultSetUp() {
        controller = new ToyStoreController();
    }

    public void oneCustomerSetUp() {
        controller = new ToyStoreController();
        controller.addCustomer("John Doe", "123 Elm Street", "johndoe@example.com", "password123");
    }

    public void oneProductSetUp() {
        controller = new ToyStoreController();
        controller.createProduct("AA00", "Soap", "Bar of Soap", 10, 1);
    }

    public void customerProductSetUp() {
        controller = new ToyStoreController();
        controller.addCustomer("John Doe", "123 Elm Street", "johndoe@example.com", "password123");
        controller.addCustomer("Alex Terry", "20 North Street", "alexterry@gmail.com", "PassWord321");
        controller.addCustomer("Walter White", "308 Negra Arroyo Lane", "heisenberg@hotmail.com", "wordpass111");
        controller.createProduct("AA00", "Soap", "Bar of Soap", 10.0, 1);
        controller.createProduct("BB11", "Car", "Toy Car", 15.0, 2);
        controller.createProduct("CC22", "Barbie", "Barbie Doll", 20.0, 3);
    }

    public void orderCustumerProductSetUp(){
        controller = new ToyStoreController();
        controller.addCustomer("John Doe", "123 Elm Street", "johndoe@example.com", "password123");
        controller.addCustomer("Alex Terry", "20 North Street", "alexterry@gmail.com", "PassWord321");
        controller.createProduct("AA00", "Soap", "Bar of Soap", 10.0, 1);
        controller.createProduct("BB11", "Car", "Toy Car", 15.0, 2);
        controller.createProduct("CC22", "Barbie", "Barbie Doll", 20.0, 3);

        List<String> code = new ArrayList<>();
        code.add("AA00");

        controller.placeOrder("johndoe@example.com", code);

        List<String> code2 = new ArrayList<>();
        code2.add("BB11");
        code2.add("CC22");

        controller.placeOrder("alexterry@gmail.com", code2);
    }

    //ADD CUSTOMER TEST
    @Test
    public void addCustomerSuccessTest() {
        // init
        defaultSetUp();

        // act
        controller.addCustomer("John Doe", "123 Elm Street", "johndoe@example.com", "password123");
        String result = controller.displayCustomers();
        // assert
        Assert.assertEquals("Saved customers:\n" +
                "Email: johndoe@example.com - Name: John Doe", result);
    }

    @Test
    public void addEmptyCustomer() {
        // init
        defaultSetUp();

        // act
        controller.addCustomer("", "", "", "");
        String result = controller.displayCustomers();


        // assert
        Assert.assertEquals("Saved customers:\nEmail:  - Name: ", result);
    }

    @Test
    public void addExistingCustomerTest() {
        // init
        oneCustomerSetUp();

        // act
        String result = controller.addCustomer("Jane Doe", "456 Oak Avenue", "johndoe@example.com", "anotherpassword");

        // assert
        Assert.assertEquals("Customer already exists! Try another email.", result);
    }

    //CREATE PRODUCT TEST
    @Test
    public void createProductSuccessTest() {
        // init
        defaultSetUp();
        String code = "P001";
        String name = "Toy Car";
        String description = "A small toy car";
        double price = 19.99;
        int priority = 1;

        // act
        controller.createProduct(code, name, description, price, priority);

        String result = controller.displayProducts();

        // assert
        Assert.assertEquals("Saved products:\nCode: P001 - Name: Toy Car", result);
    }

    @Test
    public void createProductExistingCodeTest() {
        // init
        oneProductSetUp();

        // act
        String result = controller.createProduct("AA00", "Toy Car", "A small toy car", 19.99, 1);

        // assert
        Assert.assertEquals("Product already exists! Try another code.", result);
    }

    @Test
    public void createEmptyProductTest() {
        // init
        defaultSetUp();
        String code = "";
        String name = "";
        String description = "";
        double price = 0;
        int priority = 0;

        // act
        controller.createProduct(code, name, description, price, priority);

        String result = controller.displayProducts();

        // assert
        Assert.assertEquals("Saved products:\nCode:  - Name: ", result);
    }

    //DELETE PRODUCT TEST
    @Test
    public void deleteProductSuccessTest() {
        // init
        oneProductSetUp();

        // act
        controller.deleteProduct("AA00");
        String result = controller.displayProducts();

        // assert
        Assert.assertEquals("There are no registered products.", result);
    }

    @Test
    public void deleteProductNonExistingTest() {
        // init
        oneProductSetUp();

        // act
        String result = controller.deleteProduct("0000");

        // assert
        Assert.assertEquals("Product not found! Try another code.", result);
    }

    @Test
    public void deleteProductEmptyCodeTest() {
        // init
        defaultSetUp();

        // act
        String result = controller.deleteProduct("");

        // assert
        Assert.assertEquals("There are no registered products to delete.", result);
    }

    // UPDATE PRODUCT CODE TEST
    @Test
    public void updateProductNameSuccessTest() {
        // init
        oneProductSetUp();

        // act
        controller.updateProduct(1, "AA00", "AAAA");
        String result = controller.displayProducts();

        // assert
        Assert.assertEquals("Saved products:\n" +
                "Code: AAAA - Name: Soap", result);
    }

    @Test
    public void updateProductNonExistingCodeTest() {
        //init
        oneProductSetUp();

        // act
        String result = controller.updateProduct(1, "P999", "0000");

        // assert
        Assert.assertEquals("Product was not found! Try another code.", result);
    }

    @Test
    public void updateProductPreexistingCodeTest() {
        // init
        customerProductSetUp();

        // act
        String result = controller.updateProduct(1, "AA00", "BB11");  // Empty code

        // assert
        Assert.assertEquals("That product code is already in use. Try another one.", result);
    }

    // UPDATE PRICE UPDATE TEST
    @Test
    public void updateProductPriceSuccessTest() {
        // init
        oneProductSetUp();

        // act
        controller.updateProduct("AA00", 100.0);
        double result = controller.getProducts().getValues().get(0).getPrice();

        //assert
        Assert.assertEquals(100.0, result, 0.1);
    }

    @Test
    public void updateProductNegativePriceTest() {
        //init
        oneProductSetUp();

        // act
        controller.updateProduct("AA00", -100.0);
        double result = controller.getProducts().getValues().get(0).getPrice();

        //assert
        Assert.assertEquals(-100.0, result, 0.1);
    }

    @Test
    public void updateProduct0PriceTest() {
        // init
        customerProductSetUp();

        // act
        controller.updateProduct("AA00", 0.0);
        double result = controller.getProducts().getValues().get(0).getPrice();

        //assert
        Assert.assertEquals(0.0, result, 0.1);
    }

    //TEST UPDATE PRODUCT PRIORITY
    @Test
    public void updateProductPrioritySuccessTest() {
        // init
        oneProductSetUp();

        // act
        controller.updateProduct("AA00", 5);

        int result = controller.getProducts().getValues().get(0).getPriority();

        //assert
        Assert.assertEquals(5, result);
    }

    @Test
    public void updateProductNegativePriorityTest() {
        //init
        oneProductSetUp();

        // act
        controller.updateProduct("AA00", -1);
        int result = controller.getProducts().getValues().get(0).getPriority();

        //assert
        Assert.assertEquals(-1, result);
    }

    @Test
    public void updateProductPriorityChangeOrderTest() {
        // init
        orderCustumerProductSetUp();

        // act
        controller.updateProduct("BB11", 5);

        String result = "";

        try{
            result = controller.getOrders().getValues().get(0).getOrderedProducts().front().getCode();

        } catch (PriorityQueueException e){
            result = e.getMessage();
        }

        Assert.assertEquals("BB11", result);
    }

    //TEST PLACE ORDER
    @Test
    public void placeOrderSuccessTest(){
        // init
        defaultSetUp();
        controller.addCustomer("John Doe", "123 Elm Street", "johndoe@example.com", "password123");
        controller.createProduct("P001", "Toy Car", "A small toy car", 19.99, 1);
        List<String> productCodes = new ArrayList<>();
        productCodes.add("P001");

        // act
        controller.placeOrder("johndoe@example.com", productCodes);
        Boolean result = controller.getOrders().getValues().isEmpty();

        // assert
        Assert.assertFalse(result);
    }

    @Test
    public void placeOrderCustomerNotFoundTest(){
        // init
        defaultSetUp();
        List<String> productCodes = new ArrayList<>();
        productCodes.add("P001");

        // act
        String result = controller.placeOrder("nonexistentemail@example.com", productCodes);

        // assert
        Assert.assertEquals("The entered email is not registered. Try another one or sign up!", result);
    }

    @Test
    public void placeOrderEmptyProductCodesTest(){
        // init
        defaultSetUp();
        controller.addCustomer("John Doe", "123 Elm Street", "johndoe@example.com", "password123");
        List<String> productCodes = new ArrayList<>();

        // act
        String result = controller.placeOrder("johndoe@example.com", productCodes);

        // assert
        Assert.assertEquals("Not one product was entered! Please enter at least one.", result);
    }

    //TEST UNDO ACTION
    @Test
    public void undoEmptyActionHistory(){
        //init
        defaultSetUp();

        //act
        String result = controller.undoAction();

        //assert
        Assert.assertEquals("No actions are left to undo.", result);
    }


    @Test
    public void undoChangeProductTest(){
        //init
        oneCustomerSetUp();

        //act
        controller.undoAction();
        String result = controller.displayCustomers();

        //assert
        Assert.assertEquals("There are no registered customers.", result);
    }

    @Test
    public void undoChangeProductName(){
        //init
        oneProductSetUp();

        //act
        controller.updateProduct(1, "AA00", "BB11");
        controller.undoAction();
        String result = controller.displayProducts();

        //assert
        Assert.assertEquals("Saved products:\n" +
                "Code: AA00 - Name: Soap", result);
    }

    //TEST MANAGE SHIPMENTS
    @Test
    public void manageEmptyOrder(){
        //init
        customerProductSetUp();

        //act
        String result = controller.manageShipments();

        //assert
        Assert.assertEquals("There are no registered orders.", result);
    }

    @Test
    public void manageOneOrder(){
        //init
        customerProductSetUp();

        //act

        List<String> code = new ArrayList<>();
        code.add("AA00");

        controller.placeOrder("johndoe@example.com", code);

        List<String> code2 = new ArrayList<>();
        code2.add("AA00");
        code2.add("BB11");

        controller.placeOrder("alexterry@gmail.com", code2);



        //assert
        System.out.println(controller.manageShipments());
        System.out.println(controller.manageShipments());
        System.out.println(controller.manageShipments());
        System.out.println(controller.manageShipments());
    }
}
