/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package TUI;

import Classes.Claim;
import Manager.ClaimProcessManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TUI {
    private static final ClaimProcessManager claimManager = new ClaimProcessManager();

    public static void main(String[] args) {
        claimManager.loadFromFile("claimData.txt");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to Insurance Claims Management System");
            System.out.println("1. Add a Claim");
            System.out.println("2. Update a Claim");
            System.out.println("3. Delete a Claim");
            System.out.println("4. View a Claim");
            System.out.println("5. View all Claims");
            System.out.println("6. Save and Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addClaim(scanner);
                    break;
                case 2:
                    updateClaim(scanner);
                    break;
                case 3:
                    deleteClaim(scanner);
                    break;
                case 4:
                    viewClaim(scanner);
                    break;
                case 5:
                    viewAllClaims();
                    break;
                case 6:
                    saveAndExit();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void loadSampleData() {
        claimManager.loadFromFile("src/resources/claimData.txt");
        System.out.println("Sample data loaded successfully.");
    }

    private static void addClaim(Scanner scanner) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        System.out.print("Enter Claim ID: ");
        String claimID = scanner.nextLine();

        System.out.print("Enter Claim Date (dd-MM-yyyy): ");
        Date claimDate = null;
        try {
            claimDate = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.print("Enter Insured Person: ");
        String insuredPerson = scanner.nextLine();

        System.out.print("Enter Card Number: ");
        String cardNumber = scanner.nextLine();

        System.out.print("Enter Exam Date (dd-MM-yyyy): ");
        Date examDate = null;
        try {
            examDate = dateFormat.parse(scanner.nextLine());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.print("Enter Document Names (seperate by underscore): ");
        String[] documentArray = scanner.nextLine().split("_");
        List<String> documents = Arrays.asList(documentArray);

        System.out.print("Enter Claim Amount: ");
        double amount = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Enter Status (New, Processing, Done): ");
        String status = scanner.nextLine();

        System.out.print("Enter Receiver Banking Info (Bank - Name - Number): ");
        String receiverBankingInfo = scanner.nextLine();

        Claim newClaim = new Claim(claimID, claimDate, insuredPerson, cardNumber, examDate, documents, amount, status, receiverBankingInfo);
        claimManager.add(newClaim);
        System.out.println("Claim added successfully.");
    }

    private static void updateClaim(Scanner scanner) {
        System.out.print("Enter claim ID to update: ");
        String claimID = scanner.nextLine();

        Claim existingClaim = claimManager.getOne(claimID);

        if (existingClaim != null) {
            System.out.print("Enter Claim Date (dd-MM-yyyy): ");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            try {
                Date claimDate = dateFormat.parse(scanner.nextLine());
                existingClaim.setClaimDate(claimDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.print("Enter Insured Person: ");
            existingClaim.setInsuredPerson(scanner.nextLine());

            System.out.print("Enter Card Number: ");
            existingClaim.setCardNumber(scanner.nextLine());

            System.out.print("Enter Exam Date (dd-MM-yyyy): ");
            try {
                Date examDate = dateFormat.parse(scanner.nextLine());
                existingClaim.setExamDate(examDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            System.out.print("Enter Document Names (comma-separated): ");
            String[] documentArray = scanner.nextLine().split(",");
            existingClaim.setDocuments(Arrays.asList(documentArray));

            System.out.print("Enter Claim Amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            existingClaim.setAmount(amount);

            System.out.print("Enter Status (New, Processing, Done): ");
            existingClaim.setStatus(scanner.nextLine());

            System.out.print("Enter Receiver Banking Info (Bank - Name - Number): ");
            existingClaim.setReceiverBankingInfo(scanner.nextLine());

            claimManager.update(existingClaim);
            System.out.println("Claim updated successfully.");
        } else {
            System.out.println("Claim not found.");
        }
    }

    private static void deleteClaim(Scanner scanner) {
        System.out.print("Enter claim ID to delete: ");
        String claimID = scanner.nextLine();

        Claim existingClaim = claimManager.getOne(claimID);

        if (existingClaim != null) {
            claimManager.delete(claimID);
            System.out.println("Claim deleted successfully.");
        } else {
            System.out.println("Claim not found.");
        }
    }

    private static void viewClaim(Scanner scanner) {
        System.out.print("Enter claim ID to view: ");
        String claimId = scanner.nextLine();

        Claim claim = claimManager.getOne(claimId);

        if (claim != null) {
            System.out.println(claim);
        } else {
            System.out.println("Claim not found.");
        }
    }

    private static void viewAllClaims() {
        List<Claim> allClaims = claimManager.getAll();
        if (!allClaims.isEmpty()) {
            for (Claim claim : allClaims) {
                System.out.println(claim);
            }
        } else {
            System.out.println("No claims found.");
        }
    }

    private static void saveAndExit() {
        claimManager.saveToFile("src/File/claimData.txt");
        System.out.println("Data saved successfully. Exiting...");
    }
}
