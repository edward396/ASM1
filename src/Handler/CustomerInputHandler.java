package Handler;

/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

import Classes.Claim;
import CustomerManager.CustomerManager;
import Classes.Customer;

import java.util.*;

public class CustomerInputHandler {
    private final CustomerManager customerManager;

    public CustomerInputHandler() {
        this.customerManager = new CustomerManager();
        try {
            customerManager.loadFromFile("src/File/customerData.txt");
        } catch (Exception e) {
            System.out.println("Error initializing CustomerManager: " + e.getMessage());
            System.exit(1);  // Exit the program if there's an error
        }
    }

    public void addPolicyHolder(Scanner scanner) {
        try {
            System.out.print("Enter the Policy Holder ID: ");
            String id = InputValidator.getCustomerID(scanner);

            System.out.print("Enter the full name: ");
            String fullName = scanner.nextLine();

            System.out.print("Enter the insurance card: ");
            String insuranceCard = scanner.nextLine();

            int numClaims;
            do {
                System.out.print("Enter the number of claims (max 3): ");
                while (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid integer.");
                    scanner.next(); // Consume the invalid input
                }
                numClaims = scanner.nextInt();
                scanner.nextLine();  // Consume the newline

                if (numClaims < 0 || numClaims > 3) {
                    System.out.println("Invalid number of claims. Please enter again.");
                }
            } while (numClaims < 0 || numClaims > 3);

            List<Claim> claims = new ArrayList<>();
            for (int i = 0; i < numClaims; i++) {
                System.out.println("Enter details for Claim " + (i + 1) + ":");
                Claim claim = addClaimDetails(scanner);
                claims.add(claim);
            }

            customerManager.addPolicyHolder(id, fullName, insuranceCard, claims);
            System.out.println("PolicyHolder added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding PolicyHolder: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    public void addDependent(Scanner scanner) {
        try {
            System.out.print("Enter the Dependent ID: ");
            String id = InputValidator.getCustomerID(scanner);

            System.out.print("Enter the full name: ");
            String fullName = scanner.nextLine();

            System.out.print("Enter the insurance card: ");
            String insuranceCard = scanner.nextLine();

            System.out.print("Enter the PolicyHolder ID: ");
            String policyHolderId = scanner.nextLine();

            customerManager.addDependent(id, fullName, insuranceCard, policyHolderId);
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

    public void viewCustomer(String id) {
        try {
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
        } catch (Exception e) { //must be specific exception
            System.out.println("Error viewing all customers: " + e); //trace back the problem
            System.out.println("-------------------------------------------");
        }
    }

    public void saveAndExit() {
        try {
            customerManager.saveToFile("src/File/customerData.txt");
            System.out.println("Customer data saved. Exiting program...");
            System.exit(0);  // Exit the program
        } catch (Exception e) {
            System.out.println("Error saving customer data: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    private Claim addClaimDetails(Scanner scanner) {
        String claimID = InputValidator.getFormattedClaimID(scanner);
        Date claimDate = InputValidator.getDateInput(scanner, "Enter Claim Date (dd-MM-yyyy): ");
        String insuredPerson = InputValidator.getStringInput(scanner, "Enter Insured Person: ");
        String cardNumber = InputValidator.getStringInput(scanner, "Enter Card Number: ");
        Date examDate = InputValidator.getDateInput(scanner, "Enter Exam Date (dd-MM-yyyy): ");
        String[] documentArray = InputValidator.getStringInput(scanner, "Enter Document Names (claimId_cardNumber_documentName.pdf): ").split("_");
        List<String> documents = Arrays.asList(documentArray);
        double amount = InputValidator.getDoubleInput(scanner, "Enter Claim Amount: ");
        String status = InputValidator.getClaimStatus(scanner);
        String bankName = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Bank Name): ");
        String accountOwner = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Account Owner): ");
        String accountNumber = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Account Number): ");

        return new Claim.Builder()
                .claimID(claimID)
                .claimDate(claimDate)
                .insuredPerson(insuredPerson)
                .cardNumber(cardNumber)
                .examDate(examDate)
                .documents(documents)
                .amount(amount)
                .status(status)
                .bankName(bankName)
                .accountOwner(accountOwner)
                .accountNumber(accountNumber)
                .build();
    }
}