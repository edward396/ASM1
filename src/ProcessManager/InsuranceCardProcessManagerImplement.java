/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package ProcessManager;

import Classes.InsuranceCard;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * The interface for managing insurance card processes.
 * This interface defines the methods to manage insurance cards.
 */
public class InsuranceCardProcessManagerImplement implements InsuranceCardProcessManager {

    /** The file path where insurance card data is stored. */
    private static final String FILE_PATH = "src/File/insuranceCardData.txt";

    /** The list to hold InsuranceCard objects. */
    private List<InsuranceCard> insuranceCards = new ArrayList<>();

    /** The date format used for formatting and parsing dates. */
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    /**
     * Constructs an InsuranceCardProcessManagerImplement and loads data from the file.
     */
    public InsuranceCardProcessManagerImplement() {
        try {
            loadFromFile(FILE_PATH);
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Adds a new InsuranceCard object to the list.
     *
     * @param insuranceCard the InsuranceCard to add
     */
    @Override
    public void add(InsuranceCard insuranceCard) {
        insuranceCards.add(insuranceCard);
    }

    /**
     * Retrieves an InsuranceCard object by card number.
     *
     * @param cardNumber the card number of the InsuranceCard to retrieve
     * @return the InsuranceCard object if found, otherwise null
     */
    @Override
    public InsuranceCard getOne(String cardNumber) {
        for (InsuranceCard card : insuranceCards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }

    /**
     * Retrieves all InsuranceCard objects.
     *
     * @return the list of all InsuranceCard objects
     */
    @Override
    public List<InsuranceCard> getAll() {
        return insuranceCards;
    }

    /**
     * Saves the list of InsuranceCard objects to a file.
     *
     * @param fileName the name of the file to save to
     */
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

    /**
     * Loads insurance card data from a file and populates the list.
     *
     * @param fileName the name of the file to load from
     * @throws Exception if an error occurs while reading the file or parsing the data
     */
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

    /**
     * Processes a line of data read from the file and creates an InsuranceCard object.
     *
     * @param parts the parts of the data line split by ","
     */
    private void processInsuranceCardData(String[] parts) {
        String cardNumber = parts[0].trim();
        String customerID = parts[1].trim();
        String policyOwner = parts[2].trim();
        String expirationDateStr = parts[3].trim();

        InsuranceCard insuranceCard = new InsuranceCard(cardNumber);
        insuranceCard.setCardHolderID(customerID);
        insuranceCard.setPolicyOwner(policyOwner);

        try {
            insuranceCard.setExpirationDate(dateFormat.parse(expirationDateStr));
        } catch (Exception e) {
            System.out.println("Error parsing date: " + e.getMessage());
        }

        add(insuranceCard);
    }
}