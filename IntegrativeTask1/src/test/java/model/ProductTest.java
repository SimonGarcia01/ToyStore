package model;

import exceptions.*;
import org.junit.Assert;
import org.junit.Test;
import model.Product;

public class ProductTest {
    private Product product;

    public void defaultSet() {
        // init
        product = new Product("P001", "Sample Product", "xd1", 1, 3);
    }

    @Test
    public void getCodeTest() {
        // init
        defaultSet();
        // act
        String code = product.getCode();
        // assert
        Assert.assertEquals("P001", code);
    }

    @Test
    public void getNameTest() {
        // init
        defaultSet();
        // act
        String name = product.getName();
        // assert
        Assert.assertEquals("Sample Product", name);
    }

    @Test
    public void getPriceTest() {
        // init
        defaultSet();
        // act
        double price = product.getPrice();
        // assert
        Assert.assertEquals(1, price, 0.01);
    }

    @Test
    public void setCodeTest() {
        // init
        defaultSet();
        // act
        product.setCode("P002");
        // assert
        Assert.assertEquals("P002", product.getCode());
    }

    @Test
    public void setNameTest() {
        // init
        defaultSet();
        // act
        product.setName("Updated Product");
        // assert
        Assert.assertEquals("Updated Product", product.getName());
    }

    @Test
    public void setPriceTest() {
        // init
        defaultSet();
        // act
        product.setPrice(15.0);
        // assert
        Assert.assertEquals(15.0, product.getPrice(), 0.01);
    }

    @Test
    public void compareToTest() {
        // init
        defaultSet();
        Product otherProduct = new Product("P002", "Other Product", "xd3", 2, 3);
        // act
        int comparisonResult = product.compareTo(otherProduct);
        // assert
        Assert.assertTrue(comparisonResult >= 0);
    }
}
