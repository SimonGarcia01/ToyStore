package model;

public class Product implements Comparable<Product> {

    private String code;
    private String name;
    private String description;
    private double price;
    private int priority;

    public Product(String code, String name, String description, double price, int priority) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.price = price;
        this.priority = priority;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Product o) {
        return Integer.compare(this.priority, o.priority);
    }

    @Override
    public String toString() {
        return String.format("Code: %s - Name: %s", code, name);
    }

    public String actionFormat(){
        return String.format("%s-%s-%s-%f-%d", code, name, description, price, priority);
    }
}


