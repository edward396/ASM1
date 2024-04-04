/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package ClaimManager;

import Classes.Claim;

import java.util.List;

public interface ClaimProcessManager {
    void add(Claim claim);
    void update(Claim claim);
    void delete(String claimId);
    Claim getOne(String claimId);
    List<Claim> getAll();
    void saveToFile(String fileName);
    void loadFromFile(String fileName);


}