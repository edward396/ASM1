package ProcessManager;

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

public class CustomerProcessManagerImplement implements CustomerProcessManager {
    private List<Customer> customers = new ArrayList<>();

    public void addPolicyHolder(String id, String fullName, String insuranceCard) {
        PolicyHolder policyHolder = new PolicyHolder(id, fullName, insuranceCard, new ArrayList<>(), new ArrayList<>());
        customers.add(policyHolder);
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

    private boolean customerExists(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(customerId)) {
                return true;
            }
        }
        return false;
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

                if (customer instanceof PolicyHolder) {
                    List<String> dependentInfo = new ArrayList<>();
                    for (Dependent dependent : ((PolicyHolder) customer).getDependents()) {
                        dependentInfo.add("{Dependent ID: " + dependent.getCustomerID() +
                                ", Full Name: " + dependent.getFullName() +
                                ", Insurance Card: " + dependent.getInsuranceCard() + "}");
                    }
                    if (dependentInfo.isEmpty()) {
                        writer.println("PolicyHolder");
                    } else {
                        writer.println("PolicyHolder, " + String.join(", ", dependentInfo));
                    }
                } else if (customer instanceof Dependent) {
                    writer.println("Dependent");
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
                ArrayList<Dependent> dependents = new ArrayList<>();

                if (parts[3].trim().equals("PolicyHolder")) {
                    if (parts.length > 4 && !parts[4].trim().equals("None")) {
                        String depInfo = parts[4].trim().substring(13, parts[4].trim().length() - 1);
                        String[] depData = depInfo.split(", ");

                        for (String info : depData) {
                            String[] details = info.split(":");
                            String depId = details[1].trim();
                            String depName = details[3].trim();
                            String depCard = details[5].trim();

                            Dependent dependent = new Dependent(depId, depName, depCard, new ArrayList<>());
                            dependents.add(dependent);
                        }
                    }

                    Customer customer = new PolicyHolder(id, fullName, insuranceCard, new ArrayList<>(), dependents);
                    customers.add(customer);

                } else if (parts[3].trim().equals("Dependent")) {
                    Customer customer = new Dependent(id, fullName, insuranceCard, new ArrayList<>());
                    customers.add(customer);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}