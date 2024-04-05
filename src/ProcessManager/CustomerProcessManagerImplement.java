package ProcessManager;

/**
 * @author Nguyen Vo Truong Toan
 * @sID s3979056
 * version JDK21
 */

import Classes.Customer;
import Classes.Dependent;
import Classes.InsuranceCard;
import Classes.PolicyHolder;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerProcessManagerImplement implements CustomerProcessManager {
    private List<Customer> customers = new ArrayList<>();

    private boolean isCustomerIDUnique(String id) {
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(id)) {
                return false;  // ID already exists
            }
        }
        return true;  // ID is unique
    }

    @Override
    public void addPolicyHolder(String id, String fullName, String insuranceCardNumber) throws Exception {
        if (!isCustomerIDUnique(id)) {
            throw new Exception("Customer ID already exists. Please enter a unique ID.");
        }

        InsuranceCard insuranceCard = new InsuranceCard(insuranceCardNumber);
        PolicyHolder policyHolder = new PolicyHolder(id, fullName, insuranceCard);
        customers.add(policyHolder);
        saveToFile("src/File/customerData.txt", "src/File/dependentData.txt"); // Save changes
    }

    @Override
    public void addDependent(String id, String fullName, String policyHolderId) throws Exception {
        if (!isCustomerIDUnique(id)) {
            throw new Exception("Customer ID already exists. Please enter a unique ID.");
        }

        Customer policyHolder = getOne(policyHolderId);
        if (policyHolder == null) {
            throw new Exception("Invalid PolicyHolder ID. Please enter a valid PolicyHolder ID.");
        }

        InsuranceCard insuranceCard = new InsuranceCard(policyHolder.getInsuranceCard().getCardNumber());
        Dependent dependent = new Dependent(id, fullName, insuranceCard, policyHolderId);
        customers.add(dependent);
        ((PolicyHolder) policyHolder).getDependentIDs().add(id);
        saveToFile("src/File/customerData.txt", "src/File/dependentData.txt"); // Save changes
    }

    @Override
    public void delete(String customerID) {
        Customer existingCustomer = getOne(customerID);
        if (existingCustomer != null) {
            customers.remove(existingCustomer);
            saveToFile("src/File/customerData.txt", "src/File/dependentData.txt"); // Save changes
        } else {
            throw new IllegalArgumentException("Customer not found.");
        }
    }

    @Override
    public Customer getOne(String customerID) {
        for (Customer customer : customers) {
            if (customer.getCustomerID().equals(customerID)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public boolean exists(String customerID) {
        return customers.stream().anyMatch(customer -> customer.getCustomerID().equals(customerID));
    }

    @Override
    public List<Customer> getAll() {
        return customers;
    }

    @Override
    public void saveToFile(String customerFilename, String dependentFilename) {
        try (PrintWriter customerWriter = new PrintWriter(new FileWriter(customerFilename));
             PrintWriter dependentWriter = new PrintWriter(new FileWriter(dependentFilename))) {

            for (Customer customer : customers) {
                if (customer instanceof PolicyHolder) {
                    String dependentIDs = ((PolicyHolder) customer).getDependentIDs().isEmpty() ? "null" :
                            String.join(", ", ((PolicyHolder) customer).getDependentIDs());
                    customerWriter.println(customer.getCustomerID() + ", " +
                            customer.getFullName() + ", " +
                            ((PolicyHolder) customer).getInsuranceCard().getCardNumber() + ", " +
                            "PolicyHolder, " +
                            dependentIDs);
                } else if (customer instanceof Dependent) {
                    dependentWriter.println(customer.getCustomerID() + ", " +
                            customer.getFullName() + ", " +
                            ((Dependent) customer).getInsuranceCard().getCardNumber() + ", " +
                            "Dependent, " +
                            ((Dependent) customer).getPolicyHolderID());
                }
            }

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public void loadFromFile(String customerFilename, String dependentFilename) {
        try (BufferedReader customerReader = new BufferedReader(new FileReader(customerFilename));
             BufferedReader dependentReader = new BufferedReader(new FileReader(dependentFilename))) {

            String line;
            while ((line = customerReader.readLine()) != null) {
                String[] parts = line.split(", ");
                String id = parts[0].trim();
                String fullName = parts[1].trim();
                String cardNumber = parts[2].trim();

                Set<String> dependentIDs = new HashSet<>();
                if (!parts[4].trim().equals("null")) {
                    String[] depIDs = parts[4].split(", ");
                    for (String depID : depIDs) {
                        dependentIDs.add(depID);
                    }
                }
                InsuranceCard insuranceCard = new InsuranceCard(cardNumber);
                PolicyHolder policyHolder = new PolicyHolder(id, fullName, insuranceCard, dependentIDs);
                customers.add(policyHolder);
            }

            while ((line = dependentReader.readLine()) != null) {
                String[] parts = line.split(", ");
                String id = parts[0].trim();
                String fullName = parts[1].trim();
                String cardNumber = parts[2].trim();
                String policyHolderID = parts[4].trim();

                InsuranceCard insuranceCard = new InsuranceCard(cardNumber);
                Dependent dependent = new Dependent(id, fullName, insuranceCard, policyHolderID);
                customers.add(dependent);

                PolicyHolder policyHolder = (PolicyHolder) getOne(policyHolderID);
                if (policyHolder != null) {
                    policyHolder.getDependentIDs().add(id);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}