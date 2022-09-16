package controller;

import contract.ServiceContract;
import model.FrequencyMap;

import java.io.InputStream;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Service implements ServiceContract {

    private final ArrayList<Double> userInput;
    private FrequencyMap frequencyMap;

    public Service() {
        userInput = new ArrayList<>();
    }

    /**
     * Function to return the frequency Map Object
     * Required for testing
     *
     * @return FrequencyMap Object
     */
    public FrequencyMap getFrequencyMap() {
        return frequencyMap;
    }

    /**
     * Function to accept input from the user.
     *
     * @param inputStream Ordered stream of bytes to read data entered by the user using the Scanner class
     * @return ArrayList of type <String> used for further processes in the application.
     */
    @Override
    public ArrayList<String> acceptInput(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream);
        System.out.print("Enter Values separated by a single space. Type \"Cancel\" at any point to stop process.");
        printSeparator();
        String inputString = scanner.nextLine().trim().toLowerCase(Locale.ROOT);
        System.out.printf("You entered --> %s\n", inputString);
        return (ArrayList<String>) Arrays.stream(inputString.split(" "))
                .collect(Collectors.toList());
    }

    /**
     * Function to check if the user input is valid for the application.
     *
     * @param stringInput ArrayList<String> comprising values entered by the user.
     * @throws NumberFormatException when @param stringInput is non numeric.
     * returns void
     */
    @Override
    public void validateInput(ArrayList<String> stringInput) throws NumberFormatException {
        userInput.clear();
        for (String strNum : stringInput) {
            if (!isNumeric(strNum)) {
                throw new NumberFormatException("Seems like you have added an extra space or a non-numeric character.\n" +
                        "Please enter numbers separated by a single space only and try again.");
            } else {
                userInput.add(Double.parseDouble(strNum));
            }
        }
    }

    /**
     * Function to check if the string is numeric in nature by comparing to a regular expression.
     *
     * @param strNum String type input used to check for numeric nature.
     * @return boolean whether sting entered is numeric or not, True if it is, False if it is not.
     */
    @Override
    public boolean isNumeric(String strNum) {
        final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    /**
     * Function to initialise the FrequencyMap Object and call its function for creating the required
     * TreeMap and 2D Array of type<String> used for printing the number line.
     * returns void
     */
    @Override
    public void defineFrequencyMapObject() {
        frequencyMap = new FrequencyMap();
        Collections.sort(userInput);
        frequencyMap.createFrequencyTreeMap(userInput);
        frequencyMap.createFrequencyMatrix();
    }

    /**
     * Function to print the solution of the user input and print the number line
     * using the 2D Array FrequencyMatrix of the FrequencyMap class.
     * returns void
     */
    @Override
    public void printSolution() {
        System.out.printf("The following displays a list for the solution to the problem.\n" +
                "%1$-10s %2$-10s\n", "Value", "Frequency");
        printSeparator();
        for (Map.Entry<Double, Integer> mapElement : frequencyMap.getFrequencyTreeMap().entrySet()) {
            System.out.printf("%1$-10s %2$-10s\n", mapElement.getKey(), mapElement.getValue());
        }
        printSeparator();
        //Printing the 2D Array FrequencyMatrix using different traversal to suit printing format of number line.
        for (int i = frequencyMap.getFrequencyMatrix()[0].length - 1; i >= 0; i--) {
            for (int j = 0; j < frequencyMap.getFrequencyMatrix().length; j++) {
                System.out.printf("%1$-10s ", frequencyMap.getFrequencyMatrix()[j][i]);
            }
            System.out.print("\n");
        }
        printSeparator();
    }

    /**
     * Function to print a separator between different solution prints.
     * returns void
     */
    @Override
    public void printSeparator() {
        System.out.print("\n-------------------------------------------------\n");
    }
}
