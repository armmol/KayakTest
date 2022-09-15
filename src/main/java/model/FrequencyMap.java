package model;

import contract.FrequencyMapContract;

import java.util.*;

public class FrequencyMap implements FrequencyMapContract {
    private final TreeMap<Double, Integer> frequencyTreeMap;
    private final ArrayList<Double> userInput;
    private String[][] frequencyMatrix;

    public FrequencyMap(ArrayList<Double> userInput) {
        this.userInput = userInput;
        frequencyTreeMap = new TreeMap<>();
        createFrequencyTreeMap(userInput);
        createFrequencyMatrix();
    }

    @Override
    public void createFrequencyTreeMap(ArrayList<Double> userInput) {
        for (Double i = userInput.get(0); i <= userInput.get(userInput.size() - 1); i++) {
            if (!frequencyTreeMap.containsKey(i)) {
                frequencyTreeMap.put(i, 0);
            }
        }
        for (Double number : userInput) {
            frequencyTreeMap.put(number, frequencyTreeMap.get(number) + 1);
        }
    }

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

    public int getHighestFrequency() {
        if (!frequencyTreeMap.values().isEmpty())
            return frequencyTreeMap.values().stream().max(Double::compare).get();
        else
            return 0;
    }

    public TreeMap<Double, Integer> getFrequencyTreeMap() {
        return frequencyTreeMap;
    }

    public ArrayList<Double> getUserInput() {
        return userInput;
    }

    public String[][] getFrequencyMatrix() {
        return frequencyMatrix;
    }
}
