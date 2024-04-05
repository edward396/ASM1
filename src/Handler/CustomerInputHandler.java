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

public class CustomerInputHandler {
    private static CustomerManager customerManager;

    public CustomerInputHandler() {
        try {
            this.customerManager = new CustomerManager("src/File/customerData.txt");
        } catch (Exception e) {
            System.out.println("Error initializing CustomerManager: " + e.getMessage());
            System.exit(1);  // Exit the program if there's an error
        }
    }

    public void addCustomer(Scanner scanner) {
        try {
            String type;
            do {
                System.out.print("Enter the customer type (PolicyHolder or Dependent): ");
                type = scanner.nextLine();

                if ("PolicyHolder".equalsIgnoreCase(type)) {
                    System.out.print("Enter the customer ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Enter the full name: ");
                    String fullName = scanner.nextLine();

                    System.out.print("Enter the insurance card: ");
                    String insuranceCard = scanner.nextLine();

                    customerManager.add(new PolicyHolder(id, fullName, insuranceCard, new ArrayList<>(), insuranceCard, new ArrayList<>()));
                    System.out.println("Customer added successfully.");

                    // Save data to file after adding the customer
                    customerManager.saveToFile("src/File/customerData.txt");
                    break;
                } else if ("Dependent".equalsIgnoreCase(type)) {
                    System.out.print("Enter the customer ID: ");
                    String id = scanner.nextLine();

                    System.out.print("Enter the full name: ");
                    String fullName = scanner.nextLine();

                    System.out.print("Enter the insurance card: ");
                    String insuranceCard = scanner.nextLine();

                    customerManager.add(new Dependent(id, fullName, insuranceCard, new ArrayList<>()));
                    System.out.println("Customer added successfully.");

                    // Save data to file after adding the customer
                    customerManager.saveToFile("src/File/customerData.txt");
                    break;
                } else {
                    System.out.println("Invalid customer type. Please try again.");
                }
            } while (true);  // do-while loop to keep asking for valid customer type
        } catch (Exception e) {
            System.out.println("Error adding customer: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    public void deleteCustomer(Scanner scanner) {
        try {
            System.out.println("Enter the customer ID to delete: ");
            String id = scanner.nextLine();

            Customer existingCustomer = customerManager.getOne(id);

            if (existingCustomer != null) {
                customerManager.delete(id);
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting customer: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    public void viewCustomer(Scanner scanner) {
        try {
            System.out.println("Enter the customer ID to view: ");
            String id = scanner.nextLine();

            Customer existingCustomer = customerManager.getOne(id);

            if (existingCustomer != null) {
                System.out.println(existingCustomer.toString());
            } else {
                System.out.println("Customer not found.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing customer: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    public void viewAllCustomers() {
        try {
            List<Customer> customers = customerManager.getAll();
            if (!customers.isEmpty()) {
                for (Customer customer : customers) {
                    System.out.println(customer.toString());
                    System.out.println("-------------------------------------------");
                }
            } else {
                System.out.println("No customers found.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing all customers: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    public void saveAndExit() {
        try {
            customerManager.saveToFile("src/File/customerData.txt");
            System.out.println("Data saved. Exiting program...");
            System.exit(0);  // Exit the program
        } catch (Exception e) {
            System.out.println("Error saving customer data: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }
}