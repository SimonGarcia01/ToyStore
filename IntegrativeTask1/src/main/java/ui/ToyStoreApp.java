package ui;

import controller.ToyStoreController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ToyStoreApp {

    //Attributes
    private Scanner sc;
    private ToyStoreController controller;

    //Main Executable
    public static void main(String[] args) {
        ToyStoreApp main = new ToyStoreApp();

    System.out.println("Welcome to the online store");
        int option=0;
        do{
            option=main.showMenu();
            main.executeConsultOperation(option);
        }while (option!=6);


    }
    //Methods
    public int showMenu() {
        System.out.println("Enter which option you would like to do");
        System.out.println("1.Register customers"
        + "\n2.Product catalog"
        + "\n3.Undo-action"
        + "\n4.Place an order"
        + "\n5.Order management"
        + "\n6.Exit");
        int option=sc.nextInt();
        sc.nextLine();
        return option;
    }
    public void executeConsultOperation(int option) {
        switch (option) {
            case 1:
                addCustomer();
                break;
            case 2:
                showListProducts();
                break;
            case 3:
                undoAction();
                break;
            case 4:
                addOrder();
                break;
            case 5:
                orderManagement();
                break;
            case 6:
                System.out.println("Exiting program");
                break;
            default:
                System.out.println("Please enter a valid option");
            break;
        }
    }
    public void addCustomer() {
        System.out.println("Enter the name of the costumer");
        String costumer=sc.nextLine();
        System.out.println("Enter his address");
        String address=sc.nextLine();
        System.out.println("Enter his email");
        String email=sc.nextLine();
        System.out.println("Enter his password");
        String password=sc.nextLine();
        String message=controller.addCustomer(costumer, address, email, password);
        System.out.println(message);

    }
    public void showListProducts() {
        int option=0;
        do{
            System.out.println("Enter any of the following actions related to the product list");
            System.out.println("1.Create product" +
                    "\n2.Delete product" +
                    "\n3.Update product" +
                    "\n4 Exit product catalog");
            option=sc.nextInt();
            sc.nextLine();
            decisionProduct(option);
        }while(option!=4);

    }
    public void decisionProduct(int option) {
        switch (option) {
            case 1:
                addProduct();
                break;
            case 2:
                deleteProduct();
                break;
            case 3:
                updateProduct();
                break;
            case 4:
                System.out.println(":)");
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }
    public void addProduct() {
        System.out.println("Enter the code");
        String code=sc.nextLine();
        System.out.println("Enter the name");
        String name=sc.nextLine();
        System.out.println("Enter the description");
        String description=sc.nextLine();
        System.out.println("Enter the price");
        double price=sc.nextDouble();
        sc.nextLine();
        System.out.println("Enter the priority");
        int priority=sc.nextInt();
        sc.nextLine();
        String message=controller.createProduct(code,name,description,price,priority);
        System.out.println(message);
    }
    public void deleteProduct() {
        System.out.println("Enter the code");
        String code=sc.nextLine();
        String message=controller.deleteProduct(code);
        System.out.println(message);
    }
    public void updateProduct() {
        System.out.println("Enter the code of the product which you want to update");
        String code=sc.nextLine();
        System.out.println("Enter which product attribute you want to modify");
        System.out.println("1.Update code product");
        System.out.println("2.Update name product");
        System.out.println("3.Update description product");
        System.out.println("4.Update price");
        System.out.println("5.Update priority product");
        int option=sc.nextInt();
        sc.nextLine();
        switch (option) {
            case 1:
                System.out.println("Enter the new code");
                String newCode=sc.nextLine();
                String message=controller.updateProduct(option,code,newCode);
                System.out.println(message);
                break;
            case 2:
                System.out.println("Enter the new name");
                String newName=sc.nextLine();
                message=controller.updateProduct(option,code,newName);
                System.out.println(message);
                break;
            case 3:
                System.out.println("Enter the new description");
                String newDescription=sc.nextLine();
                message=controller.updateProduct(option,code,newDescription);
                System.out.println(message);
                break;
            case 4:
                System.out.println("Enter the new price");
                double newPrice=sc.nextDouble();
                sc.nextLine();
                message= controller.updateProduct(code,newPrice);
                System.out.println(message);
                break;
            case 5:
                System.out.println("Enter the new priority");
                int newPriority=sc.nextInt();
                sc.nextLine();
                message= controller.updateProduct(code,newPriority);
                System.out.println(message);
                break;
            default:
                System.out.println("Invalid option");
                break;
        }
    }


    public void undoAction() {
        String message= controller.undoAction();
        System.out.println(message);
    }
    public void addOrder() {
        System.out.println("Enter the email whom will be related the customer to his order");
        System.out.println(controller.displayCustomers());
        String email= sc.nextLine();
        System.out.println("Enter the product you want to order");
        System.out.println(controller.displayProducts());
        int i=0;
        List<String> orders=new ArrayList<>();
        do{
            String product= sc.nextLine();
            orders.add(product);
            System.out.println("Do you want to continue adding products? : 1=Yes/0=No");
            i= sc.nextInt();
            sc.nextLine();
        }while(i!=0);
        String message=controller.placeOrder(email,orders);
        System.out.println(message);
    }
    public void orderManagement() {
        String message=controller.manageShipments();
        System.out.println(message);
    }

    //Constructor
    public ToyStoreApp() {
        this.sc = new Scanner(System.in);
        this.controller = new ToyStoreController();

    }
}
