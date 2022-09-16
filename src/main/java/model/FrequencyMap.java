package model;

import contract.FrequencyMapContract;

import java.util.*;

/**
 * FrequencyMap Model class comprising a TreeMap and a 2D Array to process the input provided by the user.
 */
public class FrequencyMap implements FrequencyMapContract {
    private final TreeMap<Double, Integer> frequencyTreeMap;
    private String[][] frequencyMatrix;

    /**
     * FrequencyMap Constructor
     */
    public FrequencyMap() {
        frequencyTreeMap = new TreeMap<>();
    }

    /**
     * Function to create a TreeMap, known as frequencyTreeMap belonging to the class,
     * of the values entered in the list condition being that the list in not empty.
     *
     * @param userInput ArrayList of Type Double, includes values from user input.
     */
    @Override
    public void createFrequencyTreeMap(ArrayList<Double> userInput) {
        if(!userInput.isEmpty()) {
            for (Double i = userInput.get(0); i <= userInput.get(userInput.size() - 1); i++) {
                if (!frequencyTreeMap.containsKey(i)) {
                    frequencyTreeMap.put(i, 0);
                }
            }
            for (Double number : userInput) {
                frequencyTreeMap.put(number, frequencyTreeMap.get(number) + 1);
            }
        }
    }

    /**
     * Function to create the 2D Array known as the frequencyMatrix belonging to the class
     * which is later used to display the number line in the Service.java class.
     * returns void
     */
    @Override
    public void createFrequencyMatrix() {
        frequencyMatrix = new String[frequencyTreeMap.size()][getHighestFrequency() + 1];
        int i = 0;
        TreeMap<Double, Integer> frequencyTreeMapCopy = new TreeMap<>(frequencyTreeMap);
        for (Map.Entry<Double, Integer> currentMapElement : frequencyTreeMapCopy.entrySet()) {
            for (int j = 0; j < frequencyMatrix[0].length; j++) {
                if (j == 0) {
                    frequencyMatrix[i][j] = "" + currentMapElement.getKey();
                } else {
                    if (currentMapElement.getValue() > 0) {
                        frequencyMatrix[i][j] = "*";
                        frequencyTreeMapCopy.put(currentMapElement.getKey(), currentMapElement.getValue() - 1);
                    } else {
                        frequencyMatrix[i][j] = " ";
                    }
                }
            }
            i++;
        }
    }

    /**
     * Function to return the highest frequecy of the number found in the list of numbers entered by the user.
     *
     * @return Integer value of number of times a number occurs in the list of numbers.
     */
    @Override
    public int getHighestFrequency() {
        if (!frequencyTreeMap.values().isEmpty()) {
            return frequencyTreeMap.values().stream().max(Double::compare).get();
        }
        else {
            return 0;
        }
    }

    /**
     * Function to return the TreeMap
     *
     * @return TreeMap Data structure of type <Double,Integer>
     */
    @Override
    public TreeMap<Double, Integer> getFrequencyTreeMap() {
        return frequencyTreeMap;
    }

    /**
     * Function to return 2D Array Frequency Matrix
     *
     * @return 2D Array of type <String>
     */
    @Override
    public String[][] getFrequencyMatrix() {
        return frequencyMatrix;
    }
}
