/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Classes;

import java.util.ArrayList;
import java.util.List;

public abstract class Customer {
    private String customerID; //c- with 7 numbers
    private String fullName;
    private String insuranceCard;
    private List<Claim> claimList;


    public Customer(String customerID, String fullName, String insuranceCard, ArrayList<Claim> claimList) {
        this.customerID = (customerID != null && !customerID.isEmpty()) ? customerID : "default";
        this.fullName = (fullName != null && !fullName.isEmpty()) ? fullName : "default";
        this.insuranceCard = (insuranceCard != null && !insuranceCard.isEmpty()) ? insuranceCard : "default";
        this.claimList = (claimList != null && !claimList.isEmpty()) ? claimList : new ArrayList<>();
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getInsuranceCard() {
        return insuranceCard;
    }

    public void setInsuranceCard(String insuranceCard) {
        this.insuranceCard = insuranceCard;
    }

    public List<Claim> getClaimList() {
        return claimList;
    }

    public void setClaimList(List<Claim> claimList) {
        this.claimList = claimList;
    }
}
