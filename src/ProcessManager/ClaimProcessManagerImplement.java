package ProcessManager;

import Classes.Claim;

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
        }
    }

    @Override
    public void add(Claim claim) {
        try {
            if (getOne(claim.getClaimID()) != null) {
                System.out.println("Claim ID already exists. Please enter a unique Claim ID.");
                return;
            }
            claims.add(claim);
            saveToFile(filename);
        } catch (Exception e) {
            System.out.println("Error adding claim: " + e.getMessage());
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
                System.out.println("Claim with ID " + claim.getClaimID() + " not found.");
                return;
            }
            saveToFile(filename);
        } catch (Exception e) {
            System.out.println("Error updating claim: " + e.getMessage());
        }
    }

    @Override
    public void delete(String claimId) {
        try {
            boolean removed = claims.removeIf(claim -> claim.getClaimID().equals(claimId));
            if (!removed) {
                System.out.println("Claim with ID " + claimId + " not found.");
                return;
            }
            saveToFile(filename);
        } catch (Exception e) {
            System.out.println("Error deleting claim: " + e.getMessage());
        }
    }

    @Override
    public Claim getOne(String claimID) {
        try {
            for (Claim claim : claims) {
                if (claim.getClaimID().equals(claimID)) {
                    return claim;
                }
            }
            System.out.println("Claim with ID " + claimID + " not found.");
            return null;
        } catch (Exception e) {
            System.out.println("Error fetching claim: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Claim> getAll() {
        try {
            return new ArrayList<>(claims);
        } catch (Exception e) {
            System.out.println("Error fetching all claims: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            for (Claim claim : claims) {
                writer.print(claim.getClaimID() + ", ");
                writer.print(dateFormat.format(claim.getClaimDate()) + ", ");
                writer.print(claim.getInsuredPerson() + ", ");
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
        }
    }

    @Override
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
                                .insuredPerson(insuredPerson)
                                .cardNumber(cardNumber)
                                .examDate(examDate)
                                .documents(documents)
                                .amount(amount)
                                .status(status)
                                .bankName(bankName)
                                .accountOwner(accountOwner)
                                .accountNumber(accountNumber)
                                .build();

                        add(claim);

                    } catch (ParseException | NumberFormatException e) {
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