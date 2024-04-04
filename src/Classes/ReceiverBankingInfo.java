    package Classes;

    public class ReceiverBankingInfo {
        private String bankName;
        private String receiverName;
        private String bankNumber;

        public ReceiverBankingInfo(String bankName, String receiverName, String bankNumber) {
            this.bankName = bankName;
            this.receiverName = receiverName;
            this.bankNumber = bankNumber;
        }

        // Getters and Setters

        public String getBankName() {
            return bankName;
        }

        public void setBankName(String bankName) {
            this.bankName = bankName;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getBankNumber() {
            return bankNumber;
        }

        public void setBankNumber(String bankNumber) {
            this.bankNumber = bankNumber;
        }

        @Override
        public java.lang.String toString() {
            return "Banking Info: { Bank: " + bankName + ", Name: " + receiverName + ", Number: " + bankNumber + " }";
        }
    }
