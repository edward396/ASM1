package ProcessManager;

import Classes.Claim;

import java.util.List;

public interface ClaimProcessManager {
    void add(Claim claim);
    void update(Claim claim);
    void delete(String claimID);
    Claim getOne(String claimID);
    List<Claim> getAll();
    List<Claim> getAllClaimsByCustomerID(String customerId);
    void saveToFile(String fileName);
    void loadFromFile(String fileName);
}