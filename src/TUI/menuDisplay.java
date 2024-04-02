/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package TUI;

import java.util.Scanner;

public class menuDisplay {
        public void displayMenu() {
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
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            userInterface claimProcessUI = new userInterface();

            //Call the method relative to the user's input
            switch (choice) {
                case 1:
                    claimProcessUI.addClaim(scanner);
                    break;
                case 2:
                    claimProcessUI.updateClaim(scanner);
                    break;
                case 3:
                    claimProcessUI.deleteClaim(scanner);
                    break;
                case 4:
                    claimProcessUI.viewClaim(scanner);
                    break;
                case 5:
                    claimProcessUI.viewAllClaims();
                    break;
                case 6:
                    claimProcessUI.saveAndExit();
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
                    System.out.println("-------------------------------------------");
            }
        }
    }
}
