package model;

import org.junit.Assert;
import org.junit.Test;
import java.time.LocalDateTime;

public class OrderTest {
    private Order order;

    public void defaultSet() {
        Customer customer = new Customer("A", "abc", "@.com", "123");
        LocalDateTime localDateTime = LocalDateTime.now();
        order = new Order(customer, "O1", localDateTime);
    }

    @Test
    public void addProductTest(){
        // init
        defaultSet();
        Product product = new Product("P001", "Product 1", "xd", 1, 5);

        // act
        order.addProduct(product);

        // assert
        Assert.assertEquals(1, order.getTotal(), 0.01);
    }

    @Test
    public void addNullProductTest() {
        // init
        defaultSet();
        String message = "";

        // act

        try{
            order.addProduct(null);
        } catch(NullPointerException e){
            message = e.getMessage();
        }

        // assert
        Assert.assertEquals("Cannot invoke \"model.Product.getPrice()\" because \"product\" is null", message);
    }

    @Test
    public void getTotalTest(){
        // init
        defaultSet();
        order.addProduct(new Product("P001", "Product 1", "xd1",1, 3));
        order.addProduct(new Product("P002", "Product 2", "xd2", 2, 4));

        // act
        double total = order.getTotal();

        // assert
        Assert.assertEquals(3, total, 0.01);
    }
}
