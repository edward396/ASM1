package Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class InsuranceCard {
    private String cardNumber;
    private String cardHolderID;
    private String policyOwner;
    private Date expirationDate;
    private List<Dependent> dependents;

    public InsuranceCard(String cardNumber, String cardHolderID, String policyOwner, Date expirationDate) {
        this.cardNumber = cardNumber;
        this.cardHolderID = cardHolderID;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
        this.dependents = new ArrayList<>();
    }

    public InsuranceCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public InsuranceCard(String cardNumber, String cardHolderID) {
        this.cardNumber = cardNumber;
        this.cardHolderID = cardHolderID;
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

    public String getPolicyOwner() {
        return policyOwner;
    }

    public void setPolicyOwner(String policyOwner) {
        this.policyOwner = policyOwner;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<Dependent> getDependents() {
        return dependents;
    }

    public void setDependents(List<Dependent> dependents) {
        this.dependents = dependents;
    }

    public void addDependent(Dependent dependent) {
        dependents.add(dependent);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceCard that = (InsuranceCard) o;
        return Objects.equals(cardNumber, that.cardNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }

    @Override
    public String toString() {
        return "InsuranceCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardHolder='" + cardHolderID + '\'' +
                ", policyHolder='" + policyOwner + '\'' +
                ", expirationDate=" + expirationDate +
                ", dependents=" + dependents +
                '}';
    }
}