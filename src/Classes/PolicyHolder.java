/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Classes;

import java.util.ArrayList;
import java.util.List;

public class PolicyHolder extends Customer {
    private InsuranceCard insuranceCard;
    private List<String> dependentIDs;

    public PolicyHolder(String customerID, String fullName, InsuranceCard insuranceCard) {
        super(customerID, fullName, insuranceCard);
        this.insuranceCard = insuranceCard;
        this.dependentIDs = new ArrayList<>();  // Initialize the dependentIDs list
    }

    public PolicyHolder(String customerID, String fullName, InsuranceCard insuranceCard, List<String> dependentIDs) {
        super(customerID, fullName, insuranceCard);
        this.insuranceCard = insuranceCard;
        this.dependentIDs = dependentIDs != null ? dependentIDs : new ArrayList<>();  // Initialize the dependentIDs list if null
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(InsuranceCard insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    public List<String> getDependentIDs() {
        return dependentIDs;
    }

    public void setDependentIDs(List<String> dependentIDs) {
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