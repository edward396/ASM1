/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package Classes;

/**
 * Represents a Dependant in the Insurance Claim Management System.
 */
public class Dependant extends Customer {
    private String policyHolderID;

    /**
     * Constructor to instantiate a new Dependant.
     *
     * @param customerID     the customer ID
     * @param fullName       the full name of the dependant
     * @param insuranceCard  the insurance card associated with the dependant
     * @param policyHolderID the ID of the policyholder
     */
    public Dependant(String customerID, String fullName, InsuranceCard insuranceCard, String policyHolderID) {
        super(customerID, fullName, insuranceCard);
        this.policyHolderID = policyHolderID;
    }

    /**
     * Getters and Setters for necessary attributes
     */
    public String getPolicyHolderID() {
        return policyHolderID;
    }
    public void setPolicyHolderID(String policyHolderID) {
        this.policyHolderID = policyHolderID;
    }

    /**
     * Returns a string representation of the Dependant object.
     *
     * @return a string representation of the Dependant object
     */
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