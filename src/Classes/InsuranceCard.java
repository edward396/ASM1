/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Classes;

import java.util.Date;

public class InsuranceCard {
    private String cardNumber;
    private String cardHolderID;
    private String policyHolderID;
    private Date expirationDate;

    public InsuranceCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public InsuranceCard(String cardNumber, String cardHolderID, String policyHolderID, Date expirationDate) {
        this.cardNumber = cardNumber;
        this.cardHolderID = cardHolderID;
        this.policyHolderID = policyHolderID;
        this.expirationDate = expirationDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolderID() {
        return cardHolderID;
    }

    public void setCardHolderID(String cardHolderID) {
        this.cardHolderID = cardHolderID;
    }

    public String getPolicyHolderID() {
        return policyHolderID;
    }

    public void setPolicyHolderID(String policyHolderID) {
        this.policyHolderID = policyHolderID;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "InsuranceCard{" +
                "cardNumber='" + cardNumber + '\'' +
                '}';
    }
}




