//package ProcessManager;
//
//import Classes.InsuranceCard;
//
//import java.io.*;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class InsuranceCardProcessManagerImplement implements InsuranceCardProcessManager {
//    private List<InsuranceCard> insuranceCards;
//    private String filename;
//
//    public InsuranceCardProcessManagerImplement(String filename) {
//        this.filename = filename;
//        this.insuranceCards = new ArrayList<>();
//        try {
//            loadFromFile(filename);
//        } catch (Exception e) {
//            System.out.println("Error loading file: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void add(InsuranceCard insuranceCard) {
//        insuranceCards.add(insuranceCard);
//        saveToFile(filename);
//    }
//
//    @Override
//    public InsuranceCard getOne(String policyHolderID) {
//        for (InsuranceCard card : insuranceCards) {
//            if (card.getPolicyHolderID().equals(policyHolderID)) {
//                return card;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<InsuranceCard> getAll() {
//        return new ArrayList<>(insuranceCards);
//    }
//
//    @Override
//    public void saveToFile(String filename) {
//        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            for (InsuranceCard card : insuranceCards) {
//                writer.println(card.getPolicyHolderID() + ", " +
//                        String.join("_", card.getDependentIDs()) + ", " +
//                        dateFormat.format(card.getIssueDate()) + ", " +
//                        dateFormat.format(card.getDueDate()));
//            }
//        } catch (IOException e) {
//            System.out.println("Error saving file: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void loadFromFile(String filename) {
//        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
//            String line;
//            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//            while ((line = reader.readLine()) != null) {
//                String[] parts = line.split(", ");
//                if (parts.length == 4) {
//                    String policyHolderID = parts[0].trim();
//                    Set<String> dependentIDs = new HashSet<>(List.of(parts[1].trim().split("_")));
//                    Date issueDate = dateFormat.parse(parts[2].trim());
//                    Date dueDate = dateFormat.parse(parts[3].trim());
//
//                    InsuranceCard card = new InsuranceCard(policyHolderID, issueDate, dueDate);
//                    card.getDependentIDs().addAll(dependentIDs);
//                    insuranceCards.add(card);
//                } else {
//                    throw new RuntimeException("Invalid line format in insurance card file: " + line);
//                }
//            }
//        } catch (IOException | ParseException e) {
//            System.out.println("Error reading file: " + e.getMessage());
//            e.printStackTrace();
//        }
//    }
//}