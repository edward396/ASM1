package ProcessManager;

import Classes.Claim;
import Classes.Dependant;
import Classes.InsuranceCard;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Implementation of the Claim Process Manager interface.
 */
public class ClaimProcessManagerImplement implements ClaimProcessManager {
    private List<Claim> claims;
    private String filename;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private InsuranceCardProcessManagerImplement insuranceCardProcessManager;  // Add this line

    /**
     * Constructs a new ClaimProcessManagerImplement with the specified filename.
     *
     * @param filename the name of the file to load claims from and save claims to
     */
    public ClaimProcessManagerImplement(String filename) {
        this.filename = filename;
        this.claims = new ArrayList<>();
        this.insuranceCardProcessManager = new InsuranceCardProcessManagerImplement();  // Initialize insuranceCardProcessManager

        try {
            loadFromFile(filename);
        } catch (Exception e) {
            System.out.println("Error loading file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Adds a new claim to the manager.
     *
     * @param claim the claim to be added
     */
    @Override
    public void add(Claim claim) {
        try {
            if (getOne(claim.getClaimID()) != null) {
                throw new IllegalArgumentException("Claim ID already exists. Please enter a unique Claim ID.");
            }

            // Add the new insurance card to the insuranceCardData.txt
            String newInsuranceCardNumber = claim.getCardNumber();
            String newInsuranceCardHolderID = "c-" + claim.getCustomerID().substring(2);
            String policyOwner = "ABC Company";  // Automatic policy owner
            Date expirationDate = dateFormat.parse("01-01-2030");  // Expiration date

            InsuranceCard newInsuranceCard = new InsuranceCard(newInsuranceCardNumber, newInsuranceCardHolderID, policyOwner, expirationDate);
            insuranceCardProcessManager.add(newInsuranceCard);

            claims.add(claim);
            saveToFile(filename);

        } catch (IllegalArgumentException e) {
            System.out.println("Error adding claim: " + e.getMessage());
        } catch (ParseException e) {
            System.out.println("Error parsing expiration date: " + e.getMessage());
        }
    }

    /**
     * Updates an existing claim in the manager.
     *
     * @param claim the updated claim
     */
    @Override
    public void update(Claim claim) {
        try {
            boolean updated = false;
            for (int i = 0; i < claims.size(); i++) {
                if (claims.get(i).getClaimID().equals(claim.getClaimID())) {
                    claims.set(i, claim);
                    updated = true;
                    break;
                }
            }
            if (!updated) {
                throw new IllegalArgumentException("Claim with ID " + claim.getClaimID() + " not found.");
            }
            saveToFile(filename);
        } catch (IllegalArgumentException e) {
            System.out.println("Error updating claim: " + e.getMessage());
        }
    }

    /**
     * Removes a claim from the manager.
     *
     * @param claimId the ID of the claim to be removed
     */
    @Override
    public void remove(String claimId) {
        try {
            boolean removed = claims.removeIf(claim -> claim.getClaimID().equals(claimId));
            if (!removed) {
                throw new IllegalArgumentException("Claim with ID " + claimId + " not found.");
            }
            saveToFile(filename);
        } catch (IllegalArgumentException e) {
            System.out.println("Error removing claim: " + e.getMessage());
        }
    }

    /**
     * Retrieves a claim by its ID.
     *
     * @param claimID the ID of the claim to retrieve
     * @return the claim with the specified ID, or null if not found
     */
    @Override
    public Claim getOne(String claimID) {
        for (Claim claim : claims) {
            if (claim.getClaimID().equals(claimID)) {
                return claim;
            }
        }
        return null;
    }

    /**
     * Retrieves all claims in the manager.
     *
     * @return a list of all claims
     */
    @Override
    public List<Claim> getAll() {
        return new ArrayList<>(claims);
    }

    /**
     * Retrieves all claim IDs in the manager.
     *
     * @return a list of all claim IDs
     */
    @Override
    public List<String> getAllClaimIDs() {
        List<String> claimIDs = new ArrayList<>();
        for (Claim claim : claims) {
            claimIDs.add(claim.getClaimID());
        }
        return claimIDs;
    }

    /**
     * Retrieves all claims associated with a specific customer ID.
     *
     * @param customerId the ID of the customer
     * @return a list of all claims associated with the specified customer ID
     */
    @Override
    public List<Claim> getAllClaimsByCustomerID(String customerId) {
        List<Claim> customerClaims = new ArrayList<>();
        for (Claim claim : claims) {
            if (claim.getCustomerID().equals(customerId)) {
                customerClaims.add(claim);
            }
        }
        return customerClaims;
    }

    /**
     * Saves the claims to a file.
     *
     * @param fileName the name of the file to save to
     */
    @Override
    public void saveToFile(String fileName) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Claim claim : claims) {
                writer.print(claim.getClaimID() + ", ");
                writer.print(dateFormat.format(claim.getClaimDate()) + ", ");
                writer.print(claim.getCustomerID() + ", ");
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
            System.out.println("Error saving file: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Loads claims from a file.
     *
     * @param fileName the name of the file to load from
     */
    public void loadFromFile(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");

                if (parts.length == 11) {
                    processClaimData(parts);
                } else if (parts.length == 4) {
                    processDependentData(parts);
                } else {
                    throw new IllegalArgumentException("Invalid line format: " + line);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading file: " + e.getMessage(), e);
        }
    }

    /**
     * Processes data for a claim and adds it to the manager.
     *
     * @param parts the parts of the claim data
     */
    private void processClaimData(String[] parts) {
        try {
            String claimID = parts[0].trim();
            Date claimDate = dateFormat.parse(parts[1].trim());
            String customerID = parts[2].trim();
            String cardNumber = parts[3].trim();
            Date examDate = dateFormat.parse(parts[4].trim());
            List<String> documents = Arrays.asList(parts[5].trim().split("_"));
            double amount = Double.parseDouble(parts[6].trim());
            String status = parts[7].trim();
            String bankName = parts[8].trim();
            String accountOwner = parts[9].trim();
            String accountNumber = parts[10].trim();

            Claim claim = new Claim.Builder()
                    .claimID(claimID)
                    .claimDate(claimDate)
                    .customerID(customerID)
                    .cardNumber(cardNumber)
                    .examDate(examDate)
                    .documents(documents)
                    .amount(amount)
                    .status(status)
                    .bankName(bankName)
                    .accountOwner(accountOwner)
                    .accountNumber(accountNumber)
                    .build();

            claims.add(claim);

        } catch (ParseException | NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing line: " + String.join(", ", parts) + ". Error: " + e.getMessage(), e);
        }
    }

    /**
     * Processes data for a dependent and associates it with an insurance card.
     *
     * @param parts the parts of the dependent data
     */
    private void processDependentData(String[] parts) {
        try {
            String dependentID = parts[0].trim();
            String dependentName = parts[1].trim();
            String cardNumber = parts[2].trim();  // This is the insurance card number
            String policyHolderID = parts[3].trim();

            InsuranceCard insuranceCard = insuranceCardProcessManager.getOne(cardNumber);
            if (insuranceCard != null) {
                Dependant dependant = new Dependant(dependentID, dependentName, insuranceCard, policyHolderID);
                // Add dependent to the insurance card
                insuranceCard.addDependant(dependant);
            }

        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing line: " + String.join(", ", parts) + ". Error: " + e.getMessage(), e);
        }
    }
}