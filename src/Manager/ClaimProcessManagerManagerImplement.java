/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Manager;

import Classes.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ClaimProcessManagerManagerImplement implements ClaimProcessManager {
    private List<Claim> claims;
    private List<Customer> customers;
    private String filename;

    public ClaimProcessManagerManagerImplement(String filename) throws Exception {
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
            java.lang.String line;
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");

                String claimID = parts[0].trim();
                Date claimDate = dateFormat.parse(parts[1].trim());
                String insuredPerson = parts[2].trim();

                String cardNumber = parts[3].trim();
                Date examDate = dateFormat.parse(parts[4].trim());

                List<String> documents = Arrays.asList(parts[5].trim().split(" "));

                double amount = Double.parseDouble(parts[6].trim());
                String status = parts[7].trim();

                String bankName = parts[8].trim();
                String accountOwner = parts[9].trim();
                String accountNumber = parts[10].trim();

                Claim claim = new Claim(claimID, claimDate, insuredPerson, cardNumber, examDate, documents, amount, status, bankName, accountOwner, accountNumber);
                claims.add(claim);
            }
        } catch (IOException | ParseException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

