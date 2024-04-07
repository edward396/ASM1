/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package Classes;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Policy Holder in the Insurance Claim Management System.
 */
public class PolicyHolder extends Customer {
    private Set<String> dependantIDs;

    /**
     * Constructor to instantiate a new Policy Holder.
     *
     * @param customerID    the customer ID
     * @param fullName      the full name of the policy holder
     * @param insuranceCard the insurance card associated with the policy holder
     */
    public PolicyHolder(String customerID, String fullName, InsuranceCard insuranceCard) {
        super(customerID, fullName, insuranceCard);
        this.dependantIDs = new HashSet<>();  // Initialize the dependantIDs set
    }

    /**
     * Constructor to instantiate a new Policy Holder with dependant IDs.
     *
     * @param customerID    the customer ID
     * @param fullName      the full name of the policy holder
     * @param insuranceCard the insurance card associated with the policy holder
     * @param dependantIDs  the set of dependant IDs associated with the policy holder
     */
    public PolicyHolder(String customerID, String fullName, InsuranceCard insuranceCard, Set<String> dependantIDs) {
        super(customerID, fullName, insuranceCard);
        this.dependantIDs = dependantIDs != null ? dependantIDs : new HashSet<>();
    }

    /**
     * Getters and Setters for necessary attributes
     */
    public Set<String> getDependantIDs() {
        return dependantIDs;
    }

    public void setDependantIDs(Set<String> dependantIDs) {
        this.dependantIDs = dependantIDs;
    }

    /**
     * Returns a string representation of the PolicyHolder object.
     *
     * @return a string representation of the PolicyHolder object
     */
    @Override
    public String toString() {
        return "PolicyHolder{" +
                "customerID='" + getCustomerID() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", insuranceCard=" + getInsuranceCard() +
                ", dependantIDs=" + (dependantIDs != null ? String.join(", ", dependantIDs) : "") +
                '}';
    }
}