/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Classes;

import java.util.ArrayList;
import java.util.List;

public class PolicyHolder extends Customer {
    private List<Dependent> dependents;

    public PolicyHolder(String customerID, String fullName, String insuranceCard, List<Claim> claimList, List<Dependent> dependents) {
        super(customerID, fullName, insuranceCard, claimList);
        this.dependents = dependents;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    @Override
    public String toString() {
        return "PolicyHolder{" +
                "customerID='" + getCustomerID() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", insuranceCard='" + getInsuranceCard() + '\'' +
//                ", claimList=" + getClaimList() +
                ", dependents=" + dependents +
                '}';
    }
}