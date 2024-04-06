package Handler;

import Classes.Claim;
import Classes.Customer;
import ProcessManager.ClaimProcessManager;
import ProcessManager.ClaimProcessManagerImplement;
import ProcessManager.CustomerProcessManager;
import ProcessManager.CustomerProcessManagerImplement;

import java.util.*;

public class ClaimMenuHandler {
    private final ClaimProcessManager claimManager;
    private final CustomerProcessManager customerProcessManager;

    public ClaimMenuHandler() {
        this.claimManager = new ClaimProcessManagerImplement("src/File/claimData.txt");
        this.customerProcessManager = new CustomerProcessManagerImplement();
    }

    public void addClaim(Scanner scanner) {
        try {
            String customerID = getCustomerID(scanner);
            if (customerID == null) {
                System.out.println("Invalid or non-existing customer ID. Please try again.");
                return;
            }

            Customer customer = customerProcessManager.getOne(customerID);
            if (customer == null) {
                System.out.println("Customer not found.");
                return;
            }

            Claim claim = buildClaim(scanner, customerID);  // Pass the correct customerID here

            if (claim != null) {
                claimManager.add(claim);
                System.out.println("Claim added successfully.");
            }
        } catch (Exception e) {
            handleException("Error adding claim: ", e);
        }
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
            handleException("Error updating claim: ", e);
        }
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
            handleException("Error deleting claim: ", e);
        }
    }

    public void viewClaim(Scanner scanner) {
        try {
            String claimID = InputValidator.getFormattedClaimID(scanner);
            Claim existingClaim = claimManager.getOne(claimID);

            if (existingClaim != null) {
                System.out.println(existingClaim);
            } else {
                System.out.println("Claim not found.");
            }
        } catch (Exception e) {
            handleException("Error viewing claim: ", e);
        }
    }

    public void viewAllClaimsByCustomerID(Scanner scanner) {
        try {
            String customerID = getCustomerID(scanner);
            if (customerID == null) {
                System.out.println("Invalid or non-existing customer ID. Please try again.");
                return;
            }

            List<Claim> claims = claimManager.getAllClaimsByCustomerID(customerID);
            if (!claims.isEmpty()) {
                claims.forEach(System.out::println);
            } else {
                System.out.println("No claims found for this Customer ID.");
            }
        } catch (Exception e) {
            handleException("Error viewing claims by customer ID: ", e);
        }
    }

    public void viewAllClaims() {
        try {
            List<Claim> allClaims = claimManager.getAll();
            if (!allClaims.isEmpty()) {
                allClaims.forEach(System.out::println);
            } else {
                System.out.println("No claims found.");
            }
        } catch (Exception e) {
            handleException("Error viewing all claims: ", e);
        }
    }

    private String getCustomerID(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter Customer ID (Policy Holder or Dependant): ");
                String customerID = scanner.nextLine().trim();

                if (isValidCustomerID(customerID)) {
                    return customerID;
                } else {
                    System.out.println("Invalid or non-existing customer ID. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid customer ID.");
            }
        }
    }

    private boolean isValidCustomerID(String customerID) {
        return customerProcessManager.exists(customerID) ||
                customerProcessManager.exists("c-" + customerID) ||
                customerProcessManager.exists("d-" + customerID);
    }

    private Claim buildClaim(Scanner scanner, String customerID) {
        String claimID = generateClaimID();
        Date claimDate = InputValidator.getDateInput(scanner, "Enter Claim Date (dd-MM-yyyy): ");
        String cardNumber = InputValidator.getStringInput(scanner, "Enter Card Number (10 numbers): ");
        Date examDate = InputValidator.getDateInput(scanner, "Enter Exam Date (dd-MM-yyyy): ");
        List<String> documents = Arrays.asList(InputValidator.getStringInput(scanner, "Enter Document Names (claimId_cardNumber_documentName.pdf): ").split("_"));
        double amount = InputValidator.getDoubleInput(scanner, "Enter Claim Amount: ");
        String status = InputValidator.getClaimStatus(scanner);
        String[] bankInfo = getBankInfo(scanner);

        if (bankInfo == null) {
            return null;
        }

        return new Claim.Builder()
                .claimID(claimID)
                .claimDate(claimDate)
                .customerID(customerID)  // Set the correct customerID here
                .cardNumber(cardNumber)
                .examDate(examDate)
                .documents(documents)
                .amount(amount)
                .status(status)
                .bankName(bankInfo[0])
                .accountOwner(bankInfo[1])
                .accountNumber(bankInfo[2])
                .build();
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
        String[] bankInfo = getBankInfo(scanner);

        if (bankInfo == null) {
            return null;
        }

        builder.status(status)
                .bankName(bankInfo[0])
                .accountOwner(bankInfo[1])
                .accountNumber(bankInfo[2]);

        return builder.build();
    }

    private String[] getBankInfo(Scanner scanner) {
        String bankName = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Bank Name): ");
        String accountOwner = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Account Owner): ");
        String accountNumber = InputValidator.getStringInput(scanner, "Enter Receiver Banking Info (Account Number): ");

        if (!validateBankInfo(bankName, accountOwner, accountNumber)) {
            System.out.println("Error: Bank info cannot be empty.");
            return null;
        }

        return new String[]{bankName, accountOwner, accountNumber};
    }

    private boolean validateBankInfo(String bankName, String accountOwner, String accountNumber) {
        return !bankName.isEmpty() && !accountOwner.isEmpty() && !accountNumber.isEmpty();
    }

    private String generateClaimID() {
        List<String> existingClaimIDs = claimManager.getAllClaimIDs();
        int maxClaimID = existingClaimIDs.stream()
                .map(claimID -> Integer.parseInt(claimID.split("-")[1]))
                .max(Integer::compareTo)
                .orElse(0);

        return String.format("f-%010d", maxClaimID + 1);
    }

    private void handleException(String message, Exception e) {
        System.out.println(message + e.getMessage());
    }

    public void saveAndExit() {
        try {
            claimManager.saveToFile("src/File/claimData.txt");
            System.out.println("Claim data saved.");
        } catch (Exception e) {
            handleException("Error saving claim data: ", e);
        }
    }
}