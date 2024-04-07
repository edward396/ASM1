package ProcessManager;

import Classes.Customer;

import java.util.List;

/**
 * The interface Customer process manager.
 */
public interface CustomerProcessManager {
    /**
     * Add policy holder.
     *
     * @param fullName            the full name
     * @param insuranceCardNumber the insurance card number
     * @param dependentIds        the dependent ids
     * @throws Exception the exception
     */
    void addPolicyHolder(String fullName, String insuranceCardNumber, List<String> dependentIds) throws Exception;

    /**
     * Add dependent.
     *
     * @param fullName       the full name
     * @param policyHolderId the policy holder id
     * @throws Exception the exception
     */
    void addDependent(String fullName, String policyHolderId) throws Exception;

    /**
     * Update policy holder.
     *
     * @param id                  the id
     * @param fullName            the full name
     * @param insuranceCardNumber the insurance card number
     * @throws Exception the exception
     */
    void updatePolicyHolder(String id, String fullName, String insuranceCardNumber) throws Exception;

    /**
     * Update dependent.
     *
     * @param id             the id
     * @param fullName       the full name
     * @param policyHolderId the policy holder id
     * @throws Exception the exception
     */
    void updateDependent(String id, String fullName, String policyHolderId) throws Exception;

    /**
     * Delete.
     *
     * @param customerID the customer id
     */
    void delete(String customerID);

    /**
     * Gets one.
     *
     * @param customerID the customer id
     * @return the one
     */
    Customer getOne(String customerID);

    /**
     * Exists boolean.
     *
     * @param customerID the customer id
     * @return the boolean
     */
    boolean exists(String customerID);

    /**
     * Gets all.
     *
     * @return the all
     */
    List<Customer> getAll();

    /**
     * Gets all by type.
     *
     * @param type the type
     * @return the all by type
     */
    List<Customer> getAllByType(String type);

    /**
     * Gets all dependents by policy holder id.
     *
     * @param policyHolderID the policy holder id
     * @return the all dependents by policy holder id
     */
    List<Customer> getAllDependentsByPolicyHolderID(String policyHolderID);

    /**
     * Save to file.
     *
     * @param customerFilename  the customer filename
     * @param dependentFilename the dependent filename
     */
    void saveToFile(String customerFilename, String dependentFilename);

    /**
     * Load from file.
     *
     * @param customerFilename  the customer filename
     * @param dependentFilename the dependent filename
     * @throws Exception the exception
     */
    void loadFromFile(String customerFilename, String dependentFilename) throws Exception;
}