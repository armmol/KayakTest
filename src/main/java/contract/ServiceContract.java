package contract;

import java.io.InputStream;
import java.util.ArrayList;

/**
 *Interface for the Service.java Controller Class defining functions in use.
 */

public interface ServiceContract {

    ArrayList<String> acceptInput(InputStream inputStream);

    void validateInput(ArrayList<String> userInput);

    boolean isNumeric(String strNum);

    void defineFrequencyMapObject();

    void printSolution();

    void printSeparator();
}
