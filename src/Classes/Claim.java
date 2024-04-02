/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Classes;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Claim {
    private String claimID;
    private Date claimDate;
    private Customer insuredPerson; //not unique, using ID, store customer objects
    private String cardNumber;
    private Date examDate;
    private List<String> documents; //claimid_cardNumber_docname.pdf (one string)
    private double amount;
    private String status; //extend use enum
    private BankingInfo receiverBankingInfo; //3 attributes, make BankingInfo class

    public Claim() {
    }

    public Claim(String claimID, Date claimDate, Customer insuredPerson, String cardNumber, Date examDate,
                 List<String> documents, double amount, String status, BankingInfo receiverBankingInfo) {
        this.claimID = claimID;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.documents = documents;
        this.amount = amount;
        this.status = status;
        this.receiverBankingInfo = receiverBankingInfo;
    }

    //Getters
    public String getClaimID() {
        return claimID;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public Customer getInsuredPerson() {
        return insuredPerson;
    }

    public String getCardNumber() {
        return cardNumber;
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

    public BankingInfo getReceiverBankingInfo() {
        return receiverBankingInfo;
    }

    //Setters
    public void setClaimID(String claimID) {
        this.claimID = claimID;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public void setInsuredPerson(Customer insuredPerson) {
        this.insuredPerson = insuredPerson;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReceiverBankingInfo(BankingInfo receiverBankingInfo) {
        this.receiverBankingInfo = receiverBankingInfo;
    }

    public void setDocuments(List<String> documents) {
        this.documents = documents;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return "Clam Info:" + "\n" +
                "{Claim ID: " + claimID + ", " +
                "Claim Date: " + dateFormat.format(claimDate) + ", " +
                "Insured Person: " + insuredPerson + ", " +
                "Card Number: " + cardNumber + ", " +
                "Exam Date: " + dateFormat.format(examDate) + ", " +
                "Documents: " + documents + ", " +
                "Amount: " + amount + ", " +
                "Status: " + status + ", " +
                "Receiver Banking Info: " + receiverBankingInfo + "}";
    }
}
