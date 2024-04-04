/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Classes;

import java.util.ArrayList;

public class Dependent extends Customer {

    public Dependent(String customerID, String fullName, String insuranceCard, ArrayList<Claim> claimList) {
        super(customerID, fullName, insuranceCard, claimList);
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
