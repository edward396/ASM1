package ProcessManager;

import Classes.InsuranceCard;

import java.util.List;

public interface InsuranceCardProcessManager {
    InsuranceCard getOne(String policyHolderID);
    List<InsuranceCard> getAll();
    void saveToFile(String filename);
    void loadFromFile(String filename);
}