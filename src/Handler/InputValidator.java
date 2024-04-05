package Handler;

/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

import Classes.Claim;

import java.text.SimpleDateFormat;
import java.util.*;

public class InputValidator {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static String getFormattedClaimID(Scanner scanner) {
        while (true) {
            System.out.print("Enter Claim ID (f-followed by 10 numbers): ");
            String input = scanner.nextLine();
            String claimID = formatClaimID(input);
            if (claimID != null) {
                return claimID;
            }
        }
    }

    private static String formatClaimID(String input) {
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

    public static Date getDateInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                return dateFormat.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid date format. Please enter the date in dd-MM-yyyy format.");
            }
        }
    }

    public static double getDoubleInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                double value = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character
                if (value < 0) {
                    throw new IllegalArgumentException("Amount cannot be negative.");
                }
                return value;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine(); // Consume newline character
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String getStringInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            } else {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
    }

    public static String getClaimStatus(Scanner scanner) {
            String status;
            do {
                status = getStringInput(scanner, "Enter Status (New, Processing, Done): ").toLowerCase();
                if (!Arrays.asList("new", "processing", "done").contains(status)) {
                    System.out.println("Invalid status. Status must be New, Processing, or Done. Please try again.");
                }
            } while (!Arrays.asList("new", "processing", "done").contains(status));
            return status;
        }

    public static String getUniqueClaimID(Scanner scanner, List<Claim> existingClaims) {
        String claimID;
        boolean isUnique;
        do {
            isUnique = true;
            System.out.print("Enter the Claim ID: ");
            claimID = scanner.nextLine();
            for (Claim claim : existingClaims) {
                if (claim.getClaimID().equals(claimID)) {
                    isUnique = false;
                    System.out.println("Claim ID already exists. Please enter a unique Claim ID.");
                    break;
                }
            }
        } while (!isUnique);
        return claimID;
    }
    public static String getCustomerID(Scanner scanner) {
        String id;
        do {
            System.out.print("Enter the Customer ID (c-followed by 7 numbers): ");
            id = scanner.nextLine();
            if (!id.matches("^c-\\d{7}$")) {
                System.out.println("Invalid ID format. Please enter again.");
            }
        } while (!id.matches("^c-\\d{7}$"));
        return id;
    }
}