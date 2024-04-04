/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package TUI;

import TUI.ClaimInputHandler;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MenuDisplay {

    private final ClaimInputHandler inputHandler = new ClaimInputHandler();

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
            System.out.println("| 6. Save and Exit                           |");
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
                    inputHandler.addClaim(scanner);
                    break;
                case 2:
                    inputHandler.updateClaim(scanner);
                    break;
                case 3:
                    inputHandler.deleteClaim(scanner);
                    break;
                case 4:
                    inputHandler.viewClaim(scanner);
                    break;
                case 5:
                    inputHandler.viewAllClaims();
                    break;
                case 6:
                    inputHandler.saveAndExit();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    System.out.println("-------------------------------------------");
            }
        }
    }
}
