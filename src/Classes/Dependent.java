/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Classes;

import Classes.InsuranceCard;

public class Dependent extends Customer {
    private String policyHolderID;

    public Dependent(String customerID, String fullName, InsuranceCard insuranceCard, String policyHolderID) {
        super(customerID, fullName, insuranceCard);
        this.policyHolderID = policyHolderID;
    }

    public Dependent(String customerID, String fullName, InsuranceCard insuranceCard) {
        super(customerID, fullName, insuranceCard);
    }


    public String getPolicyHolderID() {
        return policyHolderID;
    }

    @Override
    public String toString() {
        return "Dependent{" +
                "customerID='" + getCustomerID() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", insuranceCard=" + getInsuranceCard() +
                ", policyHolderID='" + policyHolderID + '\'' +
                '}';
    }
}