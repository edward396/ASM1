/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Classes;
import java.util.List;

public interface ClaimProcessManager {
    void add(Claim claim);
    void update(String id, String status);
    void delete(String id);
    Claim getOne(String id);
    List<Claim> getAll();
}
