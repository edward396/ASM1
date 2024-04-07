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
 * Represents a claim in the Insurance Claim Management System.
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

    /**
     * Private constructor to instantiate a new Claim using the Builder pattern.
     *
     * @param builder the builder object
     */
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
     * Getters and Setters for necessary attributes
     */
    public String getClaimID() {
        return claimID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public Date getExamDate() {
        return examDate;
    }

    public List<String> getDocuments() {
        return documents;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getBankName() {
        return bankName;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

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
         * Default constructor for the Builder class.
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

    /**
     * Returns a string representation of the Claim object.
     *
     * @return a string representation of the Claim object
     */
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