/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Manager;

import Classes.Claim;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
            try (BufferedReader reader = new BufferedReader(new FileReader("sampleClaims.txt"))) {
                String line;
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                while ((line = reader.readLine()) != null) {
                    String claimID = line;
                    Date claimDate = dateFormat.parse(reader.readLine());
                    String insuredPerson = reader.readLine();
                    String cardNumber = reader.readLine();
                    Date examDate = dateFormat.parse(reader.readLine());
                    List<String> documents = List.of(reader.readLine().split(","));
                    double amount = Double.parseDouble(reader.readLine());
                    String status = reader.readLine();
                    String receiverBankingInfo = reader.readLine();

                    Claim claim = new Claim(claimID, claimDate, insuredPerson, cardNumber, examDate, documents, amount, status, receiverBankingInfo);
                    claims.add(claim);

                    reader.readLine();
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace();
            }
        }
    }
