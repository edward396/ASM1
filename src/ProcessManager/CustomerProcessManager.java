/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package ProcessManager;

import Classes.Customer;

import java.util.List;

/**
 * The interface for managing customer processes.
 * This interface defines the methods to manage customers and their dependents.
 */
public interface CustomerProcessManager {
    void addPolicyHolder(String fullName, String insuranceCardNumber, List<String> dependentIds) throws Exception;

    void addDependant(String fullName, String policyHolderId) throws Exception;

    void updatePolicyHolder(String id, String fullName, String insuranceCardNumber) throws Exception;

    void updateDependent(String id, String fullName, String policyHolderId) throws Exception;

    void remove(String customerID);

    Customer getOne(String customerID);

    boolean exists(String customerID);

    List<Customer> getAll();

    List<Customer> getAllByType(String type);

    List<Customer> getAllDependentsByPolicyHolderID(String policyHolderID);

    void saveToFile(String customerFilename, String dependentFilename);

    void loadFromFile(String customerFilename, String dependentFilename) throws Exception;
}