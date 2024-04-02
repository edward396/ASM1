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
        claimManager.loadFromFile("sampleClaims.txt");

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date claimDate1 = dateFormat.parse("2022-01-15");
            Date examDate1 = dateFormat.parse("2022-01-20");
            Date claimDate2 = dateFormat.parse("2022-02-10");
            Date examDate2 = dateFormat.parse("2022-02-15");

            Claim claim1 = new Claim("f-000001", claimDate1, "John Doe", "1234567890", examDate1,
                    Arrays.asList("f-000001_1234567890_Document1.pdf"), 500.0, "New", "BankName - Name - 12345");
            Claim claim2 = new Claim("f-000002", claimDate2, "Jane Doe", "0987654321", examDate2,
                    Arrays.asList("f-000002_0987654321_Document1.pdf"), 700.0, "Processing", "BankName - Name - 67890");

            claimManager.add(claim1);
            claimManager.add(claim2);

        } catch (ParseException e) {
            e.printStackTrace();
        }
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

        System.out.print("Enter Document Names (comma-separated): ");
        String[] documentArray = scanner.nextLine().split(",");
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
            System.out.print("Enter Claim Date (yyyy-MM-dd): ");
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

            System.out.print("Enter Exam Date (yyyy-MM-dd): ");
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
        claimManager.getAll().forEach(System.out::println);
    }

    private static void saveAndExit() {
        claimManager.saveToFile("sampleClaims.txt");
        System.out.println("Data saved successfully. Exiting...");
    }
}
