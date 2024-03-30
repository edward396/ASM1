/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Classes;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class Claim {
    private String claimID;
    private String claimDate;
    private String insuredPerson;
    private String cardNumber;
    private Date examDate;
    private double amount;
    private String status;
    private String receiverBankingInfo;
    private List<Claim> claimList = new ArrayList<>();

    public Claim() {
    }

    public Claim(String claimID, String claimDate, String insuredPerson, String cardNumber, Date examDate,
                 double amount, String status, String receiverBankingInfo, List<Claim> claimList) {
        this.claimID = claimID;
        this.claimDate = claimDate;
        this.insuredPerson = insuredPerson;
        this.cardNumber = cardNumber;
        this.examDate = examDate;
        this.amount = amount;
        this.status = status;
        this.receiverBankingInfo = receiverBankingInfo;
        this.claimList = claimList;
    }

    public String getClaimID() {
        return claimID;
    }

    public String getClaimDate() {
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

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }

    public String getReceiverBankingInfo() {
        return receiverBankingInfo;
    }

    public List<Claim> getClaimList() {
        return claimList;
    }

    //Setter
    public void setClaimID(String claimID) {
        this.claimID = claimID;
    }

    public void setClaimDate(String claimDate) {
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

    public void setClaimList(List<Claim> claimList) {
        this.claimList = claimList;
    }
}
