/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Classes;

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

    public void setPolicyHolderID(String policyHolderID) {
        this.policyHolderID = policyHolderID;
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