package controller;

import contract.ServiceContract;
import model.FrequencyMap;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Service implements ServiceContract {

    private final Scanner scanner;
    private final Pattern pattern;
    private final ArrayList<Double> userInput;
    private FrequencyMap frequencyMap;

    public Service() {
        scanner = new Scanner(System.in);
        pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        userInput = new ArrayList<>();
    }

    @Override
    public void startService() {
        while (true) {
            try {
                acceptInput();
                defineFrequencyMapObject();
                printSolution();
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @Override
    public void acceptInput() {
        System.out.println("Enter Values separated by a single space");
        ArrayList<String> stringEntered = (ArrayList<String>) Arrays.stream(scanner.nextLine().trim().split(" "))
                .collect(Collectors.toList());
        validateInput(stringEntered);
    }

    @Override
    public void validateInput(ArrayList<String> stringInput) throws NumberFormatException {
        userInput.clear();
        for (String strNum : stringInput) {
            if (!isNumeric(strNum)) {
                throw new NumberFormatException("Please enter numbers separated by a single space only and try again");
            } else {
                userInput.add(Double.parseDouble(strNum));
            }
        }
    }

    @Override
    public boolean isNumeric(String strNum) {
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
        System.out.printf("The following displays a list for the solution to the problem.\n"+
                "%1$-10s %2$-10s\n","Value","Frequency");
        for(Map.Entry<Double,Integer> mapElement : frequencyMap.getFrequencyTreeMap().entrySet()){
            System.out.printf("%1$-10s %2$-10s\n", mapElement.getKey(),mapElement.getValue());
        }
        for (int i = frequencyMap.getFrequencyMatrix()[0].length - 1; i >= 0; i--) {
            for (int j = 0; j < frequencyMap.getFrequencyMatrix().length; j++) {
                System.out.printf("%1$-10s ", frequencyMap.getFrequencyMatrix()[j][i]);
            }
            System.out.println();
        }
    }
}
