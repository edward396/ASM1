package ProcessManager;

import Classes.InsuranceCard;

import java.util.List;

/**
 * The interface Insurance card process manager.
 */
public interface InsuranceCardProcessManager {
    /**
     * Gets one.
     *
     * @param cardNumber the card number
     * @return the one
     */
    InsuranceCard getOne(String cardNumber);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<InsuranceCard> getAll();

    /**
     * Add.
     *
     * @param card the card
     */
    void add(InsuranceCard card);

    /**
     * Save to file.
     *
     * @param fileName the file name
     */
    void saveToFile(String fileName);
}