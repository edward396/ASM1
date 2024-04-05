package CustomerManager;

/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
import Classes.Claim;
import Classes.Customer;

import java.util.List;

public interface ICustomerManager {
    void addPolicyHolder(String id, String fullName, String insuranceCard, List<Claim> claims);
    void addDependent(String id, String fullName, String insuranceCard, String policyHolderId);
    void delete(String customerID);
    Customer getOne(String customerID);
    List<Customer> getAll();
    void saveToFile(String filename);
}
