/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package ProcessManager;

import Classes.Claim;

import java.util.List;

public interface ClaimProcessManager {
    void add(Claim claim) throws Exception;
    void update(Claim claim) throws Exception;
    void delete(String claimID) throws Exception;
    Claim getOne(String claimID) throws Exception;
    List<Claim> getAll() throws Exception;
    void saveToFile(String claimFilePath) throws Exception;
    void loadFromFile(String claimFilePath) throws Exception;
}