/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package TUI;

import Classes.Claim;
import Manager.ClaimProcess;
import Manager.ClaimProcessManager;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class userInterface {
    private static final ClaimProcess claimManager = new ClaimProcessManager();
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public void addClaim(Scanner scanner) {
        try {
            String claimID = getFormattedClaimID(scanner);

            Date claimDate = getDateInput(scanner, "Enter Claim Date (dd-MM-yyyy): ");

            System.out.print("Enter Insured Person: ");
            String insuredPerson = scanner.nextLine();

            System.out.print("Enter Card Number: ");
            String cardNumber = scanner.nextLine();

            Date examDate = getDateInput(scanner, "Enter Exam Date (dd-MM-yyyy): ");

            System.out.print("Enter Document Names (claimId_cardNumber_documentName.pdf): ");
            String[] documentArray = scanner.nextLine().split("_");
            List<String> documents = Arrays.asList(documentArray);

            double amount = getDoubleInput(scanner, "Enter Claim Amount: ");

            String status = getStringInput(scanner, "Enter Status (New, Processing, Done): ");

            System.out.print("Enter Receiver Banking Info (Bank - Name - Number): ");
            String receiverBankingInfo = scanner.nextLine();

            Claim newClaim = new Claim(claimID, claimDate, insuredPerson, cardNumber, examDate, documents, amount, status, receiverBankingInfo);
            claimManager.add(newClaim);
            System.out.println("Claim added successfully.");
        } catch (Exception e) {
            System.out.println("Error adding claim: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    public void updateClaim(Scanner scanner) {
        try {
            String claimID = getFormattedClaimID(scanner);

            Claim existingClaim = claimManager.getOne(claimID);

            if (existingClaim != null) {
                Date claimDate = getDateInput(scanner, "Enter Claim Date (dd-MM-yyyy): ");

                System.out.print("Enter Insured Person: ");
                existingClaim.setInsuredPerson(scanner.nextLine());

                System.out.print("Enter Card Number: ");
                existingClaim.setCardNumber(scanner.nextLine());

                Date examDate = getDateInput(scanner, "Enter Exam Date (dd-MM-yyyy): ");

                System.out.print("Enter Document Names (claimId_cardNumber_documentName.pdf): ");
                String[] documentArray = scanner.nextLine().split("_");
                existingClaim.setDocuments(Arrays.asList(documentArray));

                double amount = getDoubleInput(scanner, "Enter Claim Amount: ");

                String status = getStringInput(scanner, "Enter Status (New, Processing, Done): ");

                System.out.print("Enter Receiver Banking Info (Bank - Name - Number): ");
                existingClaim.setReceiverBankingInfo(scanner.nextLine());

                claimManager.update(existingClaim);
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
            String claimID = scanner.nextLine();

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

    public void viewClaim(Scanner scanner) {
        try {
            System.out.print("Enter claim ID to view: ");
            String claimID = scanner.nextLine();

            Claim existingClaim = claimManager.getOne(claimID);

            if (existingClaim != null) {
                System.out.println(existingClaim);
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
            claimManager.saveToFile("src/File/claimData.txt");
            System.out.println("Claims saved to file. Exiting...");
        } catch (Exception e) {
            System.out.println("Error saving claims: " + e.getMessage());
            System.out.println("-------------------------------------------");
        }
    }

    //Make sure format input handler for ID (must be "f-..." followed by 10 numbers)
    private String getFormattedClaimID(Scanner scanner) {
        while (true) {
            System.out.print("Enter Claim ID (f-followed by 10 numbers): ");
            String input = scanner.nextLine();
            String claimID = formatClaimID(input);
            if (claimID != null) {
                return claimID;
            }
        }
    }

    private String formatClaimID(String input) {
        if (input.matches("^f-\\d{10}$")) {
            return input;
        } else if (input.matches("^\\d{1,9}$")) {
            String paddedNumber = String.format("%010d", Long.parseLong(input));
            return "f-" + paddedNumber;
        } else {
            System.out.println("Invalid Claim ID format. Please enter the Claim ID in the correct format.");
            return null;
        }
    }

    //Error format input handler for Date
    private Date getDateInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return dateFormat.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
            }
        }
    }

    //Error format input handler for Amount
    private double getDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume newline character
            }
        }
    }

    private String getStringInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}
