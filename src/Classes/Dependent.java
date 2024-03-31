/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Classes;

public class Dependent extends Customer {

    public Dependent(String customerID, String fullName, String insuranceCard) {
        super(customerID, fullName, insuranceCard);
    }

    @Override
    public String toString() {
        return "Clam Info:" + "\n" +
                "{Dependent ID: " + getCustomerID() + ", " +
                "Full Name: " + getFullName() + ", " +
                "Insurance Card: " + getInsuranceCard() + ", " +
                "}";
    }
}
