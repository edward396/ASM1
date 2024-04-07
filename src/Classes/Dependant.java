/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package Classes;

/**
 * The type Dependant.
 */
public class Dependant extends Customer {
    private String policyHolderID;

    /**
     * Instantiates a new Dependant.
     *
     * @param customerID     the customer id
     * @param fullName       the full name
     * @param insuranceCard  the insurance card
     * @param policyHolderID the policy holder id
     */
    public Dependant(String customerID, String fullName, InsuranceCard insuranceCard, String policyHolderID) {
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
        return "Dependant{" +
                "customerID='" + getCustomerID() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", insuranceCard=" + getInsuranceCard() +
                ", policyHolderID='" + policyHolderID + '\'' +
                '}';
    }
}