/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Classes;

import java.util.HashSet;
import java.util.Set;

public class PolicyHolder extends Customer {
    private InsuranceCard insuranceCard;
    private Set<String> dependentIDs;

    public PolicyHolder(String customerID, String fullName, InsuranceCard insuranceCard) {
        super(customerID, fullName, insuranceCard);
        this.insuranceCard = insuranceCard;
        this.dependentIDs = new HashSet<>();  // Initialize the dependentIDs list
    }

    public PolicyHolder(String customerID, String fullName, InsuranceCard insuranceCard, Set<String> dependentIDs) {
        super(customerID, fullName, insuranceCard);
        this.insuranceCard = insuranceCard;
        this.dependentIDs = dependentIDs;  // Initialize the dependentIDs list if null
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
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
                ", insuranceCard=" + insuranceCard +
                ", dependentIDs=" + (dependentIDs != null ? dependentIDs.toString().replace("[", "").replace("]", "") : "") +
                '}';
    }
}