/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package ProcessManager;

import Classes.InsuranceCard;

import java.util.List;

/**
 * The interface for managing insurance card processes.
 * This interface defines the methods to manage insurance cards.
 */
public interface InsuranceCardProcessManager {
    InsuranceCard getOne(String cardNumber);

    List<InsuranceCard> getAll();

    void add(InsuranceCard card);

    void saveToFile(String fileName);
}