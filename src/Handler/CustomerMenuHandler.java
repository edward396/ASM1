/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package Handler;

import Classes.Customer;
import Classes.Dependent;
import Classes.PolicyHolder;
import ProcessManager.CustomerProcessManagerImplement;

import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class CustomerMenuHandler {
    private final CustomerProcessManagerImplement customerProcessManager;

    public CustomerMenuHandler() {
        this.customerProcessManager = new CustomerProcessManagerImplement();
    }

    public void addPolicyHolder(Scanner scanner) {
        try {
            String fullName = getInput(scanner, "Enter Full Name: ");
            String insuranceCardNumber = getInput(scanner, "Enter Insurance Card: ");
            String dependents = getInput(scanner, "Enter dependent IDs separated by commas (if none, enter 'null'): ");
            List<String> dependentIds = dependents.equals("null") ? List.of() : List.of(dependents.split(", "));

            customerProcessManager.addPolicyHolder(fullName, insuranceCardNumber, dependentIds);
            System.out.println("PolicyHolder added successfully.");
        } catch (Exception e) {
            handleException("Error adding PolicyHolder: ", e);
        }
    }

    public void addDependent(Scanner scanner) {
        try {
            String fullName = getInput(scanner, "Enter Full Name: ");
            String policyHolderId = getInput(scanner, "Enter the PolicyHolder ID: ");

            customerProcessManager.addDependent(fullName, policyHolderId);
            System.out.println("Dependent added successfully.");
        } catch (Exception e) {
            handleException("Error adding Dependent: ", e);
        }
    }

    public void deleteCustomer(Scanner scanner) {
        try {
            String id = getInput(scanner, "Enter the customer ID to delete: ");

            if (!customerProcessManager.exists(id)) {
                System.out.println("Customer not found.");
                return;
            }

            customerProcessManager.delete(id);
            System.out.println("Customer deleted successfully.");
        } catch (Exception e) {
            handleException("Error deleting customer: ", e);
        }
    }

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

    public void updateCustomer(Scanner scanner) {
        try {
            String id = getInput(scanner, "Enter the customer ID to update: ");

            if (!customerProcessManager.exists(id)) {
                System.out.println("Customer not found.");
                return;
            }

            Customer existingCustomer = customerProcessManager.getOne(id);

            if (existingCustomer instanceof PolicyHolder) {
                String fullName = getInput(scanner, "Enter Full Name: ");
                String insuranceCardNumber = getInput(scanner, "Enter Insurance Card: ");
                customerProcessManager.updatePolicyHolder(id, fullName, insuranceCardNumber);
                System.out.println("PolicyHolder updated successfully.");
            } else if (existingCustomer instanceof Dependent) {
                String fullName = getInput(scanner, "Enter Full Name: ");
                String policyHolderId = getInput(scanner, "Enter the PolicyHolder ID: ");
                customerProcessManager.updateDependent(id, fullName, policyHolderId);
                System.out.println("Dependent updated successfully.");
            }

        } catch (Exception e) {
            handleException("Error updating customer: ", e);
        }
    }

    public void viewDependentsOfPolicyHolder(Scanner scanner) {
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

            Set<String> dependentIDs = ((PolicyHolder) policyHolder).getDependentIDs();
            if (dependentIDs.isEmpty()) {
                System.out.println("No dependents found for this Policy Holder.");
                return;
            }

            System.out.println("Dependents of Policy Holder " + policyHolder.getFullName() + ":");
            dependentIDs.stream()
                    .map(customerProcessManager::getOne)
                    .filter(customer -> customer instanceof Dependent)
                    .forEach(System.out::println);
        } catch (Exception e) {
            handleException("Error viewing dependents: ", e);
        }
    }

    public boolean exists(String customerID) {
        return customerProcessManager.exists(customerID);
    }

    public String getCustomerName(String customerID) {
        Customer customer = customerProcessManager.getOne(customerID);
        return customer != null ? customer.getFullName() : null;
    }

    public void saveAndExit() {
        try {
            customerProcessManager.saveToFile(CustomerProcessManagerImplement.CUSTOMER_FILE_PATH, CustomerProcessManagerImplement.DEPENDENT_FILE_PATH);
            System.out.println("Customer data saved. Exiting program...");
            System.exit(0);  // Exit the program
        } catch (Exception e) {
            handleException("Error saving customer data: ", e);
        }
    }

    private String getInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private void printCustomerDetails(Customer customer) {
        if (customer instanceof PolicyHolder) {
            System.out.println("PolicyHolder: " + customer);
        } else if (customer instanceof Dependent) {
            System.out.println("Dependent: " + customer);
        }
        System.out.println("-------------------------------------------");
    }

    private void handleException(String message, Exception e) {
        System.out.println(message + e.getMessage());
        System.out.println("-------------------------------------------");
    }
}