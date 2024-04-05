package ProcessManager;

/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
import Classes.Customer;

import java.util.List;

public interface CustomerProcessManager {
    void addPolicyHolder(String id, String fullName, String insuranceCardNumber) throws Exception;
    void addDependent(String id, String fullName, String policyHolderId) throws Exception;
    void delete(String customerID);
    Customer getOne(String customerID);

    boolean exists(String customerID);

    List<Customer> getAll();
    void saveToFile(String customerFilename, String dependentFilename);
    void loadFromFile(String customerFilename, String dependentFilename);
}


