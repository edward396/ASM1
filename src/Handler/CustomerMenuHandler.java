/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * @version JDK21
 */

package Handler;

import Classes.Customer;
import Classes.Dependant;
import Classes.PolicyHolder;
import ProcessManager.CustomerProcessManagerImplement;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Handles the menu operations related to Customer, PolicyHolder, and Dependant.
 *
 */

public class CustomerMenuHandler {
    private final CustomerProcessManagerImplement customerProcessManager;

    /**
     * Constructor for CustomerMenuHandler.
     * Initializes the customer process manager.
     */
    public CustomerMenuHandler() {
        this.customerProcessManager = new CustomerProcessManagerImplement();
    }

    /**
     * Adds a new PolicyHolder.
     *
     * @param scanner the scanner object to get user input
     */
    public void addPolicyHolder(Scanner scanner) {
        try {
            String fullName = getInput(scanner, "Enter Full Name: ");
            String insuranceCardNumber = getInput(scanner, "Enter Insurance Card: ");
            String dependants = getInput(scanner, "Enter a Dependant ID (if none, enter 'null'): ");
            List<String> dependantIds = dependants.equals("null") ? List.of() : List.of(dependants.split(", "));

            customerProcessManager.addPolicyHolder(fullName, insuranceCardNumber, dependantIds);
            System.out.println("PolicyHolder added successfully.");
        } catch (Exception e) {
            handleException("Error adding PolicyHolder: ", e);
        }
    }

    /**
     * Adds a new Dependant.
     *
     * @param scanner the scanner object to get user input
     */
    public void addDependant(Scanner scanner) {
        try {
            String fullName = getInput(scanner, "Enter Full Name: ");
            String policyHolderId = getInput(scanner, "Enter the PolicyHolder ID: ");

            customerProcessManager.addDependant(fullName, policyHolderId);
            System.out.println("Dependant added successfully.");
        } catch (Exception e) {
            handleException("Error adding Dependant: ", e);
        }
    }

    /**
     * Removes a customer.
     *
     * @param scanner the scanner object to get user input
     */
    public void removeCustomer(Scanner scanner) {
        try {
            String id = getInput(scanner, "Enter the customer ID to remove: ");

            if (!customerProcessManager.exists(id)) {
                System.out.println("Customer not found.");
                return;
            }

            customerProcessManager.remove(id);
            System.out.println("Customer removed successfully.");
        } catch (Exception e) {
            handleException("Error removing customer: ", e);
        }
    }

    /**
     * Views details of a customer.
     *
     * @param scanner the scanner object to get user input
     */
    public void viewCustomer(Scanner scanner) {
        try {
            String id = getInput(scanner, "Enter the customer ID: ");

            if (!customerProcessManager.exists(id)) {
                System.out.println("Customer not found.");
                return;
            }

            Customer existingCustomer = customerProcessManager.getOne(id);
            if (existingCustomer != null) {
                System.out.println(existingCustomer);
            } else {
                System.out.println("Customer not found.");
            }
        } catch (Exception e) {
            handleException("Error viewing customer: ", e);
        }
    }

    /**
     * Views all customers.
     */
    public void viewAllCustomers() {
        try {
            List<Customer> customers = customerProcessManager.getAll();
            if (!customers.isEmpty()) {
                customers.forEach(this::printCustomerDetails);
            } else {
                System.out.println("No customers found.");
            }
        } catch (Exception e) {
            handleException("Error viewing all customers: ", e);
        }
    }

    /**
     * Views all dependants of a PolicyHolder.
     *
     * @param scanner the scanner object to get user input
     */
    public void viewDependantsOfPolicyHolder(Scanner scanner) {
        try {
            String policyHolderId = getInput(scanner, "Enter Policy Holder ID: ");

            if (!customerProcessManager.exists(policyHolderId)) {
                System.out.println("Policy Holder not found.");
                return;
            }

            Customer policyHolder = customerProcessManager.getOne(policyHolderId);
            if (!(policyHolder instanceof PolicyHolder)) {
                System.out.println("Entered ID does not belong to a Policy Holder.");
                return;
            }

            Set<String> dependantIDs = ((PolicyHolder) policyHolder).getDependantIDs();
            if (dependantIDs.isEmpty()) {
                System.out.println("No dependants found for this Policy Holder.");
                return;
            }

            System.out.println("Dependants of Policy Holder " + policyHolder.getFullName() + ":");
            dependantIDs.stream()
                    .map(customerProcessManager::getOne)
                    .filter(customer -> customer instanceof Dependant)
                    .forEach(System.out::println);
        } catch (Exception e) {
            handleException("Error viewing dependants: ", e);
        }
    }

    /**
     * Helper method to get user input.
     *
     * @param scanner the scanner object to get user input
     * @param prompt  the prompt message for the user
     * @return        the user input
     */
    private String getInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    /**
     * Helper method to print customer details.
     *
     * @param customer the customer object to print
     */
    private void printCustomerDetails(Customer customer) {
        if (customer instanceof PolicyHolder) {
            System.out.println("PolicyHolder: " + customer);
        } else if (customer instanceof Dependant) {
            System.out.println("Dependant: " + customer);
        }
        System.out.println("-------------------------------------------");
    }

    /**
     * Helper method to handle exceptions.
     *
     * @param message the error message prefix
     * @param e       the exception
     */
    private void handleException(String message, Exception e) {
        System.out.println(message + e.getMessage());
        System.out.println("-------------------------------------------");
    }

    /**
     * Saves customer data to files and exits the program.
     */
    public void saveAndExit() {
        try {
            customerProcessManager.saveToFile(CustomerProcessManagerImplement.CUSTOMER_FILE_PATH, CustomerProcessManagerImplement.DEPENDANT_FILE_PATH);
            System.out.println("Customer data saved. Exiting program...");
            System.exit(0);  // Exit the program
        } catch (Exception e) {
            handleException("Error saving customer data: ", e);
        }
    }
}