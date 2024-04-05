/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Classes;

import java.util.List;

public class Dependent extends Customer {
    private PolicyHolder policyHolder;

    public Dependent(String customerID, String fullName, String insuranceCard, List<Claim> claimList) {
        super(customerID, fullName, insuranceCard, claimList);
    }
    public PolicyHolder getPolicyHolder() {
        return policyHolder;
    }

    public void setPolicyHolder(PolicyHolder policyHolder) {
        this.policyHolder = policyHolder;
    }


    @Override
    public String toString() {
        return "Dependent{" +
                "customerID='" + getCustomerID() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", insuranceCard='" + getInsuranceCard() + '\'' +
//                ", claimList=" + getClaimList() +
                '}';
    }
}