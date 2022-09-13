package model;
import contract.FrequencyMapContract;

import java.util.*;

public class FrequencyMap implements FrequencyMapContract {
    private final TreeMap<Double, Integer> frequencyHashMap;
    private final ArrayList<Double> userInput;
    private String[][] frequencyMatrix;
    private final double maximum;
    private final double minimum;

    public FrequencyMap(ArrayList<Double> userInput) {
        this.userInput = userInput;
        frequencyHashMap = new TreeMap<>();
        maximum = userInput.get(userInput.size() - 1);
        minimum = userInput.get(0);
        createFrequencyHashMap();
        createFrequencyMatrix();
    }

    @Override
    public void createFrequencyHashMap() {
        for (Double i = minimum; i <= maximum; i++) {
            if (!frequencyHashMap.containsKey(i)) {
                frequencyHashMap.put(i, 0);
            }
        }
        for (Double number : userInput) {
            frequencyHashMap.put(number, frequencyHashMap.get(number) + 1);
        }
    }

    @Override
    public void createFrequencyMatrix() {
        frequencyMatrix = new String[frequencyHashMap.size()][getHighestFrequency() + 1];
        int i = 0;
        for (Map.Entry<Double, Integer> currentMapElement : frequencyHashMap.entrySet()) {
            for (int j = 0; j < frequencyMatrix[0].length; j++) {
                if (j == 0) {
                    frequencyMatrix[i][j] = "" + currentMapElement.getKey();
                } else {
                    if (currentMapElement.getValue() > 0) {
                        frequencyMatrix[i][j] = "*";
                        frequencyHashMap.put(currentMapElement.getKey(), currentMapElement.getValue() - 1);
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
        for (Map.Entry<Double, Integer> currentMapElement : frequencyHashMap.entrySet()) {
            if (currentMapElement.getValue() > maxIterations) {
                maxIterations = currentMapElement.getValue();
            }
        }
        return maxIterations;
    }

    public TreeMap<Double, Integer> getFrequencyHashMap() {
        return frequencyHashMap;
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
                "frequencyHashMap=" + frequencyHashMap +
                ", userInput=" + userInput +
                ", frequencyMatrix=" + Arrays.toString(frequencyMatrix) +
                ", maximum=" + maximum +
                ", minimum=" + minimum +
                '}';
    }
}