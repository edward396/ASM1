package Classes;

import java.io.*;
import java.util.*;

public class FileHandler {

    public static Map<String, Customer> readCustomers(String filename, Map<String, InsuranceCard> insuranceCards) {
        Map<String, Customer> customers = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String fullName = parts[1];
                String cardNumber = parts[2];

                customers.put(id, new Customer(id, fullName, insuranceCards.get(cardNumber)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return customers;
    }

    public static Map<String, InsuranceCard> readInsuranceCards(String filename) {
        Map<String, InsuranceCard> insuranceCards = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String cardNumber = parts[0];
                String cardHolder = parts[1];
                String policyOwner = parts[2];
                String expirationDate = parts[3];

                insuranceCards.put(cardNumber, new InsuranceCard(cardNumber, cardHolder, policyOwner, expirationDate));
            }
        } catch (IOException | Exception e) {
            e.printStackTrace();
        }

        return insuranceCards;
    }

    public static List<Claim> readClaims(String filename) {
        List<Claim> claims = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String id = parts[0];
                String claimDate = parts[1];
                String insuredPerson = parts[2];
                String cardNumber = parts[3];
                String examDate = parts[4];
                List<String> documents = Arrays.asList(parts[5].split("\\|"));
                int amount = Integer.parseInt(parts[6]);
                String status = parts[7];
                String bankingInfo = parts[8];

                claims.add(new Claim(id, claimDate, insuredPerson, cardNumber, examDate, documents, amount, status, bankingInfo));
            }
        } catch (IOException | Exception e) {
            e.printStackTrace();
        }

        return claims;
    }

    public static void writeCustomers(String filename, Map<String, Customer> customers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Customer customer : customers.values()) {
                bw.write(customer.getId() + "," + customer.getFullName() + "," + customer.getInsuranceCard().getCardNumber());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeInsuranceCards(String filename, Map<String, InsuranceCard> insuranceCards) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (InsuranceCard card : insuranceCards.values()) {
                bw.write(card.getCardNumber() + "," + card.getCardHolder() + "," + card.getPolicyOwner() + "," + card.getExpirationDate());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeClaims(String filename, List<Claim> claims)
