package ProcessManager;

import Classes.InsuranceCard;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsuranceCardProcessManagerImplement implements InsuranceCardProcessManager {
    private List<InsuranceCard> insuranceCards = new ArrayList<>();
    private String filename;

    public InsuranceCardProcessManagerImplement(String filename) {
        this.filename = filename;
        try {
            loadFromFile(filename);
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public InsuranceCard getOne(String policyHolderID) {
        for (InsuranceCard card : insuranceCards) {
            if (card.getPolicyHolderID().equals(policyHolderID)) {
                return card;
            }
        }
        return null;
    }

    @Override
    public List<InsuranceCard> getAll() {
        return new ArrayList<>(insuranceCards);
    }

    public void saveToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            for (InsuranceCard card : insuranceCards) {
                writer.println(card.getCardNumber() + ", " +
                        card.getCardHolderID() + ", " +
                        card.getPolicyHolderID() + ", " +
                        dateFormat.format(card.getExpirationDate()));
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 4) {
                    String cardNumber = parts[0].trim();
                    String cardHolderID = parts[1].trim();
                    String policyHolderID = parts[2].trim();
                    Date expirationDate = dateFormat.parse(parts[3].trim());

                    InsuranceCard card = new InsuranceCard(cardNumber, cardHolderID, policyHolderID, expirationDate);
                    insuranceCards.add(card);
                } else {
                    throw new RuntimeException("Invalid line format in insurance card file: " + line);
                }
            }
        } catch (IOException | ParseException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}