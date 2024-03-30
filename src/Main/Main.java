package Main;
import Classes.Claim;
import Classes.ClaimProcessManager;
import Classes.Customer;
import Classes.InsuranceCard;

import java.util.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final String CUSTOMERS_FILE = "customers.txt";
    private static final String INSURANCE_CARDS_FILE = "insurance_cards.txt";
    private static final String CLAIMS_FILE = "claims.txt";

    public static void main(String[] args) {
        // Load initial data from files
        Map<String, InsuranceCard> insuranceCards = FileHandler.readInsuranceCards(INSURANCE_CARDS_FILE);
        Map<String, Customer> customers = FileHandler.readCustomers(CUSTOMERS_FILE, insuranceCards);
        List<Claim> initialClaims = FileHandler.readClaims(CLAIMS_FILE);

        // Initialize Claim Process Manager
        ClaimProcessManager claimProcessManager = new SimpleClaimProcessManager(initialClaims);

        // Initialize Insurance Claims System
        InsuranceClaimsSystem system = new InsuranceClaimsSystem(customers, insuranceCards, claimProcessManager);

        // Run the text-based UI program
        system.run();
    }
}

class InsuranceClaimsSystem {
    private Map<String, Customer> customers;
    private Map<String, InsuranceCard> insuranceCards;
    private ClaimProcessManager claimsManager;

    public InsuranceClaimsSystem(Map<String, Customer> customers, Map<String, InsuranceCard> insuranceCards,
                                 ClaimProcessManager claimsManager) {
        this.customers = customers;
        this.insuranceCards = insuranceCards;
        this.claimsManager = claimsManager;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    addClaim();
                    break;
                case 2:
                    updateClaimStatus();
                    break;
                case 3:
                    deleteClaim();
                    break;
                case 4:
                    getOneClaim();
                    break;
                case 5:
                    getAllClaims();
                    break;
                case 6:
                    saveData();
                    System.out.println("Data saved. Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 6.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("Insurance Claims Management System");
        System.out.println("1. Add Claim");
        System.out.println("2. Update Claim Status");
        System.out.println("3. Delete Claim");
        System.out.println("4. Get One Claim");
        System.out.println("5. Get All Claims");
        System.out.println("6. Exit");
    }

    private void addClaim() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter Claim ID: ");
            String id = scanner.nextLine();

            System.out.print("Enter Claim Date (YYYY-MM-DD): ");
            String claimDate = scanner.nextLine();

            System.out.print("Enter Insured Person ID: ");
            String insuredPerson = scanner.nextLine();

            System.out.print("Enter Card Number: ");
            String cardNumber = scanner.nextLine();

            System.out.print("Enter Exam Date (YYYY-MM-DD): ");
            String examDate = scanner.nextLine();

            System.out.print("Enter Documents (separated by '|'): ");
            String[] documents = scanner.nextLine().split("\\|");

            System.out.print("Enter Claim Amount: ");
            int amount = scanner.nextInt();

            System.out.print("Enter Status (New/Processing/Done): ");
            String status = scanner.next();

            System.out.print("Enter Receiver Banking Info (Bank – Name – Number): ");
            String bankingInfo = scanner.next();

            Claim newClaim = new Claim(id, claimDate, insuredPerson, cardNumber, examDate, Arrays.asList(documents), amount, status, bankingInfo);
            claimsManager.add(newClaim);

            System.out.println("Claim added successfully.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void updateClaimStatus() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Claim ID to update: ");
        String id = scanner.nextLine();

        System.out.print("Enter new Status (New/Processing/Done): ");
        String status = scanner.next();

        claimsManager.update(id, status);
        System.out.println("Claim status updated successfully.");
    }

    private void deleteClaim() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Claim ID to delete: ");
        String id = scanner.nextLine();

        claimsManager.delete(id);
        System.out.println("Claim deleted successfully.");
    }

    private void getOneClaim() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Claim ID to get details: ");
        String id = scanner.nextLine();

        Claim claim = claimsManager.getOne(id);
        if (claim != null) {
            System.out.println(claim.toString());
        } else {
            System.out.println("Claim not found.");
        }
    }

    private void getAllClaims() {
        List<Claim> allClaims = claimsManager.getAll();

        if (allClaims.isEmpty()) {
            System.out.println("No claims found.");
            return;
        }

        System.out.println("List of All Claims:");
        for (Claim claim : allClaims) {
            System.out.println(claim.toString());
        }
    }

    private void saveData() {
        FileHandler.writeCustomers(InsuranceClaimsApp.CUSTOMERS_FILE, customers);
        FileHandler.writeInsuranceCards(InsuranceClaimsApp.INSURANCE_CARDS_FILE, insuranceCards);
        FileHandler.writeClaims(InsuranceClaimsApp.CLAIMS_FILE, claimsManager.getAll());
    }
}