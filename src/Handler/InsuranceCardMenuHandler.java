package Handler;

import ProcessManager.InsuranceCardProcessManagerImplement;
import Classes.InsuranceCard;

import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InsuranceCardMenuHandler {

    private final InsuranceCardProcessManagerImplement insuranceCardProcessManager;

    public InsuranceCardMenuHandler() {
        this.insuranceCardProcessManager = new InsuranceCardProcessManagerImplement("src/File/insuranceCardData.txt");
    }

    public void viewInsuranceCard(Scanner scanner) {
        System.out.print("Enter the card number to view: ");
        String cardNumber = scanner.nextLine();

        InsuranceCard card = insuranceCardProcessManager.getOne(cardNumber);
        if (card != null) {
            System.out.println("Card Number: " + card.getCardNumber() +
                    ", Card Holder ID: " + card.getCardHolderID() +
                    ", Policy Holder ID: " + card.getPolicyHolderID() +
                    ", Expiration Date: " + new SimpleDateFormat("dd-MM-yyyy").format(card.getExpirationDate()));
        } else {
            System.out.println("Insurance Card not found.");
        }
    }

    public void viewAllInsuranceCards() {
        List<InsuranceCard> cards = insuranceCardProcessManager.getAll();

        if (cards.isEmpty()) {
            System.out.println("No insurance cards found.");
            return;
        }

        System.out.println("All Insurance Cards:");
        for (InsuranceCard card : cards) {
            System.out.println("Card Number: " + card.getCardNumber() +
                    ", Card Holder ID: " + card.getCardHolderID() +
                    ", Policy Holder ID: " + card.getPolicyHolderID() +
                    ", Expiration Date: " + new SimpleDateFormat("dd-MM-yyyy").format(card.getExpirationDate()));
        }
    }

    public void saveToFile() {
        insuranceCardProcessManager.saveToFile("src/File/insuranceCardData.txt");
    }
}