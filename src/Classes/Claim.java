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
    private String insuredPerson;
    private String cardNumber;
    private Date examDate;
    private List<String> documents;
    private double amount;
    private String status;
    private String receiverBankingInfo;

    public Claim() {
    }

    public Claim(String claimID, Date claimDate, String insuredPerson, String cardNumber, Date examDate,
                 List<String> documents, double amount, String status, String receiverBankingInfo) {
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

    public String getInsuredPerson() {
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

    public String getReceiverBankingInfo() {
        return receiverBankingInfo;
    }

    //Setters
    public void setClaimID(String claimID) {
        this.claimID = claimID;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public void setInsuredPerson(String insuredPerson) {
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

    public void setReceiverBankingInfo(String receiverBankingInfo) {
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
