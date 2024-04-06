//package Handler;
//
///**
// * @author Nguyen Vo Truong Toan
// * @sID s3979056
// * version JDK21
// */
//import ProcessManager.InsuranceCardProcessManagerImplement;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.InputMismatchException;
//import java.util.Scanner;
//
//public class InsuranceCardMenuHandler {
//    private final InsuranceCardProcessManagerImplement insuranceCardManager = new InsuranceCardProcessManagerImplement("src/File/insuranceCardData.txt");
//    private final Scanner scanner = new Scanner(System.in);
//
//    public void viewInsuranceCard(Scanner scanner) {
//        System.out.print("Enter the Policy Holder ID to view insurance card: ");
//        String policyHolderID = scanner.nextLine();
//        var card = insuranceCardManager.getOne(policyHolderID);
//        if (card != null) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            System.out.println("Insurance Card Details:");
//            System.out.println("Policy Holder ID: " + card.getPolicyHolderID());
//            System.out.println("Dependent IDs: " + String.join(", ", card.getDependentIDs()));
//            System.out.println("Issue Date: " + dateFormat.format(card.getIssueDate()));
//            System.out.println("Due Date: " + dateFormat.format(card.getDueDate()));
//        } else {
//            System.out.println("Insurance card not found for the Policy Holder ID: " + policyHolderID);
//        }
//    }
//}
