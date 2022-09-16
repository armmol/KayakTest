package controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    //Dummy Test Inputs
    private final String VALID_INPUT = "1 1 1 -1 -2 3 3 ";
    private final String INVALID_INPUT = "jg 1 1 -s1  -2f f3g 3 ";
    private final ArrayList<String> VALID_INPUT_ARRAYLIST = (ArrayList<String>) Arrays.stream(VALID_INPUT.trim().split(" "))
            .collect(Collectors.toList());
    private final ArrayList<String> INVALID_INPUT_ARRAYLIST = (ArrayList<String>) Arrays.stream(INVALID_INPUT.trim().split(" "))
            .collect(Collectors.toList());
    private Service service;
    private ByteArrayOutputStream outputStreamCaptor;

    @BeforeEach
    void setUp() {
        service = new Service();
        outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        System.out.flush();
    }

    @Test
    void acceptInput() {
        InputStream systemInBackup = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(VALID_INPUT.getBytes());
        System.setIn(in);
        assertEquals(service.acceptInput(in), VALID_INPUT_ARRAYLIST);
        assertEquals(String.format("Enter Values separated by a single space."
                        + "\n-------------------------------------------------\n"
                        + "You entered --> %s\n", VALID_INPUT).trim(),
                outputStreamCaptor.toString().trim());
        System.setIn(systemInBackup);
    }

    @Test
    void validateInputTestForValidInput() {
        assertDoesNotThrow(() -> service.validateInput(VALID_INPUT_ARRAYLIST));
    }

    @Test
    void validateInputTestForInvalidInputThrowsNumberFormatException() {
        assertThrows(NumberFormatException.class, () -> service.validateInput(INVALID_INPUT_ARRAYLIST));
    }

    @Test
    void isNumericForValidNumericInput() {
        assertTrue(service.isNumeric("-2565"));
        assertTrue(service.isNumeric("65"));
        assertTrue(service.isNumeric("5"));
    }

    @Test
    void isNumericForInvalidNumericInput() {
        assertFalse(service.isNumeric("    "));
        assertFalse(service.isNumeric("1f"));
        assertFalse(service.isNumeric(" vsdknvs   "));
    }

    @Test
    void defineFrequencyMapObject() {
        service.validateInput(VALID_INPUT_ARRAYLIST);
        service.defineFrequencyMapObject();
        assertEquals(service.getFrequencyMap().getHighestFrequency(), 3);
    }

    @Test
    void printSolution() {
        service.validateInput(VALID_INPUT_ARRAYLIST);
        service.defineFrequencyMapObject();
        service.printSolution();
        String expectedOutput = String.format("The following displays a list for the solution to the problem.\n" +
                        "%1$-10s %2$-10s\n" +
                        "\n-------------------------------------------------\n" +
                        "%3$-10s %4$-10s\n" +
                        "%5$-10s %6$-10s\n" +
                        "%7$-10s %8$-10s\n" +
                        "%9$-10s %10$-10s\n" +
                        "%11$-10s %12$-10s\n" +
                        "%13$-10s %14$-10s\n" +
                        "\n-------------------------------------------------\n" +
                        "%15$-10s %16$-10s %17$-10s %18$-10s %19$-10s %20$-10s \n" +
                        "%21$-10s %22$-10s %23$-10s %24$-10s %25$-10s %26$-10s \n" +
                        "%27$-10s %28$-10s %29$-10s %30$-10s %31$-10s %32$-10s \n" +
                        "%33$-10s %34$-10s %35$-10s %36$-10s %37$-10s %38$-10s \n" +
                        "\n-------------------------------------------------\n"// +
                , "Value", "Frequency",
                "-2.0", "1",
                "-1.0", "1",
                "0.0", "0",
                "1.0", "3",
                "2.0", "0",
                "3.0", "2",
                " ", " ", " ", "*", " ", " ",
                " ", " ", " ", "*", " ", "*",
                "*", "*", " ", "*", " ", "*",
                "-2.0", "-1.0", "0.0", "1.0", "2.0 ", "3.0 ");
        assertEquals(service.getFrequencyMap().getFrequencyMatrix().length, 6);
        assertEquals(service.getFrequencyMap().getFrequencyMatrix()[0].length, 4);
        assertEquals(service.getFrequencyMap().getFrequencyTreeMap().ceilingKey(1.0), 1);
        assertEquals(service.getFrequencyMap().getFrequencyTreeMap().size(), 6);
        assertEquals(outputStreamCaptor.toString().trim(), expectedOutput.trim());
    }

    @Test
    void printSeparator() {
        service.printSeparator();
        assertEquals("\n-------------------------------------------------\n", outputStreamCaptor.toString());
    }
}