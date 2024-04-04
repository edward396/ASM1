package Handler;

/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

import CustomerManager.CustomerManager;
import Classes.Customer;
import Classes.Dependent;
import Classes.PolicyHolder;

import java.util.*;

public class ManagerInputHandler {
    private final CustomerManager customerManager;

    public ManagerInputHandler() {
        try {
            this.customerManager = new CustomerManager("src/File/customerData.txt");
        } catch (Exception e) {
            throw new RuntimeException("Error initializing CustomerManager", e);
        }
    }

    public void addCustomer(Scanner scanner) {
        String type;
        do {
            System.out.println("Enter the customer type (PolicyHolder or Dependent): ");
            type = scanner.nextLine();

            if ("PolicyHolder".equalsIgnoreCase(type)) {
                System.out.println("Enter the customer ID: ");
                String id = scanner.nextLine();

                System.out.println("Enter the full name: ");
                String fullName = scanner.nextLine();

                System.out.println("Enter the insurance card: ");
                String insuranceCard = scanner.nextLine();

                customerManager.add(new PolicyHolder(id, fullName, insuranceCard, new ArrayList<>(), insuranceCard, new ArrayList<>()));
                break;
            } else if ("Dependent".equalsIgnoreCase(type)) {
                System.out.println("Enter the customer ID: ");
                String id = scanner.nextLine();

                System.out.println("Enter the full name: ");
                String fullName = scanner.nextLine();

                System.out.println("Enter the insurance card: ");
                String insuranceCard = scanner.nextLine();

                customerManager.add(new Dependent(id, fullName, insuranceCard, new ArrayList<>()));
                break;
            } else {
                System.out.println("Invalid customer type. Please try again.");
            }
        } while (true);  // do-while loop to keep asking for valid customer type
    }

    public void deleteCustomer(Scanner scanner) {
        System.out.println("Enter the customer ID to delete: ");
        String id = scanner.nextLine();
        customerManager.delete(id);
    }

    public void viewCustomer(Scanner scanner) {
        System.out.println("Enter the customer ID to view: ");
        String id = scanner.nextLine();
        Customer customer = customerManager.getOne(id);
        if (customer != null) {
            System.out.println(customer.toString());
        } else {
            System.out.println("Customer not found.");
        }
    }

    public void viewAllCustomers() {
        List<Customer> customers = customerManager.getAll();
        for (Customer customer : customers) {
            System.out.println(customer.toString());
            System.out.println("-------------------------------------------");
        }
    }

    public void saveAndExit() {
        try {
            customerManager.saveToFile();  // Saving to the correct file path
            System.out.println("Data saved. Exiting program...");
            System.exit(0);  // Exit the program
        } catch (Exception e) {
            System.out.println("Error saving customer data: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }
}