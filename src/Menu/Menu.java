package Menu;

import Handler.ClaimMenuHandler;
import Handler.CustomerMenuHandler;
import Handler.InsuranceCardMenuHandler;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * The type Menu display.
 */
public class Menu {

    private final ClaimMenuHandler claimMenuHandler;
    private final CustomerMenuHandler customerMenuHandler;
    private final InsuranceCardMenuHandler insuranceCardMenuHandler;
    private final Scanner scanner;

    /**
     * Instantiates a new Menu display.
     */
    public Menu() {
        this.claimMenuHandler = new ClaimMenuHandler();
        this.customerMenuHandler = new CustomerMenuHandler();
        this.insuranceCardMenuHandler = new InsuranceCardMenuHandler();
        this.scanner = new Scanner(System.in);
    }


    /**
     * Display menu.
     */
    public void displayMenu() {
        boolean continueProgram = true;
        try {
            while (continueProgram) {
                displayOptions();
                int choice = getChoice();

                switch (choice) {
                    case 1:
                        claimMenuHandler.addClaim(scanner);
                        break;
                    case 2:
                        claimMenuHandler.updateClaim(scanner);
                        break;
                    case 3:
                        claimMenuHandler.deleteClaim(scanner);
                        break;
                    case 4:
                        claimMenuHandler.viewClaim(scanner);
                        break;
                    case 5:
                        claimMenuHandler.viewAllClaimsByCustomerID(scanner);
                        break;
                    case 6:
                        claimMenuHandler.viewAllClaims();
                        break;
                    case 7:
                        customerMenuHandler.addPolicyHolder(scanner);
                        break;
                    case 8:
                        customerMenuHandler.addDependent(scanner);
                        break;
                    case 9:
                        customerMenuHandler.viewCustomer(scanner);
                        break;
                    case 10:
                        customerMenuHandler.deleteCustomer(scanner);
                        break;
                    case 11:
                        customerMenuHandler.viewDependentsOfPolicyHolder(scanner);
                        break;
                    case 12:
                        customerMenuHandler.viewAllCustomers();
                        break;
                    case 13:
                        insuranceCardMenuHandler.viewInsuranceCard(scanner);
                        break;
                    case 14:
                        insuranceCardMenuHandler.viewAllInsuranceCards();
                        break;
                    case 15:
                        claimMenuHandler.saveAndExit();
                        customerMenuHandler.saveAndExit();
                        insuranceCardMenuHandler.saveToFile();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 15.");
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
        System.out.println("| 9. View a Customer                         |");
        System.out.println("| 10. Delete a Customer                      |");
        System.out.println("| 11. View all Dependents of a Policy Holder |");
        System.out.println("| 12. View all Customers in the system       |");
        System.out.println("==============================================");
        System.out.println("|           Insurance Card Options           |");
        System.out.println("| 13. View an Insurance Card                 |");
        System.out.println("| 14. View all Insurance Cards in the system |");
        System.out.println("==============================================");
        System.out.println("|              System Options                |");
        System.out.println("| 15. Save and Exit                          |");
        System.out.println("==============================================");
        System.out.print("Enter your choice: ");
    }

    private int getChoice() {
        int choice = 0;
        try {
            choice = scanner.nextInt();
            if (choice < 1 || choice > 15) {
                throw new InputMismatchException();
            }
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number between 1 and 15.");
            scanner.nextLine();
        } catch (NoSuchElementException e) {
            System.out.println("Input not found. Exiting...");
            System.exit(0);
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