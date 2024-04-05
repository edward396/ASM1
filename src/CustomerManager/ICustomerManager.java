package CustomerManager;

/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
import Classes.Customer;

import java.util.List;

public interface ICustomerManager {
    void add(Customer customer);
    void delete(String customerID);
    Customer getOne(String customerID);
    List<Customer> getAll();
    void saveToFile(String filename);
}
