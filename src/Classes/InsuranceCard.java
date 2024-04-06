package Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class InsuranceCard {
    private String cardNumber;
    private String cardHolderID; // Added
    private String policyOwner; // Added
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

    public String getCardHolderID() { // Added
        return cardHolderID;
    }

    public void setCardHolderID(String cardHolderID) { // Added
        this.cardHolderID = cardHolderID;
    }

    public String getPolicyOwner() { // Added
        return policyOwner;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public List<Dependent> getDependents() {
        return new ArrayList<>(dependents);
    }

    public void addDependent(Dependent dependent) {
        dependents.add(dependent);
    }

    public void removeDependent(Dependent dependent) {
        dependents.remove(dependent);
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