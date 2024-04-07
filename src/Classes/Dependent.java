/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package Classes;

/**
 * The type Dependent.
 */
public class Dependent extends Customer {
    private String policyHolderID;

    /**
     * Instantiates a new Dependent.
     *
     * @param customerID     the customer id
     * @param fullName       the full name
     * @param insuranceCard  the insurance card
     * @param policyHolderID the policy holder id
     */
    public Dependent(String customerID, String fullName, InsuranceCard insuranceCard, String policyHolderID) {
        super(customerID, fullName, insuranceCard);
        this.policyHolderID = policyHolderID;
    }

    /**
     * Gets policy holder id.
     *
     * @return the policy holder id
     */
    public String getPolicyHolderID() {
        return policyHolderID;
    }

    /**
     * Sets policy holder id.
     *
     * @param policyHolderID the policy holder id
     */
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