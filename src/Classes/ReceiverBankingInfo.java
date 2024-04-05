/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package Classes;

public class ReceiverBankingInfo {
    private String bankName;
    private String accountOwner;
    private String accountNumber;

    public ReceiverBankingInfo(String bankName, String accountOwner, String accountNumber) {
        this.bankName = bankName;
        this.accountOwner = accountOwner;
        this.accountNumber = accountNumber;
    }

    // Getters and Setters

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Banking Info: { Bank: " + bankName + ", Name: " + accountOwner + ", Number: " + accountNumber + " }";
    }
}
