package CustomerManager;

/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

import Classes.Claim;
import Classes.Customer;
import Classes.Dependent;
import Classes.PolicyHolder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager implements ICustomerManager {
    private List<Customer> customers = new ArrayList<>();


    public void addPolicyHolder(String id, String fullName, String insuranceCard, List<Claim> claims) {
        customers.add(new PolicyHolder(id, fullName, insuranceCard, claims, new ArrayList<>()));
        saveToFile("src/File/customerData.txt");
    }

    public void addDependent(String id, String fullName, String insuranceCard, String policyHolderId) {
        PolicyHolder policyHolder = (PolicyHolder) getOne(policyHolderId);

        if (policyHolder != null) {
            policyHolder.getDependents().add(new Dependent(id, fullName, insuranceCard, new ArrayList<>()));
            saveToFile("src/File/customerData.txt");
        } else {
            System.out.println("Invalid PolicyHolder ID. Please enter a valid PolicyHolder ID.");
        }
    }

    @Override
    public void delete(String id) {
        Customer existingCustomer = getOne(id);
        if (existingCustomer != null) {
            customers.remove(existingCustomer);
            saveToFile("src/File/customerData.txt");
        } else {
            System.out.println("Customer not found.");
        }
    }

    @Override
    public Customer getOne(String id) {
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return customers;
    }

    @Override
    public void saveToFile(String filename) {
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
                    List<String> dependentInfo = new ArrayList<>();
                    for (Dependent dependent : ((PolicyHolder) customer).getDependents()) {
                        dependentInfo.add("{Dependent ID:" + dependent.getCustomerID() +
                                ", Full Name:" + dependent.getFullName() +
                                ", Insurance Card:" + dependent.getInsuranceCard() + "}");
                    }
                    if (dependentInfo.isEmpty()) {
                        writer.println("PolicyHolder, None");
                    } else {
                        writer.println("PolicyHolder, " + String.join(", ", dependentInfo));
                    }
                } else if (customer instanceof Dependent) {
                    writer.println("Dependent, None");
                } else {
                    writer.println("None");
                }
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void loadFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");

                if (parts.length < 4) {
                    System.out.println("Incomplete data for customer: " + line);
                    continue;
                }

                String id = parts[0].trim();
                String fullName = parts[1].trim();
                String insuranceCard = parts[2].trim();
                ArrayList<Claim> claims = new ArrayList<>();
                ArrayList<Dependent> dependents = new ArrayList<>();

                if (parts.length > 4 && parts[4].trim().equals("PolicyHolder")) {
                    String[] claimIDs = parts[3].trim().split("_");
                    for (String claimId : claimIDs) {
                        Claim claim = new Claim.Builder().claimID(claimId).build();
                        claims.add(claim);
                    }

                    String[] depInfo = parts[5].trim().split(", ");
                    for (String info : depInfo) {
                        if (!info.equals("None")) {
                            String[] depData = info.split(": ");
                            Dependent dependent = new Dependent(depData[1], depData[3], depData[5], new ArrayList<>());
                            dependents.add(dependent);
                        }
                    }

                    Customer customer = new PolicyHolder(id, fullName, insuranceCard, claims, dependents);
                    customers.add(customer);

                } else if (parts[4].trim().equals("Dependent")) {
                    String[] claimIDs = parts[3].trim().split("_");
                    for (String claimId : claimIDs) {
                        Claim claim = new Claim.Builder().claimID(claimId).build();
                        claims.add(claim);
                    }

                    Customer customer = new Dependent(id, fullName, insuranceCard, claims);
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}