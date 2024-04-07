package Handler;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * The type Input validator.
 */
public class InputValidator {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    private static final String[] VALID_STATUSES = {"new", "processing", "done"};

    /**
     * Validates and returns a formatted Claim ID.
     *
     * @param scanner Scanner object for user input.
     * @return Formatted Claim ID.
     */
    public static String getFormattedClaimID(Scanner scanner) {
        return validateInput(scanner, "Enter Claim ID (f-followed by 10 numbers): ", InputValidator::formatClaimID);
    }

    /**
     * Formats the input to match the required Claim ID format.
     *
     * @param input User input for Claim ID.
     * @return Formatted Claim ID or null if the input is invalid.
     */
    private static String formatClaimID(String input) {
        if (input.matches("^f-\\d{10}$")) {
            return input;
        } else if (input.matches("^\\d{1,9}$")) {
            String paddedNumber = String.format("%010d", Long.parseLong(input));
            return "f-" + paddedNumber;
        } else {
            throw new IllegalArgumentException("Invalid Claim ID format. Please enter the Claim ID in the correct format.");
        }
    }

    /**
     * Validates and returns a Date object from the user input.
     *
     * @param scanner Scanner object for user input.
     * @param prompt  Prompt message for the user.
     * @return Validated Date object.
     */
    public static Date getDateInput(Scanner scanner, String prompt) {
        return validateInput(scanner, prompt, InputValidator::parseDate);
    }

    /**
     * Parses the input to a Date object.
     *
     * @param input User input for Date.
     * @return Date object or throws exception if the input is invalid.
     */
    private static Date parseDate(String input) throws Exception {
        if (!input.matches("^\\d{2}-\\d{2}-\\d{4}$")) {
            throw new IllegalArgumentException("Invalid date format. Please enter the date in dd-MM-yyyy format.");
        }
        return dateFormat.parse(input);
    }

    /**
     * Validates and returns a non-negative double value from the user input.
     *
     * @param scanner Scanner object for user input.
     * @param prompt  Prompt message for the user.
     * @return Validated Date object.
     */
    public static double getDoubleInput(Scanner scanner, String prompt) {
        return validateInput(scanner, prompt, InputValidator::parseDouble);
    }

    /**
     * Parses the input to a double value.
     *
     * @param input User input for double value.
     * @return Double value or throws exception if the input is invalid.
     */
    private static double parseDouble(String input) {
        double value = Double.parseDouble(input);
        if (value < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        return value;
    }

    /**
     * Validates and returns a non-empty string from the user input.
     *
     * @param scanner Scanner object for user input.
     * @param prompt  Prompt message for the user.
     * @return Validated non-empty string.
     */
    public static String getStringInput(Scanner scanner, String prompt) {
        return validateInput(scanner, prompt, InputValidator::validateNonEmptyString);
    }

    /**
     * Validates that the input is a non-empty string.
     *
     * @param input User input for string.
     * @return Validated non-empty string or throws exception if the input is empty.
     */
    private static String validateNonEmptyString(String input) {
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input cannot be empty. Please try again.");
        }
        return input;
    }

    /**
     * Validates and returns a valid claim status from the user input.
     *
     * @param scanner Scanner object for user input.
     * @return Valid claim status.
     */
    public static String getClaimStatus(Scanner scanner) {
        return validateInput(scanner, "Enter Status (New, Processing, Done): ", InputValidator::validateClaimStatus);
    }

    /**
     * Validates that the input is a valid claim status.
     *
     * @param input User input for claim status.
     * @return Valid claim status or throws exception if the input is invalid.
     */
    private static String validateClaimStatus(String input) {
        String status = input.toLowerCase();
        if (!Arrays.asList(VALID_STATUSES).contains(status)) {
            throw new IllegalArgumentException("Invalid status. Status must be New, Processing, or Done.");
        }
        return status;
    }

    /**
     * Validates and returns a valid customer ID from the user input.
     *
     * @param scanner Scanner object for user input.
     * @return Validated Customer ID.
     */
    public static String getCustomerID(Scanner scanner) {
        return validateInput(scanner, "Enter the Customer ID (c-followed by 7 numbers): ", InputValidator::validateCustomerID);
    }

    /**
     * Validates that the input is a valid customer ID.
     *
     * @param input User input for Customer ID.
     * @return Validated Customer ID or throws exception if the input is invalid.
     */
    private static String validateCustomerID(String input) {
        if (!input.matches("^c-\\d{7}$")) {
            throw new IllegalArgumentException("Invalid ID format. Please enter again.");
        }
        return input;
    }

    /**
     * Generic method to validate user input.
     *
     * @param scanner   Scanner object for user input.
     * @param prompt    Prompt message for the user.
     * @param validator Function to validate the input.
     * @param <T>       Type of the validated input.
     * @return Validated input.
     */
    private static <T> T validateInput(Scanner scanner, String prompt, Validator<T> validator) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return validator.validate(input);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Functional interface for input validation.
     *
     * @param <T> Type of the validated input.
     */
    @FunctionalInterface
    private interface Validator<T> {
        /**
         * Validate t.
         *
         * @param input the input
         * @return the t
         * @throws Exception the exception
         */
        T validate(String input) throws Exception;
    }
}