/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package TUI;

import Classes.Claim;
import Manager.ClaimProcessManager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static TUI.userInterface.*;

public class menuDisplay {
    static final ClaimProcessManager claimManager = new ClaimProcessManager();
    static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) {
        claimManager.loadFromFile("claimData.txt");

        Scanner scanner = new Scanner(System.in);

        //Menu
        while (true) {
            System.out.println("Welcome to Insurance Claims Management System");
            System.out.println("1. Add a Claim");
            System.out.println("2. Update a Claim");
            System.out.println("3. Delete a Claim");
            System.out.println("4. View a Claim");
            System.out.println("5. View all Claims");
            System.out.println("6. Save and Exit");
            System.out.print("Enter your choice: ");

            int choice = 0;

            //Handle invalid input
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.println("-------------------------------------------");
                scanner.nextLine();
                continue;
            }

            //Call the method relative to the user's input
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
                    System.out.println("-------------------------------------------");
            }
        }
    }
}
