package Handler;

import Classes.Claim;
import ProcessManager.ClaimProcessManager;
import ProcessManager.ClaimProcessManagerImplement;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ClaimInputHandler {
    private ClaimProcessManager claimManager;
    private CustomerInputHandler customerInputHandler;

    public ClaimInputHandler() {
        this.claimManager = new ClaimProcessManagerImplement("src/File/claimData.txt");
        this.customerInputHandler = new CustomerInputHandler();
        try {
            claimManager.loadFromFile("src/File/claimData.txt");
        } catch (Exception e) {
            System.out.println("Error initializing ClaimProcessManager: " + e.getMessage());
            System.exit(1);
        }
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

            claimManager.add(claim);
            System.out.println("Claim added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding claim: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private String getCustomerID(Scanner scanner) {
        System.out.print("Enter Customer ID (Policy Holder or Dependant): ");
        return scanner.nextLine();
    }

    private Claim buildClaim(Scanner scanner, String insuredPerson) {
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

    private String generateClaimID() {
        String generatedID = "";
        boolean isUnique = false;

        while (!isUnique) {
            StringBuilder sb = new StringBuilder();
            sb.append("f-");
            String numbers = "0123456789";
            for (int i = 0; i < 10; i++) {
                int index = (int) (numbers.length() * Math.random());
                sb.append(numbers.charAt(index));
            }
            generatedID = sb.toString();
            if (claimManager.getOne(generatedID) == null) {
                isUnique = true;
            }
        }
        return generatedID;
    }

    public void updateClaim(Scanner scanner) {
        try {
            String claimID = InputValidator.getFormattedClaimID(scanner);
            Claim existingClaim = claimManager.getOne(claimID);

            if (existingClaim != null) {
                Claim updatedClaim = buildUpdatedClaim(scanner, existingClaim);
                claimManager.update(updatedClaim);
                System.out.println("Claim updated successfully.");
            } else {
                System.out.println("Claim not found.");
            }
        } catch (Exception e) {
            System.out.println("Error updating claim: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private Claim buildUpdatedClaim(Scanner scanner, Claim existingClaim) {
        String status = InputValidator.getClaimStatus(scanner);
        String bankName = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Bank Name): ");
        String accountOwner = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Account Owner): ");
        String accountNumber = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Account Number): ");

        if (bankName.isEmpty() || accountOwner.isEmpty() || accountNumber.isEmpty()) {
            System.out.println("Error: Bank info cannot be empty.");
            return null;
        }

        return new Claim.Builder()
                .claimID(existingClaim.getClaimID())
                .claimDate(existingClaim.getClaimDate())
                .insuredPerson(existingClaim.getInsuredPerson())
                .cardNumber(existingClaim.getCardNumber())
                .examDate(existingClaim.getExamDate())
                .documents(existingClaim.getDocuments())
                .amount(existingClaim.getAmount())
                .status(status)
                .bankName(bankName)
                .accountOwner(accountOwner)
                .accountNumber(accountNumber)
                .build();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            System.exit(0);
        } catch (Exception e) {
            System.out.println("Error saving claim data: " + e.getMessage());
            e.printStackTrace();
        }
    }
}