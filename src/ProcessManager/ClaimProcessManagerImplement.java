package ProcessManager;

import Classes.Claim;
import Classes.Dependent;
import Classes.InsuranceCard;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ClaimProcessManagerImplement implements ClaimProcessManager {
    private List<Claim> claims;
    private String filename;

    public ClaimProcessManagerImplement(String filename) {
        this.filename = filename;
        this.claims = new ArrayList<>();
        try {
            loadFromFile(filename);
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void add(Claim claim) {
        try {
            if (getOne(claim.getClaimID()) != null) {
                throw new Exception("Claim ID already exists. Please enter a unique Claim ID.");
            }
            claims.add(claim);
            saveToFile(filename);
        } catch (Exception e) {
            System.out.println("Error adding claim: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void update(Claim claim) {
        try {
            boolean updated = false;
            for (int i = 0; i < claims.size(); i++) {
                if (claims.get(i).getClaimID().equals(claim.getClaimID())) {
                    claims.set(i, claim);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                throw new Exception("Claim with ID " + claim.getClaimID() + " not found.");
            }
            saveToFile(filename);
        } catch (Exception e) {
            System.out.println("Error updating claim: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String claimId) {
        try {
            boolean removed = claims.removeIf(claim -> claim.getClaimID().equals(claimId));
            if (!removed) {
                throw new Exception("Claim with ID " + claimId + " not found.");
            }
            saveToFile(filename);
        } catch (Exception e) {
            System.out.println("Error deleting claim: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Claim getOne(String claimID) {
        for (Claim claim : claims) {
            if (claim.getClaimID().equals(claimID)) {
                return claim;
            }
        }
        return null;
    }

    @Override
    public List<Claim> getAll() {
        return new ArrayList<>(claims);
    }

    @Override
    public List<Claim> getAllClaimsByCustomerID(String customerId) {
        List<Claim> customerClaims = new ArrayList<>();
        for (Claim claim : claims) {
            if (claim.getCustomerID().equals(customerId)) {
                customerClaims.add(claim);
            }
        }
        return customerClaims;
    }

    @Override
    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            for (Claim claim : claims) {
                writer.print(claim.getClaimID() + ", ");
                writer.print(dateFormat.format(claim.getClaimDate()) + ", ");
                writer.print(claim.getCustomerID() + ", ");
                writer.print(claim.getCardNumber() + ", ");
                writer.print(dateFormat.format(claim.getExamDate()) + ", ");
                writer.print(String.join("_", claim.getDocuments()) + ", ");
                writer.print(claim.getAmount() + ", ");
                writer.print(claim.getStatus() + ", ");
                writer.print(claim.getBankName() + ", ");
                writer.print(claim.getAccountOwner() + ", ");
                writer.print(claim.getAccountNumber());
                writer.println();
            }
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");

                if (parts.length == 11) {
                    try {
                        String claimID = parts[0].trim();
                        Date claimDate = dateFormat.parse(parts[1].trim());
                        String insuredPerson = parts[2].trim();
                        String cardNumber = parts[3].trim();
                        Date examDate = dateFormat.parse(parts[4].trim());
                        List<String> documents = Arrays.asList(parts[5].trim().split("_"));
                        double amount = Double.parseDouble(parts[6].trim());
                        String status = parts[7].trim();
                        String bankName = parts[8].trim();
                        String accountOwner = parts[9].trim();
                        String accountNumber = parts[10].trim();

                        Claim claim = new Claim.Builder()
                                .claimID(claimID)
                                .claimDate(claimDate)
                                .customerID(insuredPerson)
                                .cardNumber(cardNumber)
                                .examDate(examDate)
                                .documents(documents)
                                .amount(amount)
                                .status(status)
                                .bankName(bankName)
                                .accountOwner(accountOwner)
                                .accountNumber(accountNumber)
                                .build();

                        claims.add(claim);

                    } catch (ParseException | NumberFormatException e) {
                        throw new RuntimeException("Error parsing line: " + line + ". Error: " + e.getMessage(), e);
                    }
                } else if (parts.length == 4) {
                    try {
                        String dependentID = parts[0].trim();
                        String dependentName = parts[1].trim();
                        String cardNumber = parts[2].trim();  // This is the insurance card number
                        String policyHolderID = parts[3].trim();

                        InsuranceCard insuranceCard = new InsuranceCard(cardNumber);
                        Dependent dependent = new Dependent(dependentID, dependentName, insuranceCard, policyHolderID);
                        // Add dependent to a list or another data structure
                        // For now, let's just print it
                        System.out.println(dependent);

                    } catch (NumberFormatException e) {
                        throw new RuntimeException("Error parsing line: " + line + ". Error: " + e.getMessage(), e);
                    }
                } else {
                    throw new RuntimeException("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
    }
}