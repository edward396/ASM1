package ProcessManager;

import Classes.InsuranceCard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InsuranceCardProcessManagerImplement implements InsuranceCardProcessManager {
    private static final String FILE_PATH = "src/File/insuranceCardData.txt";
    private List<InsuranceCard> insuranceCards = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public InsuranceCardProcessManagerImplement() {
        try {
            loadFromFile(FILE_PATH);
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public InsuranceCard getOne(String cardNumber) {
        for (InsuranceCard card : insuranceCards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }

    @Override
    public List<InsuranceCard> getAll() {
        return insuranceCards;
    }

    @Override
    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (InsuranceCard card : insuranceCards) {
                writer.println(card.toString());
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");

                if (parts.length == 4) {
                    processInsuranceCardData(parts);
                } else {
                    throw new IllegalArgumentException("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
    }

    private void processInsuranceCardData(String[] parts) {
        try {
            String cardNumber = parts[0].trim();
            String cardHolderID = parts[1].trim();
            String policyHolderID = parts[2].trim();
            Date expirationDate = dateFormat.parse(parts[3].trim());

            // Validate card holder ID format
            if (!cardHolderID.matches("c-\\d{7}")) {
                throw new IllegalArgumentException("Invalid card holder ID format: " + cardHolderID);
            }

            // Validate policy holder ID format
            if (!policyHolderID.matches("c-\\d{7}")) {
                throw new IllegalArgumentException("Invalid policy holder ID format: " + policyHolderID);
            }

            InsuranceCard insuranceCard = new InsuranceCard(cardNumber, cardHolderID, policyHolderID, expirationDate);
            insuranceCards.add(insuranceCard);

        } catch (ParseException e) {
            throw new IllegalArgumentException("Error parsing line: " + String.join(", ", parts) + ". Error: " + e.getMessage(), e);
        }
    }
}