/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Classes;

import java.util.ArrayList;
import java.util.List;
public class PolicyHolder extends Customer {
    private List<Dependent> dependents;

    public PolicyHolder(String id, String fullName, String insuranceCard) {
        super(id, fullName, insuranceCard);
        this.dependents = new ArrayList<>();
    }

    public void addDependent(Dependent dependent) {
        if (dependents.contains(dependent)) {
            throw new IllegalArgumentException("Dependent already exists.");
        }
        this.dependents.add(dependent);
    }

    public void removeDependent(Dependent dependent) {
        this.dependents.remove(dependent);
    }
    @Override
    public String toString() {
        return "Clam Info:" + "\n" +
                "{Policy Holder ID: " + getCustomerID() + ", " +
                "Full Name: " + getFullName() + ", " +
                "Insurance Card: " + getInsuranceCard() + ", " +
                "Dependent List: " + dependents +
                "}";
    }
}
