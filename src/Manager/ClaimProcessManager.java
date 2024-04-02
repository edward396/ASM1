/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Manager;

import Classes.Claim;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

    public class ClaimProcessManager implements ClaimProcess {
        private List<Claim> claims = new ArrayList<>();

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
        public void delete(String claimId) {
            claims.removeIf(claim -> claim.getClaimID().equals(claimId));
        }

        @Override
        public Claim getOne(String claimId) {
            return claims.stream()
                    .filter(claim -> claim.getClaimID().equals(claimId))
                    .findFirst()
                    .orElse(null);
        }

        @Override
        public List<Claim> getAll() {
            return new ArrayList<>(claims);
        }

        @Override
        public void saveToFile(String fileName) {
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                for (Claim claim : claims) {
                    writer.println("Claim ID: " + claim.getClaimID());
                    writer.println("Claim Date: " + dateFormat.format(claim.getClaimDate()));
                    writer.println("Insured Person: " + claim.getInsuredPerson());
                    writer.println("Card Number: " + claim.getCardNumber());
                    writer.println("Exam Date: " + dateFormat.format(claim.getExamDate()));
                    writer.println("Documents: " + String.join(",", claim.getDocuments()));
                    writer.println("Claim Amount: " + claim.getAmount());
                    writer.println("Status: " + claim.getStatus());
                    writer.println("Receiver Banking Info: " + claim.getReceiverBankingInfo());
                    writer.println("-----");  // Separator between claims
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        @Override
        public void loadFromFile(String fileName) {
            claims.clear();  // Clear existing claims before loading from file
            try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
                String line;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(": ", 2);  // Split into two parts
                    if (parts.length > 1) {
                        String value = parts[1].trim();

                        String claimID = value;
                        Date claimDate = dateFormat.parse(reader.readLine().split(": ")[1].trim());
                        String insuredPerson = reader.readLine().split(": ")[1].trim();
                        String cardNumber = reader.readLine().split(": ")[1].trim();
                        Date examDate = dateFormat.parse(reader.readLine().split(": ")[1].trim());
                        List<String> documents = Arrays.asList(reader.readLine().split(": ")[1].trim().split(","));
                        double amount = Double.parseDouble(reader.readLine().split(": ")[1].trim());
                        String status = reader.readLine().split(": ")[1].trim();
                        String receiverBankingInfo = reader.readLine().split(": ")[1].trim();

                        Claim claim = new Claim(claimID, claimDate, insuredPerson, cardNumber, examDate, documents, amount, status, receiverBankingInfo);
                        claims.add(claim);

                        // Read separator between claims
                        reader.readLine();
                    }
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }
