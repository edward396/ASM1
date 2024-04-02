package Classes;

public class BankingInfo {
    private String bankName;
    private String bankNumber;
    private String bankCode;

    public BankingInfo(String bankName, String bankNumber, String bankCode) {
        this.bankName = bankName;
        this.bankNumber = bankNumber;
        this.bankCode = bankCode;
    }

    // Getters and Setters

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public void setBankNumber(String bankNumber) {
        this.bankNumber = bankNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    @Override
    public String toString() {
        return "Banking Info: {" + "\n" +
                "Bank Name: " + bankName +
                ", Bank Number:'" + bankNumber +
                ", BankCode: " + bankCode +
                '}';
    }
}
