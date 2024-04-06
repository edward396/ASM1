/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Classes;

import java.util.HashSet;
import java.util.Set;

public class PolicyHolder extends Customer {
    private Set<String> dependentIDs;

    public PolicyHolder(String customerID, String fullName, InsuranceCard insuranceCard) {
        super(customerID, fullName, insuranceCard);
        this.dependentIDs = new HashSet<>();  // Initialize the dependentIDs set
    }

    public PolicyHolder(String customerID, String fullName, InsuranceCard insuranceCard, Set<String> dependentIDs) {
        super(customerID, fullName, insuranceCard);
        this.dependentIDs = dependentIDs != null ? dependentIDs : new HashSet<>();
    }

    public Set<String> getDependentIDs() {
        return dependentIDs;
    }

    public void setDependentIDs(Set<String> dependentIDs) {
        this.dependentIDs = dependentIDs;
    }

    @Override
    public String toString() {
        return "PolicyHolder{" +
                "customerID='" + getCustomerID() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", insuranceCard=" + getInsuranceCard() +
                ", dependentIDs=" + (dependentIDs != null ? String.join(", ", dependentIDs) : "") +
                '}';
    }
}