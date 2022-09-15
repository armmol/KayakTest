package contract;

import java.util.ArrayList;

public interface ServiceContract {

    void acceptInput();

    void validateInput(ArrayList<String> userInput);

    boolean isNumeric(String strNum);

    void defineFrequencyMapObject();

    void printSolution();

    void printSeparator();
}
