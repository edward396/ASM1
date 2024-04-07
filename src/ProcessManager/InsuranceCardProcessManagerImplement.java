/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package ProcessManager;

import Classes.InsuranceCard;

import java.io.*;
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
    public void add(InsuranceCard insuranceCard) {
        insuranceCards.add(insuranceCard);
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
                writer.print(card.getCardNumber() + ", ");
                writer.print(card.getCardHolderID() + ", ");
                writer.print(card.getPolicyOwner() + ", ");
                writer.print(dateFormat.format(card.getExpirationDate()));
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void loadFromFile(String fileName) throws Exception {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Invalid line format: " + line);
                }
                processInsuranceCardData(parts);
            }
        }
    }

    private void processInsuranceCardData(String[] parts) throws ParseException {
        String cardNumber = parts[0].trim();
        String customerID = parts[1].trim();
        String policyOwner = parts[2].trim();
        String expirationDateStr = parts[3].trim();

        InsuranceCard insuranceCard = new InsuranceCard(cardNumber);
        insuranceCard.setCardHolderID(customerID);  // Setting the cardHolderID to customerID
        insuranceCard.setPolicyOwner(policyOwner);  // Setting the policyOwner from the file

        try {
            insuranceCard.setExpirationDate(dateFormat.parse(expirationDateStr));
        } catch (Exception e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }

        add(insuranceCard);
    }
}