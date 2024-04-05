package Handler;

/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

import ProcessManager.CustomerProcessManagerImplement;
import Classes.Customer;
import Classes.Dependent;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class CustomerInputHandler {
    private final CustomerProcessManagerImplement customerProcessManager;

    public CustomerInputHandler() {
        this.customerProcessManager = new CustomerProcessManagerImplement();
        try {
            customerProcessManager.loadFromFile("src/File/customerData.txt");
        } catch (Exception e) {
            System.out.println("Error initializing CustomerManager: " + e.getMessage());
            System.exit(1);  // Exit the program if there's an error
        }
    }

    public void addPolicyHolder(Scanner scanner) {
        try {
            System.out.print("Enter Customer ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Enter Insurance Card: ");
            String insuranceCard = scanner.nextLine();

            this.customerProcessManager.addPolicyHolder(id, fullName, insuranceCard);
            System.out.println("PolicyHolder added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding PolicyHolder: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    public void addDependent(Scanner scanner) {
        try {
            System.out.print("Enter Customer ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter Full Name: ");
            String fullName = scanner.nextLine();

            System.out.print("Enter Insurance Card: ");
            String insuranceCard = scanner.nextLine();

            System.out.print("Enter the PolicyHolder ID: ");
            String policyHolderId = scanner.nextLine();

            customerProcessManager.addDependent(id, fullName, insuranceCard, policyHolderId);
            System.out.println("Dependent added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding Dependent: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    public void deleteCustomer(Scanner scanner) {
        try {
            System.out.println("Enter the customer ID to delete: ");
            String id = scanner.nextLine();

            Customer existingCustomer = customerProcessManager.getOne(id);

            if (existingCustomer != null) {
                customerProcessManager.delete(id);
                System.out.println("Customer deleted successfully.");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting customer: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    public void viewCustomer(String id) {
        try {
            Customer existingCustomer = customerProcessManager.getOne(id);

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
            List<Customer> customers = customerProcessManager.getAll();
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
            customerProcessManager.saveToFile("src/File/customerData.txt");
            System.out.println("Customer data saved. Exiting program...");
            System.exit(0);  // Exit the program
        } catch (Exception e) {
            System.out.println("Error saving customer data: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }
}
