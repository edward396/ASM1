/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */
package ClaimManager;

import Classes.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ClaimProcessManagerImplement implements ClaimProcessManager {
    private List<Claim> claims;
    private List<Customer> customers;
    private String filename;

    public ClaimProcessManagerImplement(String filename) throws Exception {
        this.filename = filename;
        this.claims = new ArrayList<>();
        this.customers = new ArrayList<>();
        loadFromFile(filename);
    }

    @Override
    public void add(Claim claim) {
        claims.add(claim);
    }

    @Override
    public void update(Claim claim) {
        for (int i = 0; i < claims.size(); i++) {
            if (claims.get(i).getClaimID().equals(claim.getClaimID())) {
                claims.set(i, claim);
                break;
            }
        }
    }

    @Override
    public void delete(java.lang.String claimId) {
        claims.removeIf(claim -> claim.getClaimID().equals(claimId));
    }

    @Override
    public Claim getOne(java.lang.String claimID) {
        for (Claim claim : claims) {
            if (claim.getClaimID().equals(claimID)) {
                return claim;
            }
        }
        return null;
    }

    @Override
    public List<Claim> getAll() {
        return claims;
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
            System.out.println("Error reading file: " + e.getMessage());
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
                        claims.add(claim);
                    } catch (ParseException | NumberFormatException e) {
                        System.out.println("Error parsing line: " + line + ". Error: " + e.getMessage());
                    }
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

