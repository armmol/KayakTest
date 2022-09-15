package controller;

import contract.ServiceContract;
import model.FrequencyMap;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Service implements ServiceContract {

    private final Scanner scanner;
    private final ArrayList<Double> userInput;
    private FrequencyMap frequencyMap;

    public Service() {
        scanner = new Scanner(System.in);
        userInput = new ArrayList<>();
    }

    @Override
    public void acceptInput() {
        System.out.print("Enter Values separated by a single space.");
        printSeparator();
        ArrayList<String> stringEntered = (ArrayList<String>) Arrays.stream(scanner.nextLine().trim().split(" "))
                .collect(Collectors.toList());
        validateInput(stringEntered);
    }

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

    @Override
    public boolean isNumeric(String strNum) {
        final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    @Override
    public void defineFrequencyMapObject() {
        Collections.sort(userInput);
        frequencyMap = new FrequencyMap(userInput);
    }

    @Override
    public void printSolution() {
        System.out.printf("The following displays a list for the solution to the problem.\n" +
                "%1$-10s %2$-10s\n", "Value", "Frequency");
        printSeparator();
        for (Map.Entry<Double, Integer> mapElement : frequencyMap.getFrequencyTreeMap().entrySet()) {
            System.out.printf("%1$-10s %2$-10s\n", mapElement.getKey(), mapElement.getValue());
        }
        printSeparator();
        for (int i = frequencyMap.getFrequencyMatrix()[0].length - 1; i >= 0; i--) {
            for (int j = 0; j < frequencyMap.getFrequencyMatrix().length; j++) {
                System.out.printf("%1$-10s ", frequencyMap.getFrequencyMatrix()[j][i]);
            }
            System.out.println();
        }
        printSeparator();
    }

    @Override
    public void printSeparator() {
        System.out.println("\n-------------------------------------------------");
    }
}
