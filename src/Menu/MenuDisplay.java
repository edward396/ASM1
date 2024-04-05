/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Menu;

import Handler.ClaimInputHandler;
import Handler.CustomerInputHandler;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuDisplay {

    private final ClaimInputHandler claimInputHandler = new ClaimInputHandler();
    private final CustomerInputHandler customerInputHandler = new CustomerInputHandler();
    private final Scanner scanner = new Scanner(System.in);


    public void displayMenu() {

        //Menu
        boolean continueProgram = true;
        while (continueProgram) {
            System.out.println("==============================================");
            System.out.println("|        Welcome to Insurance Claims         |");
            System.out.println("|            Management System               |");
            System.out.println("==============================================");
            System.out.println("|               Claim Options                |");
            System.out.println("| 1. Add a Claim                             |");
            System.out.println("| 2. Update a Claim                          |");
            System.out.println("| 3. Delete a Claim                          |");
            System.out.println("| 4. View a Claim                            |");
            System.out.println("| 5. View all Claims                         |"); //should have for one customer
            System.out.println("==============================================");
            System.out.println("|              Customer Options              |");
            System.out.println("| 6. Add a PolicyHolder                      |");
            System.out.println("| 7. Add a Dependent                         |");
            System.out.println("| 8. Delete a Customer                       |");
            System.out.println("| 9. View a Customer                         |");
            System.out.println("| 10. View all Customers                     |"); //View insurance card, one enum to specify the type of customers
            System.out.println("==============================================");
            System.out.println("|              System Options                |");
            System.out.println("| 11. Save and Exit                          |");
            System.out.println("==============================================");
            System.out.print("Enter your choice: ");

            int choice = 0;

            //Handle invalid input
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            } catch (NoSuchElementException e) {
                System.out.println("No line found. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            //Call the method relative to the user's input
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
                    claimInputHandler.viewAllClaims();
                    break;
                case 6:
                    customerInputHandler.addPolicyHolder(scanner);
                    break;
                case 7:
                    customerInputHandler.addDependent(scanner);
                    break;
                case 8:
                    customerInputHandler.deleteCustomer(scanner);
                    break;
                case 9:
                    System.out.print("Enter the customer ID to view: ");
                    String id = scanner.nextLine();
                    customerInputHandler.viewCustomer(id);
                    break;
                case 10:
                    customerInputHandler.viewAllCustomers();
                    break;
                case 11:
                    claimInputHandler.saveAndExit();
                    customerInputHandler.saveAndExit();
                    scanner.close(); // Close the scanner
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    System.out.println("-------------------------------------------");
            }


            // Prompt to ask the user if they want to continue or exit
            System.out.print("Do you want to continue? (yes/no): ");
            String continueChoice = scanner.nextLine();
            while (!continueChoice.equalsIgnoreCase("yes") && !continueChoice.equalsIgnoreCase("no")) {
                System.out.print("Invalid choice. Please enter 'yes' or 'no': ");
                continueChoice = scanner.nextLine();
            }
            if (continueChoice.equalsIgnoreCase("no")) {
                claimInputHandler.saveAndExit();
                customerInputHandler.saveAndExit();
                scanner.close(); // Close the scanner
                continueProgram = false;
            }
        }
    }
}