/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package Classes;

import java.util.Objects;

/**
 * Abstract class representing a Customer in the Insurance Claim Management System.
 */
public abstract class Customer {
    private String customerID;
    private String fullName;
    private InsuranceCard insuranceCard;

    /**
     * Constructor to instantiate a new Customer.
     *
     * @param customerID    the customer ID
     * @param fullName      the full name of the customer
     * @param insuranceCard the insurance card associated with the customer
     */
    public Customer(String customerID, String fullName, InsuranceCard insuranceCard) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
    }

    /**
     * Getters and Setters for necessary attributes
     */
    public String getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    /**
     * Checks if the customer with the given ID exists.
     *
     * @param customerID the customer ID to check
     * @return true if the customer exists, false otherwise
     */
    public boolean exists(String customerID) {
        return this.customerID.equals(customerID);
    }

    /**
     * Overrides the equals method to compare Customer objects based on customerID.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerID, customer.customerID);
    }

    /**
     * Overrides the hashCode method.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(customerID);
    }

    /**
     * Returns a string representation of the Customer object.
     *
     * @return a string representation of the Customer object
     */
    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", fullName='" + fullName + '\'' +
                ", insuranceCard=" + insuranceCard +
                '}';
    }
}