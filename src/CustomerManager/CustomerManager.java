package CustomerManager;

/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

import Classes.Customer;
import Classes.Dependent;
import Classes.PolicyHolder;
import Classes.Claim;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    private List<Customer> customers;
    private String filename;

    public CustomerManager(String filename) throws IOException {
        this.filename = filename;
        this.customers = new ArrayList<>();
        loadFromFile(filename);
    }

    public void add(Customer customer) {
        customers.add(customer);
    }

    public void delete(String customerID) {
        customers.removeIf(customer -> customer.getCustomerID().equals(customerID));
    }

    public Customer getOne(String customerID) {
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(customerID)) {
                return customer;
            }
        }
        return null;
    }

    public List<Customer> getAll() {
        return customers;
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Customer customer : customers) {
                writer.print(customer.getCustomerID() + ", ");
                writer.print(customer.getFullName() + ", ");
                writer.print(customer.getInsuranceCard() + ", ");

                List<String> claimIDs = new ArrayList<>();
                for (Claim claim : customer.getClaimList()) {
                    claimIDs.add(claim.getClaimID());
                }
                writer.print(String.join("_", claimIDs) + ", ");

                if (customer instanceof PolicyHolder) {
                    writer.print("PolicyHolder, ");
                    writer.println(String.join("_", ((PolicyHolder) customer).getDependents().toString()));
                } else if (customer instanceof Dependent) {
                    writer.print("Dependent, ");
                    writer.println("None");
                } else {
                    writer.println("None");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");

                String id = parts[0].trim();
                String fullName = parts[1].trim();
                String insuranceCard = parts[2].trim();
                ArrayList<Claim> claims = new ArrayList<>();
                ArrayList<Dependent> dependents = new ArrayList<>();

                String[] claimIDs = parts[3].trim().split("_");
                for (String claimId : claimIDs) {
                    Claim claim = new Claim.Builder().claimID(claimId).build();
                    claims.add(claim);
                }

                if ("PolicyHolder".equals(parts[4].trim())) {
                    String[] depParts = parts[5].trim().split("_");
                    for (String depId : depParts) {
                        Dependent dependent = (Dependent) getOne(depId);
                        if (dependent != null) {
                            dependents.add(dependent);
                        }
                    }
                }

                Customer customer = null;

                if ("PolicyHolder".equals(parts[4].trim())) {
                    customer = new PolicyHolder(id, fullName, insuranceCard, claims, insuranceCard, dependents);
                } else if ("Dependent".equals(parts[4].trim())) {
                    customer = new Dependent(id, fullName, insuranceCard, claims);
                }

                customers.add(customer);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}