/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Classes;

import java.util.ArrayList;
import java.util.List;
public class PolicyHolder extends Customer {

    private List<Dependent> dependentList;

    public PolicyHolder(String customerID, String fullName, String insuranceCard, ArrayList<Claim> claimList,
                        String insuranceCard1, List<Dependent> dependentList) {
        super(customerID, fullName, insuranceCard, claimList);
        this.dependentList = dependentList;
    }

    public List<Dependent> getDependents() {
        return dependentList;
    }


    public void addDependent(Dependent dependent) {
        if (dependentList.contains(dependent)) {
            throw new IllegalArgumentException("Dependent already exists.");
        }
        this.dependentList.add(dependent);
    }

    public void removeDependent(Dependent dependent) {
        this.dependentList.remove(dependent);
    }
    @Override
    public java.lang.String toString() {
        return "Claim Info:" + "\n" +
                "{Policy Holder ID: " + getCustomerID() + ", " +
                "Full Name: " + getFullName() + ", " +
                "Insurance Card: " + getInsuranceCard() + ", " +
                "Dependent List: " + dependentList +
                "}";
    }
}
