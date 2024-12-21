package controller;

import exceptions.*;
import model.*;
import structures.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class ToyStoreController {

    //Attributes

    //Relations
    private IHashTable<String, Product> products;
    private IHashTable<String, Customer> customers;
    private IHashTable<String, Order> orders;
    private IStack<String> actionHistory;
    private IQueue<Order> customerOrders;

    //Methods

    public String addCustomer(String name, String address, String email, String password) {
        String message;

        Customer customer = new Customer(name, address, email, password);

        try{
            customers.add(email, customer);
            message = "Customer added successfully!";
            actionHistory.push("customer-" + email);
        } catch (HashTableException e){
            message = "Customer already exists! Try another email.";
        }
        return message;
    }

    public String createProduct(String code, String name, String description, double price, int priority) {
        String message;

        Product product = new Product(code, name, description, price, priority);

        try{
            products.add(code, product);
            message = "Product added successfully!";
            actionHistory.push("product-" + code);
        } catch (HashTableException e){
            message = "Product already exists! Try another code.";
        }

        return message;
    }


    public String displayProducts() {
        String message = "Saved products:";
        List<Product> productList= products.getValues();

        if(productList.isEmpty()){
            message = "There are no registered products.";
        } else {
            for(Product product: productList){
                message += "\n" + product.toString();
            }
        }

        return message;
    }

    public String displayCustomers() {
        String message = "Saved customers:";
        List<Customer> customerList= customers.getValues();

        if(customerList.isEmpty()){
            message = "There are no registered customers.";
        } else {
            for(Customer customer: customerList){
                message += "\n" + customer.toString();
            }
        }

        return message;
    }


    public String deleteProduct(String code) {
        String message;
        if(!products.getValues().isEmpty()){
            try {
                Product saveProduct = products.search(code);
                products.delete(code);
                actionHistory.push("product-delete-" + saveProduct.actionFormat());
                message = "Product deleted successfully.";
            } catch (HashTableException e) {
                message = "Product not found! Try another code.";
            }
        } else {
            message = "There are no registered products to delete.";
        }


        return message;
    }


    public String updateProduct(int intOption, String code, String modification) {
        String message;

        Product product = products.search(code);

        if (product == null) {
            message = "Product was not found! Try another code.";
        } else {
            switch (intOption) {
                case 1:
                    if(products.search(modification) == null){
                        try{
                            String oldProductCode = product.getCode();
                            product.setCode(modification);
                            actionHistory.push("product-"+ oldProductCode + "-" + product.actionFormat());
                            products.delete(code);
                            products.add(modification, product);
                            message = "Product code updated successfully!";
                        } catch (HashTableException e){
                            message = "There was a problem changing that product code.";
                        }
                    } else {
                        message = "That product code is already in use. Try another one.";
                    }
                    break;
                case 2:
                    actionHistory.push("product-update-" + product.actionFormat());
                    product.setName(modification);
                    message = "Product name updated successfully.";
                    break;
                case 3:
                    actionHistory.push("product-update-" + product.actionFormat());
                    product.setDescription(modification);
                    message = "Product description updated successfully.";
                    break;
                default:
                    message = "Error: Invalid option. Please select 1 for code, 2 for name or 3 for description.";
                    break;
            }
        }

        return message;
    }


    public String updateProduct(String code, double price) {
        String message;
        Product product = products.search(code);
        actionHistory.push("product-update-" + product.actionFormat());
        if (product != null) {
            product.setPrice(price);
            message = "Product price updated successfully.";
        } else {
            message = "Product not found! Try another code.";
        }

        return message;
    }


    public String updateProduct(String code, int priority) {
        String message;
        Product product = products.search(code);
        actionHistory.push("product-priority-" + product.actionFormat());
        if (product != null) {
                product.setPriority(priority);
                List<Order> reorderOrders = orders.getValues();
                for(Order order: reorderOrders){
                    order.reHeapOrders();
                }
                message = "Product priority updated successfully!";
        } else {
            message = "Product not found! Try another code.";
        }

        return message;
    }

    public String placeOrder(String email, List<String> codes) {
        String message = "";

        Customer customer = customers.search(email);

        if(customer != null){
            if(!codes.isEmpty()){
                Random random = new Random();
                int intOrderNumber = random.nextInt(100);
                String orderNumber = email + intOrderNumber;

                LocalDateTime currentDate = LocalDateTime.now();

                Order order = new Order(customer, orderNumber, currentDate);

                for (String code : codes) {
                    Product product = products.search(code);
                    if (product != null) {
                        order.addProduct(product);
                    }
                }

                try{
                    orders.add(orderNumber, order);
                    actionHistory.push("order-" + orderNumber);
                    organizeCustomerOrders(order);
                    message = "The order was placed successfully!";
                } catch (HashTableException e){
                    message = "There was a problem adding the order. Try again later.";
                }
            } else {
                message = "Not one product was entered! Please enter at least one.";
            }
        } else {
            message = "The entered email is not registered. Try another one or sign up!";
        }

        return message;
    }

    private void organizeCustomerOrders(Order newOrder) {
        Queue<Order> tempQueue = new Queue<>();
        boolean customerFound = false;

        while (!customerOrders.isEmpty()) {
            try {
                Order currentOrder = customerOrders.front();

                if (currentOrder.getCostumer().equals(newOrder.getCostumer())) {
                    customerFound = true;
                }

                currentOrder = customerOrders.dequeue();

                tempQueue.enqueue(currentOrder);

                if (customerFound && !customerOrders.isEmpty() &&
                        !customerOrders.front().getCostumer().equals(newOrder.getCostumer())) {
                    tempQueue.enqueue(newOrder);
                    customerFound = false;
                }

            } catch (QueueException e) {
                // Shouldn't throw the front exception since it stops before empty
            }
        }

        if (customerFound) {
            tempQueue.enqueue(newOrder);
        }

        if(customerOrders.isEmpty()){
            tempQueue.enqueue(newOrder);
        }

        customerOrders = tempQueue;
    }

    public String undoAction(){
        String message;

        if(!actionHistory.isEmpty()){
            try{
                String lastAction = actionHistory.top();
                actionHistory.pop();

                String[] lastActionSplit = lastAction.split("-");

                if(lastActionSplit[0].equals("customer")){
                    customers.delete(lastActionSplit[1]);
                    message = "The last created customer was deleted.";

                } else if(lastActionSplit[0].equals("product")){
                    if(lastActionSplit.length == 2){
                        products.delete(lastActionSplit[1]);
                        message = "The last created product was deleted.\nEven though discontinued, the orders with this product will still be dispatched.";
                    } else if(lastActionSplit.length == 7){
                        if(!lastActionSplit[1].equals("delete")){
                            products.delete(lastActionSplit[2]);
                        }

                        String code = lastActionSplit[1].equals("update") ? lastActionSplit[2] : lastActionSplit[1];
                        String name= lastActionSplit[3];
                        String description = lastActionSplit[4];
                        double price = Double.parseDouble(lastActionSplit[5].replace(",","."));
                        int priority = Integer.parseInt(lastActionSplit[6]);
                        Product product = new Product(code, name, description, price, priority);
                        products.add(code, product);

                        if(lastActionSplit[1].equals("priority")){
                            List<Order> reorderOrders = orders.getValues();
                            for(Order order: reorderOrders){
                                order.reHeapOrders();
                            }
                        }

                        message = "The last updated product was restored to it's original values.";
                    } else {
                        message = "Unexpected error product action split length.";
                    }
                } else if(lastActionSplit[0].equals("order")){
                    orders.delete(lastActionSplit[1]);
                    customerOrders.dequeue();
                    message = "The last added order has been dequeued.";
                } else {
                    message = "Unexpected error customer/product/order.";
                }

            } catch (StackException | HashTableException | QueueException e){
                message = e.getMessage();
            }
        } else {
            message = "No actions are left to undo.";
        }

        return message;
    }

    public String manageShipments(){
        String message;

        if(customerOrders.isEmpty()){
            message = "There are no registered orders.";
        } else {
            try{
                if(customerOrders.front().isEmpty()){
                    customerOrders.dequeue();
                }
                Order nextOrder = customerOrders.front();
                Product product = nextOrder.removeProduct();
                message = "For order " + nextOrder.getOrderNumber() + " the product " + product.toString() + " must be dispatched.";
            } catch (QueueException | PriorityQueueException e){
                message = e.getMessage();
            }
        }

        return message;
    }


    //CONSTRUCTOR
    public ToyStoreController() {
        this.products = new HashTable<>();
        this.customers = new HashTable<>();
        this.actionHistory = new Stack<>();
        this.customerOrders = new Queue<>();
        this.customers = new HashTable<>();
        this.orders = new HashTable<>();
    }

    //GETTERS AND SETTERS

    public IHashTable<String, Product> getProducts() {
        return products;
    }

    public IHashTable<String, Customer> getCustomers() {
        return customers;
    }

    public IHashTable<String, Order> getOrders() {
        return orders;
    }

    public IStack<String> getActionHistory() {
        return actionHistory;
    }

    public IQueue<Order> getCustomerOrders() {
        return customerOrders;
    }
}
