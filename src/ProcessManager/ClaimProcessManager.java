/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package ProcessManager;

import Classes.Claim;

import java.util.List;

public interface ClaimProcessManager {
    void add(Claim claim) throws DuplicateClaimException, FileOperationException;

    void update(Claim claim) throws ClaimNotFoundException, FileOperationException;

    void delete(String claimID) throws ClaimNotFoundException, FileOperationException;

    Claim getOne(String claimID) throws ClaimNotFoundException, FileOperationException;

    List<Claim> getAll() throws FileOperationException;

    void saveToFile(String claimFilePath) throws FileOperationException;

    void loadFromFile(String claimFilePath) throws FileOperationException, InvalidFileFormatException;
}

class DuplicateClaimException extends Exception {
    public DuplicateClaimException(String message) {
        super(message);
    }
}

class ClaimNotFoundException extends Exception {
    public ClaimNotFoundException(String message) {
        super(message);
    }
}

class FileOperationException extends Exception {
    public FileOperationException(String message) {
        super(message);
    }
}

class InvalidFileFormatException extends Exception {
    public InvalidFileFormatException(String message) {
        super(message);
    }
}