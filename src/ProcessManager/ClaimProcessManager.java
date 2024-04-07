package ProcessManager;

import Classes.Claim;

import java.util.List;

/**
 * The interface Claim process manager.
 */
public interface ClaimProcessManager {
    /**
     * Add.
     *
     * @param claim the claim
     */
    void add(Claim claim);

    /**
     * Update.
     *
     * @param claim the claim
     */
    void update(Claim claim);

    /**
     * Delete.
     *
     * @param claimID the claim id
     */
    void remove(String claimID);

    /**
     * Gets one.
     *
     * @param claimID the claim id
     * @return the one
     */
    Claim getOne(String claimID);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<Claim> getAll();

    /**
     * Gets all claim i ds.
     *
     * @return the all claim i ds
     */
    List<String> getAllClaimIDs();

    /**
     * Gets all claims by customer id.
     *
     * @param customerId the customer id
     * @return the all claims by customer id
     */
    List<Claim> getAllClaimsByCustomerID(String customerId);

    /**
     * Save to file.
     *
     * @param fileName the file name
     */
    void saveToFile(String fileName);

    /**
     * Load from file.
     *
     * @param fileName the file name
     */
    void loadFromFile(String fileName);
}