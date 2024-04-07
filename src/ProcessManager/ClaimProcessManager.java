/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package ProcessManager;

import Classes.Claim;

import java.util.List;

/**
 * The interface for managing claim processes.
 * This interface defines the methods to manage claims.
 */
 public interface ClaimProcessManager {
    void add(Claim claim);

    void update(Claim claim);

    void remove(String claimID);

    Claim getOne(String claimID);

    List<Claim> getAll();

    List<String> getAllClaimIDs();

    List<Claim> getAllClaimsByCustomerID(String customerId);

    void saveToFile(String fileName);

    void loadFromFile(String fileName);
}