/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

package Classes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * The type Insurance card.
 */
public class InsuranceCard {
    private String cardNumber;
    private String cardHolderID;
    private String policyOwner;
    private Date expirationDate;
    private List<Dependant> dependants;

    /**
     * Instantiates a new Insurance card.
     *
     * @param cardNumber     the card number
     * @param cardHolderID   the card holder id
     * @param policyOwner    the policy owner
     * @param expirationDate the expiration date
     */
    public InsuranceCard(String cardNumber, String cardHolderID, String policyOwner, Date expirationDate) {
        this.cardNumber = cardNumber;
        this.cardHolderID = cardHolderID;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
        this.dependants = new ArrayList<>();
    }

    /**
     * Instantiates a new Insurance card.
     *
     * @param cardNumber the card number
     */
    public InsuranceCard(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Getters and Setters for necessary attributes
     */
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

    /**
     * Adds a dependant to the list of dependants.
     *
     * @param dependant the dependant to add
     */
    public void addDependant(Dependant dependant) {
        dependants.add(dependant);
    }

    /**
     * Overrides the equals method to compare InsuranceCard objects based on cardNumber.
     *
     * @param o the object to compare
     * @return true if the objects are equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InsuranceCard that = (InsuranceCard) o;
        return Objects.equals(cardNumber, that.cardNumber);
    }

    /**
     * Overrides the hashCode method.
     *
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(cardNumber);
    }

    /**
     * Returns a string representation of the InsuranceCard object.
     *
     * @return a string representation of the InsuranceCard object
     */
    @Override
    public String toString() {
        return "InsuranceCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", cardHolder='" + cardHolderID + '\'' +
                ", policyOwner='" + policyOwner + '\'' +
                ", expirationDate=" + expirationDate +
                ", dependants=" + dependants +
                '}';
    }
}