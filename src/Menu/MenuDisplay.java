/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Menu;

import Handler.ClaimInputHandler;
import Handler.ManagerInputHandler;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuDisplay {

    private final ClaimInputHandler claimInputHandler = new ClaimInputHandler();
    private final ManagerInputHandler managerInputHandler = new ManagerInputHandler();

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);

        //Menu
        while (true) {
            System.out.println("==============================================");
            System.out.println("|        Welcome to Insurance Claims         |");
            System.out.println("|            Management System               |");
            System.out.println("==============================================");
            System.out.println("| 1. Add a Claim                             |");
            System.out.println("| 2. Update a Claim                          |");
            System.out.println("| 3. Delete a Claim                          |");
            System.out.println("| 4. View a Claim                            |");
            System.out.println("| 5. View all Claims                         |");
            System.out.println("| 6. Add a Customer                          |");
            System.out.println("| 7. Delete a Customer                       |");
            System.out.println("| 8. View a Customer                         |");
            System.out.println("| 9. View all Customers                      |");
            System.out.println("| 10. Save and Exit                          |");
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
                    claimInputHandler.viewClaim(scanner);
                    break;
                case 5:
                    claimInputHandler.viewAllClaims();
                    break;
                case 6:
                    managerInputHandler.addCustomer(scanner);
                    break;
                case 7:
                    managerInputHandler.deleteCustomer(scanner);
                    break;
                case 8:
                    managerInputHandler.viewCustomer(scanner);
                    break;
                case 9:
                    managerInputHandler.viewAllCustomers();
                    break;
                case 10:
                    claimInputHandler.saveAndExit();
                    managerInputHandler.saveAndExit();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    System.out.println("-------------------------------------------");
            }


            // Prompt to ask the user if they want to continue or exit
            String continueChoice;
            do {
                System.out.print("Do you want to continue? (yes/no): ");
                continueChoice = scanner.nextLine().toLowerCase();  // Convert input to lowercase

                if (!continueChoice.equals("yes") && !continueChoice.equals("no")) {
                    System.out.println("Invalid input. Please enter 'yes' or 'no'.");
                }

            } while (!continueChoice.equals("yes") && !continueChoice.equals("no"));

            if (continueChoice.equals("no")) {
                claimInputHandler.saveAndExit();
                managerInputHandler.saveAndExit();
                System.out.println("Data saved. Exiting program...");
                return;  // Return to the main menu instead of breaking the loop
            }
        }
    }
}