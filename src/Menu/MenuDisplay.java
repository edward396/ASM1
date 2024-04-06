package Menu;

import Classes.Claim;
import Handler.ClaimInputHandler;
import Handler.CustomerInputHandler;
import ProcessManager.ClaimProcessManagerImplement;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuDisplay {

    private final ClaimInputHandler claimInputHandler = new ClaimInputHandler();
    private final CustomerInputHandler customerInputHandler = new CustomerInputHandler();
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        boolean continueProgram = true;
        try {
            while (continueProgram) {
                displayOptions();
                int choice = getChoice();

                switch (choice) {
                    case 1:
                        claimInputHandler.addClaim(scanner);
                        break;
                    case 2:
                        claimInputHandler.updateClaim(scanner);
                        break;
                    case 3:
                        claimInputHandler.deleteClaim(scanner);
                        break;
                    case 4:
                        System.out.print("Enter the claim ID to view: ");
                        String claimID = scanner.nextLine();
                        claimInputHandler.viewClaim(claimID);
                        break;
                    case 5:
                        System.out.print("Enter the customer ID to view claims: ");
                        String customerID = scanner.nextLine();
                        claimInputHandler.viewAllClaimsByCustomerID(customerID);
                        break;
                    case 6:
                        claimInputHandler.viewAllClaims();
                        break;
                    case 7:
                        customerInputHandler.addPolicyHolder(scanner);
                        break;
                    case 8:
                        customerInputHandler.addDependent(scanner);
                        break;
                    case 9:
                        customerInputHandler.deleteCustomer(scanner);
                        break;
                    case 10:
                        customerInputHandler.viewAllCustomers();
                        customerInputHandler.viewCustomer(scanner);
                        break;
                    case 11:
                        customerInputHandler.viewDependentsOfPolicyHolder(scanner);
                        break;
                    case 12:
                        customerInputHandler.viewAllCustomers();
                        break;
                    case 13:
                        //viewInsuranceCard();
                        break;
                    case 14:
                        claimInputHandler.saveAndExit();
                        customerInputHandler.saveAndExit();
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                        System.out.println("-------------------------------------------");
                }
                continueProgram = continueOrExit();
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("Input not found. Exiting...");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }

    private void displayOptions() {
        System.out.println("==============================================");
        System.out.println("|        Welcome to Insurance Claims         |");
        System.out.println("|            Management System               |");
        System.out.println("==============================================");
        System.out.println("|               Claim Options                |");
        System.out.println("| 1. Add a Claim                             |");
        System.out.println("| 2. Update a Claim (by Claim ID)            |");
        System.out.println("| 3. Delete a Claim (by Claim ID)            |");
        System.out.println("| 4. View a Claim (by Claim ID)              |");
        System.out.println("| 5. View all Claims (by Customer ID)        |");
        System.out.println("| 6. View all Claims in the system           |");
        System.out.println("==============================================");
        System.out.println("|              Customer Options              |");
        System.out.println("| 7. Add a PolicyHolder                      |");
        System.out.println("| 8. Add a Dependent                         |");
        System.out.println("| 9. Delete a Customer                       |");
        System.out.println("| 10. View a Customer                        |");
        System.out.println("| 11. View all Dependents of a Policy Holder |");
        System.out.println("| 12. View all Customers in the system       |");
        System.out.println("==============================================");
        System.out.println("|           Insurance Card Options           |");
        System.out.println("| 13. View an Insurance Card                 |");
        System.out.println("==============================================");
        System.out.println("|              System Options                |");
        System.out.println("| 14. Save and Exit                          |");
        System.out.println("==============================================");
        System.out.print("Enter your choice: ");
    }

    private int getChoice() {
        int choice = 0;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
        return choice;
    }

    private boolean continueOrExit() {
        System.out.print("Do you want to continue? (yes/no): ");
        String continueChoice = scanner.nextLine();
        while (!continueChoice.equalsIgnoreCase("yes") && !continueChoice.equalsIgnoreCase("no")) {
            System.out.print("Invalid choice. Please enter 'yes' or 'no': ");
            continueChoice = scanner.nextLine();
        }
        return continueChoice.equalsIgnoreCase("yes");
    }
}