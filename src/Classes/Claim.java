/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Claim {
    private String claimID;
    private Date claimDate;
    private String insuredPerson;
    private String cardNumber;
    private Date examDate;
    private List<String> documents;
    private double amount;
    private String status;
    private String bankName;
    private String accountOwner;
    private String accountNumber;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    public Claim(){
    }

    private Claim(Builder builder) {
        this.claimID = builder.claimID;
        this.claimDate = builder.claimDate;
        this.insuredPerson = builder.insuredPerson;
        this.cardNumber = builder.cardNumber;
        this.examDate = builder.examDate;
        this.documents = builder.documents;
        this.amount = builder.amount;
        this.status = builder.status;
        this.bankName = builder.bankName;
        this.accountOwner = builder.accountOwner;
        this.accountNumber = builder.accountNumber;
    }

    public String getClaimID() {
        return claimID;
    }

    public String getInsuredPerson() {
        return insuredPerson;
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

    public static class Builder {
        private String claimID;
        private Date claimDate;
        private String insuredPerson;
        private String cardNumber;
        private Date examDate;
        private List<String> documents;
        private double amount;
        private String status;
        private String bankName;
        private String accountOwner;
        private String accountNumber;

        public Builder claimID(String claimID) {
            this.claimID = claimID;
            return this;
        }

        public Builder claimDate(Date claimDate) {
            this.claimDate = claimDate;
            return this;
        }

        public Builder insuredPerson(String insuredPerson) {
            this.insuredPerson = insuredPerson;
            return this;
        }

        public Builder cardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
            return this;
        }

        public Builder examDate(Date examDate) {
            this.examDate = examDate;
            return this;
        }

        public Builder documents(List<String> documents) {
            this.documents = documents;
            return this;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder status(String status) {
            this.status = status;
            return this;
        }

        public Builder bankName(String bankName) {
            this.bankName = bankName;
            return this;
        }

        public Builder accountOwner(String accountOwner) {
            this.accountOwner = accountOwner;
            return this;
        }

        public Builder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public Claim build() {
            return new Claim(this);
        }
    }

    @Override
    public String toString() {
        return "{claimID='" + claimID + '\'' +
                ", claimDate=" + (dateFormat.format(claimDate) == null ? dateFormat.format(claimDate) : "no date") +
                ", insuredPerson='" + insuredPerson + '\'' +
                ", cardNumber='" + cardNumber + '\'' +
                ", examDate=" + dateFormat.format(examDate) + //same
                ", documents=" + documents +
                ", amount=" + amount +
                ", status='" + status + '\'' +
                ", bankName='" + bankName + '\'' +
                ", accountOwner='" + accountOwner + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
