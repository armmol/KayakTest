package model;

import contract.FrequencyMapContract;

import java.util.*;

public class FrequencyMap implements FrequencyMapContract {
    private final TreeMap<Double, Integer> frequencyTreeMap;
    private final ArrayList<Double> userInput;
    private String[][] frequencyMatrix;
    private final double maximum;
    private final double minimum;

    public FrequencyMap(ArrayList<Double> userInput) {
        this.userInput = userInput;
        frequencyTreeMap = new TreeMap<>();
        maximum = userInput.get(userInput.size() - 1);
        minimum = userInput.get(0);
        createFrequencyTreeMap();
        createFrequencyMatrix();
    }

    @Override
    public void createFrequencyTreeMap() {
        for (Double i = minimum; i <= maximum; i++) {
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
        int maxIterations = 0;
        for (Map.Entry<Double, Integer> currentMapElement : frequencyTreeMap.entrySet()) {
            if (currentMapElement.getValue() > maxIterations) {
                maxIterations = currentMapElement.getValue();
            }
        }
        return maxIterations;
    }

    public TreeMap<Double, Integer> getFrequencyTreeMap() {
        return frequencyTreeMap;
    }

    public ArrayList<Double> getUserInput() {
        return userInput;
    }

    public Double getMaximum() {
        return maximum;
    }

    public double getMinimum() {
        return minimum;
    }

    public String[][] getFrequencyMatrix() {
        return frequencyMatrix;
    }

    @Override
    public String toString() {
        return "FrequencyMap{" +
                "frequencyHashMap=" + frequencyTreeMap +
                ", userInput=" + userInput +
                ", frequencyMatrix=" + Arrays.toString(frequencyMatrix) +
                ", maximum=" + maximum +
                ", minimum=" + minimum +
                '}';
    }
}
