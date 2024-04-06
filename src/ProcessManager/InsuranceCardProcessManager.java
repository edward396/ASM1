package ProcessManager;

/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

import Classes.InsuranceCard;

import java.util.List;

public interface InsuranceCardProcessManager {
    void add(InsuranceCard insuranceCard);
    InsuranceCard getOne(String policyHolderID);
    List<InsuranceCard> getAll();
    void saveToFile(String filename);
    void loadFromFile(String filename);
}