package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class FrequencyMapTest {

    //Dummy Test Inputs
    private final ArrayList<Double> VALID_USER_INPUT_ARRAYLIST = new ArrayList<>(Arrays.asList(1.0, 1.0, 1.0, -1.0, -2.0, 3.0, 3.0));
    private final TreeMap<Double, Integer> VALID_FREQUENCY_TREEMAP = new TreeMap<>();
    private final String[][] VALID_FREQUENCY_MATRIX = {{"-2.0", "*", " ", " "}, {"-1.0", "*", " ", " "},
            {"0.0", " ", " ", " "}, {"1.0", "*", "*", "*"}, {"2.0", " ", " ", " "}, {"3.0", "*", "*", " "}};
    private FrequencyMap frequencyMap;

    @BeforeEach
    void setUp() {
        Collections.sort(VALID_USER_INPUT_ARRAYLIST);
        frequencyMap = new FrequencyMap();
        VALID_FREQUENCY_TREEMAP.put(1.0, 3);
        VALID_FREQUENCY_TREEMAP.put(-1.0, 1);
        VALID_FREQUENCY_TREEMAP.put(-2.0, 1);
        VALID_FREQUENCY_TREEMAP.put(0.0, 0);
        VALID_FREQUENCY_TREEMAP.put(2.0, 0);
        VALID_FREQUENCY_TREEMAP.put(3.0, 2);
    }

    @AfterEach
    void tearDown() {
        System.out.flush();
    }

    @Test
    void createFrequencyTreeMap() {
        frequencyMap.createFrequencyTreeMap(VALID_USER_INPUT_ARRAYLIST);
        assertEquals(VALID_FREQUENCY_TREEMAP.lastEntry(), frequencyMap.getFrequencyTreeMap().lastEntry());
        assertEquals(VALID_FREQUENCY_TREEMAP.firstEntry(), frequencyMap.getFrequencyTreeMap().firstEntry());
        assertEquals(VALID_FREQUENCY_TREEMAP.entrySet(), frequencyMap.getFrequencyTreeMap().entrySet());
    }

    @Test
    void createFrequencyMatrix() {
        frequencyMap.createFrequencyTreeMap(VALID_USER_INPUT_ARRAYLIST);
        frequencyMap.createFrequencyMatrix();
        Assertions.assertEquals(VALID_FREQUENCY_MATRIX.length, frequencyMap.getFrequencyMatrix().length);
        Assertions.assertEquals(VALID_FREQUENCY_MATRIX[0].length, frequencyMap.getFrequencyMatrix()[0].length);
        Assertions.assertArrayEquals(frequencyMap.getFrequencyMatrix(), VALID_FREQUENCY_MATRIX);
    }

    @Test
    void createFrequencyTreeMapForEmptyInput() {
        frequencyMap.createFrequencyTreeMap(new ArrayList<>(Collections.emptyList()));
        assertNotEquals(VALID_FREQUENCY_TREEMAP.entrySet(), frequencyMap.getFrequencyTreeMap().entrySet());
        assertTrue(frequencyMap.getFrequencyTreeMap().isEmpty());
    }

    @Test
    void getHighestFrequency() {
        frequencyMap.createFrequencyTreeMap(VALID_USER_INPUT_ARRAYLIST);
        assertEquals(3, frequencyMap.getHighestFrequency());
    }

    @Test
    void getHighestFrequencyReturnsZeroForEmptyTreeMap() {
        frequencyMap.createFrequencyTreeMap(new ArrayList<>(Collections.emptyList()));
        assertEquals(0, frequencyMap.getHighestFrequency());
    }

    @Test
    void getFrequencyTreeMap() {
        frequencyMap.createFrequencyTreeMap(VALID_USER_INPUT_ARRAYLIST);
        assertEquals(VALID_FREQUENCY_TREEMAP, frequencyMap.getFrequencyTreeMap());
    }

    @Test
    void getFrequencyMatrix() {
        frequencyMap.createFrequencyTreeMap(VALID_USER_INPUT_ARRAYLIST);
        frequencyMap.createFrequencyMatrix();
        Assertions.assertArrayEquals(VALID_FREQUENCY_MATRIX, frequencyMap.getFrequencyMatrix());
    }
}