/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Classes;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String customerID;
    private String fullName;
    private String insuranceCard;
    private List<Claim> claimList;

    public Customer(String customerID, String fullName, String insuranceCard, List<Claim> claimList) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
        this.claimList = claimList;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public String getInsuranceCard() {
        return insuranceCard;
    }

    public List<Claim> getClaimList() {
        return claimList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerID='" + customerID + '\'' +
                ", fullName='" + fullName + '\'' +
                ", insuranceCard='" + insuranceCard + '\'' +
//                ", claimList=" + claimList +
                '}';
    }
}