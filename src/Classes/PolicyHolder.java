/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Classes;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Policy holder.
 */
public class PolicyHolder extends Customer {
    private Set<String> dependantIDs;

    /**
     * Instantiates a new Policy holder.
     *
     * @param customerID    the customer id
     * @param fullName      the full name
     * @param insuranceCard the insurance card
     */
    public PolicyHolder(String customerID, String fullName, InsuranceCard insuranceCard) {
        super(customerID, fullName, insuranceCard);
        this.dependantIDs = new HashSet<>();  // Initialize the dependantIDs set
    }

    /**
     * Instantiates a new Policy holder.
     *
     * @param customerID    the customer id
     * @param fullName      the full name
     * @param insuranceCard the insurance card
     * @param dependantIDs  the dependant i ds
     */
    public PolicyHolder(String customerID, String fullName, InsuranceCard insuranceCard, Set<String> dependantIDs) {
        super(customerID, fullName, insuranceCard);
        this.dependantIDs = dependantIDs != null ? dependantIDs : new HashSet<>();
    }

    /**
     * Gets dependant i ds.
     *
     * @return the dependant i ds
     */
    public Set<String> getDependantIDs() {
        return dependantIDs;
    }

    /**
     * Sets dependant i ds.
     *
     * @param dependantIDs the dependant i ds
     */
    public void setDependantIDs(Set<String> dependantIDs) {
        this.dependantIDs = dependantIDs;
    }

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