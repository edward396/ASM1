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
    private List<Dependent> dependents;

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
        this.dependents = new ArrayList<>();
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
     * Gets card number.
     *
     * @return the card number
     */
    public String getCardNumber() {
        return cardNumber;
    }

    /**
     * Sets card number.
     *
     * @param cardNumber the card number
     */
    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Gets card holder id.
     *
     * @return the card holder id
     */
    public String getCardHolderID() {
        return cardHolderID;
    }

    /**
     * Sets card holder id.
     *
     * @param cardHolderID the card holder id
     */
    public void setCardHolderID(String cardHolderID) {
        this.cardHolderID = cardHolderID;
    }

    /**
     * Gets policy owner.
     *
     * @return the policy owner
     */
    public String getPolicyOwner() {
        return policyOwner;
    }

    /**
     * Sets policy owner.
     *
     * @param policyOwner the policy owner
     */
    public void setPolicyOwner(String policyOwner) {
        this.policyOwner = policyOwner;
    }

    /**
     * Gets expiration date.
     *
     * @return the expiration date
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * Sets expiration date.
     *
     * @param expirationDate the expiration date
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * Add dependent.
     *
     * @param dependent the dependent
     */
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