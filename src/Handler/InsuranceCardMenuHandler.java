/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * @version JDK21
 */

package Handler;

import ProcessManager.InsuranceCardProcessManagerImplement;
import Classes.InsuranceCard;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;
import java.io.File;

/**
 * A menu handler class for managing Insurance Card operations.
 * This class provides methods to view individual and all insurance cards and to save the insurance card data to a file.
 */
public class InsuranceCardMenuHandler {

    private final InsuranceCardProcessManagerImplement insuranceCardProcessManager;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private final String filePath = "src/File/insuranceCardData.txt";

    /**
     * Instantiates a new Insurance card menu handler.
     * It checks if the insurance card data file exists, and if not, creates a new file.
     */
    public InsuranceCardMenuHandler() {
        this.insuranceCardProcessManager = new InsuranceCardProcessManagerImplement();

        // Check if the file exists, if not, create a new file
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println("Error creating insurance card data file: " + e.getMessage());
            }
        }
    }

    /**
     * View insurance card details by card number.
     *
     * @param scanner the scanner object for user input.
     */
    public void viewInsuranceCard(Scanner scanner) {
        try {
            System.out.print("Enter the card number to view: ");
            String cardNumber = scanner.nextLine();

            InsuranceCard card = insuranceCardProcessManager.getOne(cardNumber);
            if (card != null) {
                printInsuranceCardDetails(card);
            } else {
                System.out.println("Insurance Card not found.");
            }
        } catch (Exception e) {
            System.out.println("Error viewing insurance card: " + e.getMessage());
        }
    }

    /**
     * View details of all insurance cards.
     */
    public void viewAllInsuranceCards() {
        try {
            List<InsuranceCard> cards = insuranceCardProcessManager.getAll();

            if (cards.isEmpty()) {
                System.out.println("No insurance cards found.");
                return;
            }

            System.out.println("All Insurance Cards:");
            for (InsuranceCard card : cards) {
                printInsuranceCardDetails(card);
            }
        } catch (Exception e) {
            System.out.println("Error viewing all insurance cards: " + e.getMessage());
        }
    }

    /**
     * Prints the details of an InsuranceCard object.
     *
     * @param card the InsuranceCard object to print details of.
     */
    private void printInsuranceCardDetails(InsuranceCard card) {
        System.out.println("Card Number: " + card.getCardNumber() +
                ", Card Holder ID: " + card.getCardHolderID() +
                ", Policy Owner: " + card.getPolicyOwner() +
                ", Expiration Date: " + dateFormat.format(card.getExpirationDate()));
    }

    /**
     * Saves the insurance card data to a file.
     */
    public void saveToFile() {
        try {
            insuranceCardProcessManager.saveToFile(filePath);
            System.out.println("Insurance card data saved.");
        } catch (Exception e) {
            System.out.println("Error saving insurance card data: " + e.getMessage());
        }
    }
}