package Handler;

import Classes.Claim;
import ProcessManager.ClaimProcessManager;
import ProcessManager.ClaimProcessManagerImplement;

import java.util.*;

public class ClaimInputHandler {
    private ClaimProcessManager claimManager;
    private CustomerInputHandler customerInputHandler;

    public ClaimInputHandler() {
        this.claimManager = new ClaimProcessManagerImplement("src/File/claimData.txt");
        this.customerInputHandler = new CustomerInputHandler();
    }

    public void addClaim(Scanner scanner) {
        try {
            String customerID = getCustomerID(scanner);
            if (customerID == null) {
                System.out.println("Customer not found.");
                return;
            }

            String insuredPerson = customerInputHandler.getCustomerName(customerID);
            Claim claim = buildClaim(scanner, insuredPerson);

            if (claim != null) {
                claimManager.add(claim);
                System.out.println("Claim added successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error adding claim: " + e.getMessage());
        }
    }

    private String getCustomerID(Scanner scanner) {
        System.out.print("Enter Customer ID (Policy Holder or Dependant): ");
        return scanner.nextLine();
    }

    private Claim buildClaim(Scanner scanner, String customerID) {
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
            return null;
        }

        return new Claim.Builder()
                .claimID(claimID)
                .claimDate(claimDate)
                .customerID(customerID)
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

    private String generateClaimID() {
        String generatedID;
        do {
            generatedID = "f-" + UUID.randomUUID().toString().substring(0, 8);
        } while (claimManager.getOne(generatedID) != null);
        return generatedID;
    }

    public void updateClaim(Scanner scanner) {
        try {
            String claimID = InputValidator.getFormattedClaimID(scanner);
            Claim existingClaim = claimManager.getOne(claimID);

            if (existingClaim != null) {
                Claim updatedClaim = buildUpdatedClaim(scanner, existingClaim);
                if (updatedClaim != null) {
                    claimManager.update(updatedClaim);
                    System.out.println("Claim updated successfully.");
                }
            } else {
                System.out.println("Claim not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating claim: " + e.getMessage());
        }
    }

    private Claim buildUpdatedClaim(Scanner scanner, Claim existingClaim) {
        Claim.Builder builder = new Claim.Builder()
                .claimID(existingClaim.getClaimID())
                .claimDate(existingClaim.getClaimDate())
                .customerID(existingClaim.getCustomerID())
                .cardNumber(existingClaim.getCardNumber())
                .examDate(existingClaim.getExamDate())
                .documents(existingClaim.getDocuments())
                .amount(existingClaim.getAmount());

        String status = InputValidator.getClaimStatus(scanner);
        String bankName = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Bank Name): ");
        String accountOwner = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Account Owner): ");
        String accountNumber = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Account Number): ");

        if (bankName.isEmpty() || accountOwner.isEmpty() || accountNumber.isEmpty()) {
            System.out.println("Error: Bank info cannot be empty.");
            return null;
        }

        builder.status(status)
                .bankName(bankName)
                .accountOwner(accountOwner)
                .accountNumber(accountNumber);

        return builder.build();
    }

    public void deleteClaim(Scanner scanner) {
        try {
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
        }
    }

    public void viewClaim(String claimID) {
        try {
            Claim existingClaim = claimManager.getOne(claimID);
            if (existingClaim != null) {
                System.out.println(existingClaim.toString());
            } else {
                System.out.println("Claim not found.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing claim: " + e.getMessage());
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
        }
    }

    public void viewAllClaimsByCustomerID(String customerId) {
        try {
            List<Claim> claims = claimManager.getAllClaimsByCustomerID(customerId);
            if (!claims.isEmpty()) {
                for (Claim claim : claims) {
                    System.out.println(claim);
                }
            } else {
                System.out.println("No claims found for this Customer ID.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing claims by customer ID: " + e.getMessage());
        }
    }

    public void saveAndExit() {
        try {
            claimManager.saveToFile("src/File/claimData.txt");
            System.out.println("Claim data saved. Exiting program...");
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error saving claim data: " + e.getMessage());
        }
    }
}