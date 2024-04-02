/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Manager;

import Classes.Claim;
import Classes.Customer;

import java.util.List;

public interface ClaimProcess {
    void add(Claim claim);
    void update(Claim claim);
    void delete(String claimId);
    Claim getOne(String claimId);
    List<Claim> getAll();
    void saveToFile(String fileName);
    void loadFromFile(String fileName);

    List<Customer> getAllCustomers();

    void addCustomer(Customer insuredPerson);
}