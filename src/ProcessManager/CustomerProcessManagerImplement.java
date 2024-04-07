package ProcessManager;

import Classes.Customer;
import Classes.Dependant;
import Classes.InsuranceCard;
import Classes.PolicyHolder;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

/**
 * The type Customer process manager implement.
 */
public class CustomerProcessManagerImplement implements CustomerProcessManager {
    /**
     * The constant CUSTOMER_FILE_PATH.
     */
    public static final String CUSTOMER_FILE_PATH = "src/File/policyHolderData.txt";
    /**
     * The constant DEPENDANT_FILE_PATH.
     */
    public static final String DEPENDANT_FILE_PATH = "src/File/dependantData.txt";
    private List<Customer> customers = new ArrayList<>();
    private int lastCustomerId = 0;

    /**
     * Instantiates a new Customer process manager implement.
     */
    public CustomerProcessManagerImplement() {
        try {
            loadFromFile(CUSTOMER_FILE_PATH, DEPENDANT_FILE_PATH);
        } catch (Exception e) {
            System.out.println("Error loading customers from file: " + e.getMessage());
        }
    }

    private String generateCustomerID() {
        lastCustomerId++;
        return "c-" + String.format("%07d", lastCustomerId);
    }

    private void validateCustomerID(String id) throws IllegalArgumentException {
        if (!isCustomerIDUnique(id)) {
            throw new IllegalArgumentException("Customer ID already exists. Please enter a unique ID.");
        }
    }

    private boolean isCustomerIDUnique(String id) {
        return customers.stream().noneMatch(customer -> customer.getCustomerID().equals(id));
    }

    @Override
    public void addPolicyHolder(String fullName, String insuranceCardNumber, List<String> dependentIds) {
        String id = generateCustomerID();
        validateCustomerID(id);

        if (!insuranceCardNumber.matches("\\d{10}")) {
            throw new IllegalArgumentException("Invalid insurance card number format. Please enter a 10-digit number.");
        }

        InsuranceCard insuranceCard = new InsuranceCard(insuranceCardNumber);
        PolicyHolder policyHolder = new PolicyHolder(id, fullName, insuranceCard);
        policyHolder.setDependantIDs(new HashSet<>(dependentIds));
        customers.add(policyHolder);
        saveToFile(CUSTOMER_FILE_PATH, DEPENDANT_FILE_PATH);
    }

    @Override
    public void addDependant(String fullName, String policyHolderId) throws Exception {
        String id = generateCustomerID();
        validateCustomerID(id);

        Customer policyHolder = getOne(policyHolderId);
        if (policyHolder == null || !policyHolder.exists(policyHolderId)) {
            throw new Exception("Invalid PolicyHolder ID. Please enter a valid PolicyHolder ID.");
        }

        InsuranceCard insuranceCard = new InsuranceCard(policyHolder.getInsuranceCard().getCardNumber());
        Dependant dependant = new Dependant(id, fullName, insuranceCard, policyHolderId);
        customers.add(dependant);
        ((PolicyHolder) policyHolder).getDependantIDs().add(id);
        saveToFile(CUSTOMER_FILE_PATH, DEPENDANT_FILE_PATH);
    }
    @Override
    public void remove(String customerID) {
        Customer existingCustomer = getOne(customerID);
        if (existingCustomer != null) {
            customers.remove(existingCustomer);
            saveToFile(CUSTOMER_FILE_PATH, DEPENDANT_FILE_PATH);
        } else {
            throw new IllegalArgumentException("Customer not found.");
        }
    }

    @Override
    public void updatePolicyHolder(String id, String fullName, String insuranceCardNumber) {
        PolicyHolder policyHolder = (PolicyHolder) getOne(id);
        if (policyHolder != null) {
            policyHolder.setFullName(fullName);

            if (!insuranceCardNumber.matches("\\d{10}")) {
                throw new IllegalArgumentException("Invalid insurance card number format. Please enter a 10-digit number.");
            }

            policyHolder.getInsuranceCard().setCardNumber(insuranceCardNumber);
            saveToFile(CUSTOMER_FILE_PATH, DEPENDANT_FILE_PATH);
        } else {
            throw new IllegalArgumentException("Policy Holder not found.");
        }
    }

    @Override
    public void updateDependent(String id, String fullName, String policyHolderId) {
        Dependant dependant = (Dependant) getOne(id);
        if (dependant != null) {
            dependant.setFullName(fullName);
            dependant.setPolicyHolderID(policyHolderId);
            saveToFile(CUSTOMER_FILE_PATH, DEPENDANT_FILE_PATH);
        } else {
            throw new IllegalArgumentException("Dependent not found.");
        }
    }

    @Override
    public Customer getOne(String customerID) {
        return customers.stream()
                .filter(customer -> customer.getCustomerID().equals(customerID))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean exists(String customerID) {
        return customers.stream().anyMatch(customer -> customer.getCustomerID().equals(customerID));
    }

    @Override
    public List<Customer> getAll() {
        return new ArrayList<>(customers);
    }

    @Override
    public List<Customer> getAllByType(String type) {
        return customers.stream()
                .filter(customer -> {
                    if ("PolicyHolder".equalsIgnoreCase(type) && customer instanceof PolicyHolder) {
                        return true;
                    }
                    return "Dependent".equalsIgnoreCase(type) && customer instanceof Dependant;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<Customer> getAllDependentsByPolicyHolderID(String policyHolderID) {
        Customer policyHolder = getOne(policyHolderID);
        if (policyHolder instanceof PolicyHolder) {
            List<Customer> dependents = new ArrayList<>();
            for (String dependentID : ((PolicyHolder) policyHolder).getDependantIDs()) {
                Customer dependent = getOne(dependentID);
                if (dependent instanceof Dependant) {
                    dependents.add(dependent);
                }
            }
            return dependents;
        }
        return new ArrayList<>();
    }

    @Override
    public void saveToFile(String customerFilename, String dependentFilename) {
        try (PrintWriter customerWriter = new PrintWriter(new FileWriter(customerFilename));
             PrintWriter dependentWriter = new PrintWriter(new FileWriter(dependentFilename))) {

            for (Customer customer : customers) {
                if (customer instanceof PolicyHolder) {
                    String dependentIDs = ((PolicyHolder) customer).getDependantIDs().isEmpty() ? "null" :
                            String.join(", ", ((PolicyHolder) customer).getDependantIDs());
                    customerWriter.println(customer.getCustomerID() + ", " +
                            customer.getFullName() + ", " +
                            customer.getInsuranceCard().getCardNumber() + ", " +
                            "PolicyHolder, " +
                            dependentIDs);
                } else if (customer instanceof Dependant) {
                    dependentWriter.println(customer.getCustomerID() + ", " +
                            customer.getFullName() + ", " +
                            customer.getInsuranceCard().getCardNumber() + ", " +
                            ((Dependant) customer).getPolicyHolderID());
                }
            }

        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    @Override
    public void loadFromFile(String customerFilename, String dependentFilename) throws Exception {
        try {
            customers = loadCustomersFromFile(customerFilename);
            loadDependentsFromFile(dependentFilename);
        } catch (IOException e) {
            throw new Exception("Error reading from file: " + e.getMessage());
        }
    }

    private List<Customer> loadCustomersFromFile(String customerFilename) throws IOException {
        List<Customer> tempCustomers = new ArrayList<>();
        try (BufferedReader customerReader = new BufferedReader(new FileReader(customerFilename))) {
            String line;
            while ((line = customerReader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length < 5) {
                    throw new IllegalArgumentException("Invalid data format in customer file.");
                }

                String id = parts[0].trim();
                String fullName = parts[1].trim();
                String cardNumber = parts[2].trim();

                Set<String> dependentIDs = new HashSet<>();
                if (!parts[4].trim().equals("null")) {
                    String[] depIDs = parts[4].split(", ");
                    dependentIDs.addAll(Arrays.asList(depIDs));
                }

                InsuranceCard insuranceCard = new InsuranceCard(cardNumber);
                PolicyHolder policyHolder = new PolicyHolder(id, fullName, insuranceCard);
                policyHolder.setDependantIDs(dependentIDs);
                tempCustomers.add(policyHolder);

                // Update lastCustomerId based on loaded IDs
                int customerIdNum = Integer.parseInt(id.split("-")[1]);
                if (customerIdNum > lastCustomerId) {
                    lastCustomerId = customerIdNum;
                }
            }
        }
        return tempCustomers;
    }

    private void loadDependentsFromFile(String dependentFilename) throws IOException {
        try (BufferedReader dependentReader = new BufferedReader(new FileReader(dependentFilename))) {
            String line;
            while ((line = dependentReader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length != 4) {
                    throw new IllegalArgumentException("Invalid data format in dependent file.");
                }

                String dependentID = parts[0].trim();
                String dependentName = parts[1].trim();
                String cardNumber = parts[2].trim();
                String policyHolderID = parts[3].trim();

                InsuranceCard insuranceCard = new InsuranceCard(cardNumber);
                Dependant dependant = new Dependant(dependentID, dependentName, insuranceCard, policyHolderID);
                customers.add(dependant);

                PolicyHolder policyHolder = (PolicyHolder) getOne(policyHolderID);
                if (policyHolder != null) {
                    policyHolder.getDependantIDs().add(dependentID);
                }

                // Update lastCustomerId based on loaded IDs
                int customerIdNum = Integer.parseInt(dependentID.split("-")[1]);
                if (customerIdNum > lastCustomerId) {
                    lastCustomerId = customerIdNum;
                }
            }
        }
    }
}