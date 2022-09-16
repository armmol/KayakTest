package contract;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Interface for the FrequencyMap.java Model Class defining functions in use.
 */

public interface FrequencyMapContract {
    void createFrequencyTreeMap(ArrayList<Double> userInput);

    void createFrequencyMatrix();

    int getHighestFrequency();

    TreeMap<Double, Integer> getFrequencyTreeMap();

    String[][] getFrequencyMatrix();
}
