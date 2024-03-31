/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Classes;

import java.util.ArrayList;
import java.util.List;
public abstract class Customer {
    private String customerID;
    private String fullName;
    private String insuranceCard;
    private List<Claim> claimList = new ArrayList<>();

    //Default Constructor
    public Customer(){
        this.customerID = "Default";
        this.fullName = "Default";
        this.insuranceCard = "Default";
    }

    //Constructor without List of Claims
    public Customer(String customerID, String fullName, String insuranceCard) {
        this.customerID = customerID;
        this.fullName = fullName;
        this.insuranceCard = insuranceCard;
    }

    //Getters
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

    //Setters


    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setInsuranceCard(String insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    public void setClaimList(List<Claim> claimList) {
        this.claimList = claimList;
    }
}
