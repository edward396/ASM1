/**
 * @author <Nguyen Vo Truong Toan - s3979056>
 */
package Manager;

import Classes.BankingInfo;
import Classes.Claim;
import Classes.Customer;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

    public class ClaimProcessManager implements ClaimProcess {
        private List<Claim> claims = new ArrayList<>();
        private List<Customer> customers = new ArrayList<>();

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
            try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
                for (Claim claim : claims) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    writer.println(claim.getClaimID());
                    writer.println(dateFormat.format(claim.getClaimDate()));
                    writer.println(claim.getInsuredPerson().getCustomerID());
                    writer.println(claim.getCardNumber());
                    writer.println(dateFormat.format(claim.getExamDate()));
                    writer.println(String.join(",", claim.getDocuments()));
                    writer.println(claim.getAmount());
                    writer.println(claim.getStatus());
                    writer.println(claim.getReceiverBankingInfo());
                    writer.println("------------------------------------------------------");
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

                        line = reader.readLine();
                        Date claimDate = dateFormat.parse(line.split(": ")[1].trim());

                        line = reader.readLine();
                        String insuredPersonID = line.split(": ")[1].trim();
                        Customer insuredPerson = getCustomer(insuredPersonID);

                        line = reader.readLine();
                        String cardNumber = line.split(": ")[1].trim();

                        line = reader.readLine();
                        Date examDate = dateFormat.parse(line.split(": ")[1].trim());

                        line = reader.readLine();
                        List<String> documents = Arrays.asList(line.split(": ")[1].trim().split("_"));

                        line = reader.readLine();
                        double amount = Double.parseDouble(line.split(": ")[1].trim());

                        line = reader.readLine();
                        String status = line.split(": ")[1].trim();

                        line = reader.readLine();
                        String[] bankingInfoArray = line.split(": ")[1].trim().split(" - ");
                        BankingInfo receiverBankingInfo = new BankingInfo(bankingInfoArray[0], bankingInfoArray[1], bankingInfoArray[2]);

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

        public void addCustomer(Customer customer) {
            customers.add(customer);
        }

        private Customer getCustomer(String customerID) {
            List<Customer> allCustomers = getAllCustomers();
            for (Customer customer : allCustomers) {
                if (customer.getCustomerID().equals(customerID)) {
                    return customer;
                }
            }
            return null;
        }

        public List<Customer> getAllCustomers() {
            return customers;
        }
    }
