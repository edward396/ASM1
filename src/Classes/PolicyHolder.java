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
    private Set<String> dependentIDs;

    /**
     * Instantiates a new Policy holder.
     *
     * @param customerID    the customer id
     * @param fullName      the full name
     * @param insuranceCard the insurance card
     */
    public PolicyHolder(String customerID, String fullName, InsuranceCard insuranceCard) {
        super(customerID, fullName, insuranceCard);
        this.dependentIDs = new HashSet<>();  // Initialize the dependentIDs set
    }

    /**
     * Instantiates a new Policy holder.
     *
     * @param customerID    the customer id
     * @param fullName      the full name
     * @param insuranceCard the insurance card
     * @param dependentIDs  the dependent i ds
     */
    public PolicyHolder(String customerID, String fullName, InsuranceCard insuranceCard, Set<String> dependentIDs) {
        super(customerID, fullName, insuranceCard);
        this.dependentIDs = dependentIDs != null ? dependentIDs : new HashSet<>();
    }

    /**
     * Gets dependent i ds.
     *
     * @return the dependent i ds
     */
    public Set<String> getDependentIDs() {
        return dependentIDs;
    }

    /**
     * Sets dependent i ds.
     *
     * @param dependentIDs the dependent i ds
     */
    public void setDependentIDs(Set<String> dependentIDs) {
        this.dependentIDs = dependentIDs;
    }

    @Override
    public String toString() {
        return "PolicyHolder{" +
                "customerID='" + getCustomerID() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", insuranceCard=" + getInsuranceCard() +
                ", dependentIDs=" + (dependentIDs != null ? String.join(", ", dependentIDs) : "") +
                '}';
    }
}