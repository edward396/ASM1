package ProcessManager;

import Classes.InsuranceCard;

import java.util.List;

public interface InsuranceCardProcessManager {
    InsuranceCard getOne(String cardNumber);
    List<InsuranceCard> getAll();
    void add(InsuranceCard card);  // Add this line

    void saveToFile(String fileName);
}