/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package TUI;

import Classes.BankingInfo;
import Classes.Claim;
import Classes.Customer;
import Classes.Dependent;
import Classes.PolicyHolder;
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

            System.out.print("Enter Insured Person ID: ");
            String insuredPersonID = scanner.nextLine();
            Customer insuredPerson = getCustomer(insuredPersonID);

            // Start of the while loop to handle incorrect person type
            while (insuredPerson == null) {
                System.out.print("Enter Insured Person Name: ");
                String insuredPersonName = scanner.nextLine();

                System.out.print("Enter Insured Person Email: ");
                String insuredPersonEmail = scanner.nextLine();


                String type;
                while (true) {
                    System.out.print("Enter Insured Person Type (Dependent/PolicyHolder): ");
                    type = scanner.nextLine();

                    if ("Dependent".equalsIgnoreCase(type)) {
                        insuredPerson = new Dependent(insuredPersonID, insuredPersonName, insuredPersonEmail);
                        claimManager.addCustomer(insuredPerson);
                        break;
                    } else if ("PolicyHolder".equalsIgnoreCase(type)) {
                        insuredPerson = new PolicyHolder(insuredPersonID, insuredPersonName, insuredPersonEmail);
                        claimManager.addCustomer(insuredPerson);
                        break;
                    } else {
                        System.out.println("Invalid person type. Please enter either Dependent or PolicyHolder.");
                    }
                }
            }
            // End of the while loop

            System.out.print("Enter Card Number: ");
            String cardNumber = scanner.nextLine();

            Date examDate = getDateInput(scanner, "Enter Exam Date (dd-MM-yyyy): ");

            System.out.print("Enter Document Names (claimId_cardNumber_documentName.pdf): ");
            String[] documentArray = scanner.nextLine().split("_");
            List<String> documents = Arrays.asList(documentArray);

            double amount = getDoubleInput(scanner, "Enter Claim Amount: ");

            // Loop for incorrect status
            String status;
            while (true) {
                status = getStringInput(scanner, "Enter Status (New, Processing, Done): ");
                if ("New".equalsIgnoreCase(status) || "Processing".equalsIgnoreCase(status) || "Done".equalsIgnoreCase(status)) {
                    break;
                } else {
                    System.out.println("Invalid status. Please enter either New, Processing, or Done.");
                }
            }
            // End loop

            // Loop for Receiver Banking Info
            String bank = "";
            String name = "";
            String number = "";
            while (true) {
                System.out.print("Enter Receiver Banking Info - Bank: ");
                bank = scanner.nextLine();

                System.out.print("Enter Receiver Banking Info - Name: ");
                name = scanner.nextLine();

                System.out.print("Enter Receiver Banking Info - Number: ");
                number = scanner.nextLine();

                if (!bank.isEmpty() && !name.isEmpty() && !number.isEmpty()) {
                    break;
                } else {
                    System.out.println("Please enter all banking info (Bank, Name, Number).");
                }
            }
            // End loop

            BankingInfo receiverBankingInfo = new BankingInfo(bank, name, number);

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
                String insuredPersonID = scanner.nextLine();
                Customer insuredPerson = getCustomer(insuredPersonID);

                System.out.print("Enter Card Number: ");
                existingClaim.setCardNumber(scanner.nextLine());

                Date examDate = getDateInput(scanner, "Enter Exam Date (dd-MM-yyyy): ");

                System.out.print("Enter Document Names (claimId_cardNumber_documentName.pdf): ");
                String[] documentArray = scanner.nextLine().split("_");
                existingClaim.setDocuments(Arrays.asList(documentArray));

                double amount = getDoubleInput(scanner, "Enter Claim Amount: ");

                String status = getStringInput(scanner, "Enter Status (New, Processing, Done): ");

                System.out.print("Enter Receiver Banking Info (Bank - Name - Number): ");
                String[] bankingInfoArray = scanner.nextLine().split(" - ");
                existingClaim.setReceiverBankingInfo(new BankingInfo(bankingInfoArray[0], bankingInfoArray[1], bankingInfoArray[2]));

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
            System.out.println("Data saved. Exiting program...");
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

    private Customer getCustomer(String customerID) {
        List<Customer> allCustomers = claimManager.getAllCustomers();
        for (Customer customer : allCustomers) {
            if (customer.getCustomerID().equals(customerID)) {
                return customer;
            }
        }
        return null;
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