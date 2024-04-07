/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * The type Claim.
 */
public class Claim {
    private String claimID;
    private Date claimDate;
    private String customerID;
    private String cardNumber;
    private Date examDate;
    private List<String> documents;
    private double amount;
    private String status;
    private String bankName;
    private String accountOwner;
    private String accountNumber;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    private Claim(Builder builder) {
        this.claimID = builder.claimID;
        this.claimDate = builder.claimDate;
        this.customerID = builder.customerID;
        this.cardNumber = builder.cardNumber;
        this.examDate = builder.examDate;
        this.documents = builder.documents;
        this.amount = builder.amount;
        this.status = builder.status;
        this.bankName = builder.bankName;
        this.accountOwner = builder.accountOwner;
        this.accountNumber = builder.accountNumber;
    }

    /**
     * Gets claim id.
     *
     * @return the claim id
     */
    public String getClaimID() {
        return claimID;
    }

    /**
     * Gets customer id.
     *
     * @return the customer id
     */
    public String getCustomerID() {
        return customerID;
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
     * Gets claim date.
     *
     * @return the claim date
     */
    public Date getClaimDate() {
        return claimDate;
    }

    /**
     * Gets exam date.
     *
     * @return the exam date
     */
    public Date getExamDate() {
        return examDate;
    }

    /**
     * Gets documents.
     *
     * @return the documents
     */
    public List<String> getDocuments() {
        return documents;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Gets bank name.
     *
     * @return the bank name
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * Gets account owner.
     *
     * @return the account owner
     */
    public String getAccountOwner() {
        return accountOwner;
    }

    /**
     * Gets account number.
     *
     * @return the account number
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * The type Builder.
     */
    public static class Builder {
        private String claimID;
        private Date claimDate;
        private String customerID;
        private String cardNumber;
        private Date examDate;
        private List<String> documents;
        private double amount;
        private String status;
        private String bankName;
        private String accountOwner;
        private String accountNumber;

        /**
         * Instantiates a new Builder.
         */
        public Builder() {}

        /**
         * Claim id builder.
         *
         * @param claimID the claim id
         * @return the builder
         */
        public Builder claimID(String claimID) {
            this.claimID = claimID;
            return this;
        }

        /**
         * Claim date builder.
         *
         * @param claimDate the claim date
         * @return the builder
         */
        public Builder claimDate(Date claimDate) {
            this.claimDate = claimDate;
            return this;
        }

        /**
         * Customer id builder.
         *
         * @param customerID the customer id
         * @return the builder
         */
        public Builder customerID(String customerID) {
            this.customerID = customerID;
            return this;
        }

        /**
         * Card number builder.
         *
         * @param cardNumber the card number
         * @return the builder
         */
        public Builder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        /**
         * Exam date builder.
         *
         * @param examDate the exam date
         * @return the builder
         */
        public Builder examDate(Date examDate) {
            this.examDate = examDate;
            return this;
        }

        /**
         * Documents builder.
         *
         * @param documents the documents
         * @return the builder
         */
        public Builder documents(List<String> documents) {
            this.documents = documents;
            return this;
        }

        /**
         * Amount builder.
         *
         * @param amount the amount
         * @return the builder
         */
        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        /**
         * Status builder.
         *
         * @param status the status
         * @return the builder
         */
        public Builder status(String status) {
            this.status = status;
            return this;
        }

        /**
         * Bank name builder.
         *
         * @param bankName the bank name
         * @return the builder
         */
        public Builder bankName(String bankName) {
            this.bankName = bankName;
            return this;
        }

        /**
         * Account owner builder.
         *
         * @param accountOwner the account owner
         * @return the builder
         */
        public Builder accountOwner(String accountOwner) {
            this.accountOwner = accountOwner;
            return this;
        }

        /**
         * Account number builder.
         *
         * @param accountNumber the account number
         * @return the builder
         */
        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        /**
         * Build claim.
         *
         * @return the claim
         */
        public Claim build() {
            return new Claim(this);
        }
    }

    @Override
    public String toString() {
        return "{claimID='" + claimID + '\'' +
                ", claimDate=" + (claimDate != null ? dateFormat.format(claimDate) : "No date") +
                ", customerID='" + customerID + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", examDate=" + (examDate != null ? dateFormat.format(examDate) : "No date") +
                ", documents=" + documents +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountOwner='" + accountOwner + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}