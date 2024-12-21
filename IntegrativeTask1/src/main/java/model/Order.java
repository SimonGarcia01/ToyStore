package model;

import java.time.LocalDateTime;

import exceptions.PriorityQueueException;
import structures.IPriorityQueue;
import structures.PriorityQueue;

public class Order {
    private IPriorityQueue <Product> orderedProducts;
    private Customer customer;
    private String orderNumber;
    private LocalDateTime date;
    private double total;

    public Order(Customer customer, String orderNumber, LocalDateTime date) {
        this.orderedProducts = new PriorityQueue<>();
        this.customer = customer;
        this.orderNumber = orderNumber;
        this.date = date;
    }

    public void reHeapOrders(){
        IPriorityQueue<Product> reHeap = new PriorityQueue<>();

        try{
            while(!orderedProducts.isEmpty()){
                reHeap.enqueue(orderedProducts.dequeue());
            }
            orderedProducts = reHeap;
        } catch (PriorityQueueException e) {

        }
    }

    public boolean isEmpty(){
        return orderedProducts.isEmpty();
    }

    public void addProduct(Product product) {
        orderedProducts.enqueue(product);
        total += product.getPrice();
    }

    public Product removeProduct() throws PriorityQueueException {
        Product product = orderedProducts.dequeue();
        total -= product.getPrice();
        return product;
    }

    public IPriorityQueue<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public  void setCostumer(Customer costumer){
        this.customer = costumer;
    }

    public Customer getCostumer(){
        return customer;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

}
