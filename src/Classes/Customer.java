/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package Classes;

import java.util.Objects;

/**
 * The type Customer.
 */
public abstract class Customer {
    private String customerID;
    private String fullName;
    private InsuranceCard insuranceCard;

    /**
     * Instantiates a new Customer.
     *
     * @param customerID    the customer id
     * @param fullName      the full name
     * @param insuranceCard the insurance card
     */
    public Customer(String customerID, String fullName, InsuranceCard insuranceCard) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
    }

    /**
     * Gets customer id.
     *
     * @return the customer id
     */
    public String getCustomerID() {
        return customerID;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        return fullName;
    }

    /**
     * Sets full name.
     *
     * @param fullName the full name
     */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Gets insurance card.
     *
     * @return the insurance card
     */
    public InsuranceCard getInsuranceCard() {
        return insuranceCard;
    }

    /**
     * Exists boolean.
     *
     * @param customerID the customer id
     * @return the boolean
     */
    public boolean exists(String customerID) {
        return this.customerID.equals(customerID);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerID, customer.customerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerID);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", fullName='" + fullName + '\'' +
                ", insuranceCard=" + insuranceCard +
                '}';
    }
}