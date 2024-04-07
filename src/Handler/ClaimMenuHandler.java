/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package Handler;

import Classes.Claim;
import Classes.Customer;
import ProcessManager.ClaimProcessManager;
import ProcessManager.ClaimProcessManagerImplement;
import ProcessManager.CustomerProcessManager;
import ProcessManager.CustomerProcessManagerImplement;

import java.util.*;

/**
 * Handles the menu operations related to Claim entities.
 */
public class ClaimMenuHandler {
    private final ClaimProcessManager claimManager;
    private final CustomerProcessManager customerProcessManager;

    /**
     * Instantiates a new Claim menu handler.
     */
    public ClaimMenuHandler() {
        this.claimManager = new ClaimProcessManagerImplement("src/File/claimData.txt");
        this.customerProcessManager = new CustomerProcessManagerImplement();
    }

    /**
     * Adds a new claim.
     *
     * @param scanner the scanner
     */
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

    /**
     * Updates an existing claim.
     *
     * @param scanner the scanner
     */
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

    /**
     * Removes a claim.
     *
     * @param scanner the scanner
     */
    public void removeClaim(Scanner scanner) {
        try {
            String claimID = InputValidator.getFormattedClaimID(scanner);
            Claim existingClaim = claimManager.getOne(claimID);

            if (existingClaim != null) {
                claimManager.remove(claimID);
                System.out.println("Claim removed successfully.");
            } else {
                System.out.println("Claim not found.");
            }
        } catch (Exception e) {
            handleException("Error removing claim: ", e);
        }
    }

    /**
     * Displays details of a specific claim.
     *
     * @param scanner the scanner
     */
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

    /**
     * Displays all claims associated with a specific customer ID.
     *
     * @param scanner the scanner
     */
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

    /**
     * Displays all existing claims.
     */
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

    /**
     * Prompts the user to enter a Customer ID (either Policy Holder or Dependant) and validates it.
     *
     * @param scanner the scanner
     * @return the validated customer ID
     */
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

    /**
     * Checks if the provided customer ID is valid.
     *
     * @param customerID the customer ID to validate
     * @return true if the customer ID is valid, otherwise false
     */
    private boolean isValidCustomerID(String customerID) {
        return customerProcessManager.exists(customerID) ||
                customerProcessManager.exists("c-" + customerID) ||
                customerProcessManager.exists("d-" + customerID);
    }

    /**
     * Builds a new Claim object using the provided information.
     *
     * @param scanner    the scanner
     * @param customerID the customer ID associated with the claim
     * @return the constructed Claim object
     */
    private Claim buildClaim(Scanner scanner, String customerID) {
        String claimID = generateClaimID();
        Date claimDate = InputValidator.getDateInput(scanner, "Enter Claim Date (dd-MM-yyyy): ");
        String cardNumber = InputValidator.getStringInput(scanner, "Enter Card Number: ");
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

    /**
     * Builds an updated Claim object using the provided information.
     *
     * @param scanner       the scanner
     * @param existingClaim the existing claim to be updated
     * @return the updated Claim object
     */
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

    /**
     * Prompts the user to enter banking information.
     *
     * @param scanner the scanner
     * @return an array containing bank name, account owner, and account number
     */
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

    /**
     * Validates the provided banking information.
     *
     * @param bankName      the bank name
     * @param accountOwner  the account owner
     * @param accountNumber the account number
     * @return true if the banking information is valid, otherwise false
     */
    private boolean validateBankInfo(String bankName, String accountOwner, String accountNumber) {
        return !bankName.isEmpty() && !accountOwner.isEmpty() && !accountNumber.isEmpty();
    }

    /**
     * Generates a new unique claim ID.
     *
     * @return the generated claim ID
     */
    private String generateClaimID() {
        List<String> existingClaimIDs = claimManager.getAllClaimIDs();
        int maxClaimID = existingClaimIDs.stream()
                .map(claimID -> Integer.parseInt(claimID.split("-")[1]))
                .max(Integer::compareTo)
                .orElse(0);

        return String.format("f-%010d", maxClaimID + 1);
    }

    /**
     * Handles exceptions by displaying an error message.
     *
     * @param message the error message prefix
     * @param e       the exception
     */
    private void handleException(String message, Exception e) {
        System.out.println(message + e.getMessage());
    }

    /**
     * Saves the claim data to a file and exits the program.
     */
    public void saveAndExit() {
        try {
            claimManager.saveToFile("src/File/claimData.txt");
            System.out.println("Claim data saved.");
        } catch (Exception e) {
            handleException("Error saving claim data: ", e);
        }
    }
}