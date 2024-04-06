package Handler;

/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
import Classes.Claim;
import ProcessManager.ClaimProcessManager;
import ProcessManager.ClaimProcessManagerImplement;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ClaimInputHandler {
    private ClaimProcessManager claimManager;
    private CustomerInputHandler customerInputHandler;

    public ClaimInputHandler() {
        this.claimManager = new ClaimProcessManagerImplement("src/File/claimData.txt");  // Added filename parameter
        this.customerInputHandler = new CustomerInputHandler();
        try {
            claimManager.loadFromFile("src/File/claimData.txt");
        } catch (Exception e) {
            System.out.println("Error initializing ClaimProcessManager: " + e.getMessage());
            System.exit(1);  // Exit the program if there's an error
        }
    }

    public void addClaim(Scanner scanner) {
        try {
            System.out.print("Enter Customer ID (Policy Holder or Dependant): ");
            String customerID = scanner.nextLine();

            if (!customerInputHandler.exists(customerID)) {
                System.out.println("Customer not found.");
                return;
            }

            String insuredPerson = customerInputHandler.getCustomerName(customerID);

            String claimID = generateClaimID();
            Date claimDate = InputValidator.getDateInput(scanner, "Enter Claim Date (dd-MM-yyyy): ");
            String cardNumber = InputValidator.getStringInput(scanner, "Enter Card Number: ");
            Date examDate = InputValidator.getDateInput(scanner, "Enter Exam Date (dd-MM-yyyy): ");
            List<String> documents = Arrays.asList(InputValidator.getStringInput(scanner, "Enter Document Names (claimId_cardNumber_documentName.pdf): ").split("_"));
            double amount = InputValidator.getDoubleInput(scanner, "Enter Claim Amount: ");
            String status = InputValidator.getClaimStatus(scanner);

            String bankName = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Bank Name): ");
            String accountOwner = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Account Owner): ");
            String accountNumber = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Account Number): ");

            if (bankName.isEmpty() || accountOwner.isEmpty() || accountNumber.isEmpty()) {
                System.out.println("Error: Bank info cannot be empty.");
                return;
            }

            Claim claim = new Claim.Builder()
                    .claimID(claimID)
                    .claimDate(claimDate)
                    .insuredPerson(insuredPerson)  // Set the insured person with the fetched customer name
                    .cardNumber(cardNumber)
                    .examDate(examDate)
                    .documents(documents)
                    .amount(amount)
                    .status(status)
                    .bankName(bankName)
                    .accountOwner(accountOwner)
                    .accountNumber(accountNumber)
                    .build();

            claimManager.add(claim);
            System.out.println("Claim added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding claim: " + e.getClass().getSimpleName() + " - " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    private String generateClaimID() {
        StringBuilder sb = new StringBuilder();
        sb.append("f-");
        String numbers = "0123456789";
        for (int i = 0; i < 10; i++) {
            int index = (int) (numbers.length() * Math.random());
            sb.append(numbers.charAt(index));
        }
        return sb.toString();
    }

    public void updateClaim(Scanner scanner) {
        try {
            String claimID = InputValidator.getFormattedClaimID(scanner);

            Claim existingClaim = claimManager.getOne(claimID);

            if (existingClaim != null) {
                String status = InputValidator.getClaimStatus(scanner);

                System.out.print("Enter Receiver Banking Info (Bank Name): ");
                String bankName = scanner.nextLine();

                System.out.print("Enter Receiver Banking Info (Account Owner): ");
                String accountOwner = scanner.nextLine();

                System.out.print("Enter Receiver Banking Info (Account Number): ");
                String accountNumber = scanner.nextLine();

                if (bankName.isEmpty() || accountOwner.isEmpty() || accountNumber.isEmpty()) {
                    System.out.println("Error: Bank info cannot be empty.");
                    return;
                }

                Claim updatedClaim = new Claim.Builder()
                        .claimID(claimID)
                        .claimDate(existingClaim.getClaimDate())  // Keep the original date
                        .insuredPerson(existingClaim.getInsuredPerson())  // Keep the original insured person
                        .cardNumber(existingClaim.getCardNumber())  // Keep the original card number
                        .examDate(existingClaim.getExamDate())  // Keep the original exam date
                        .documents(existingClaim.getDocuments())  // Keep the original documents
                        .amount(existingClaim.getAmount())  // Keep the original amount
                        .status(status)  // Update the status
                        .bankName(bankName)  // Update the bank name
                        .accountOwner(accountOwner)  // Update the account owner
                        .accountNumber(accountNumber)  // Update the account number
                        .build();
                claimManager.update(updatedClaim);
                System.out.println("Claim updated successfully.");

            } else {
                System.out.println("Claim not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating claim: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    public void deleteClaim(Scanner scanner) {
        try {
            System.out.print("Enter claim ID to delete: ");
            String claimID = InputValidator.getFormattedClaimID(scanner);

            Claim existingClaim = claimManager.getOne(claimID);

            if (existingClaim != null) {
                claimManager.delete(claimID);
                System.out.println("Claim deleted successfully.");
            } else {
                System.out.println("Claim not found.");
            }
        } catch (Exception e) {
            System.out.println("Error deleting claim: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    public void viewClaim(String claimID) {  // Modified to accept String claimID
        try {
            Claim existingClaim = claimManager.getOne(claimID);

            if (existingClaim != null) {
                System.out.println(existingClaim.toString());
            } else {
                System.out.println("Claim not found.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing claim: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    public void viewAllClaims() {
        try {
            List<Claim> allClaims = claimManager.getAll();
            if (!allClaims.isEmpty()) {
                for (Claim claim : allClaims) {
                    System.out.println(claim);
                }
            } else {
                System.out.println("No claims found.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing all claims: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    public void saveAndExit() {
        try {
            List<Claim> allClaims = claimManager.getAll();
            for (Claim claim : allClaims) {
                if (claim.getInsuredPerson() == null || claim.getClaimID() == null) {
                    System.out.println("Error saving claims: Insured Person ID cannot be null.");
                    return;
                }
            }

            claimManager.saveToFile("src/File/claimData.txt");
            System.out.println("Claim data saved. Exiting program...");
            System.exit(0);  // Exit the program
        } catch (Exception e) {
            System.out.println("Error saving claim data: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }
}