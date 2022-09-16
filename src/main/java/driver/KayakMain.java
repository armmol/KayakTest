package driver;

import controller.Service;

import java.util.ArrayList;

/**
 * Main Driver class to run the command Line application to accept input and process the output.
 * Consists of a Main Function that takes args as parameter and runs a try catch block to catch a Number format exception inside
 * an infinite while loop can be exited if the user types "Cancel" in the input at any point stopping the process.
 */

public class KayakMain {
    /**
     * Main function that creates an object of the service class, runs a try catch block inside an indefinite while loop and
     * accepts input, validates it, and prints the solution.
     *
     * @param args contains the supplied command line arguments.
     * returns void
     */
    public static void main(String[] args) {
        Service service = new Service();
        //Infinite room to run the service indefinitely
        while (true) {
            try {
                ArrayList<String> input = service.acceptInput(System.in);
                if (!input.contains("cancel")) {
                    service.validateInput(input);
                    service.defineFrequencyMapObject();
                    service.printSolution();
                } else {
                    System.out.println("Stopping Process");
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                service.printSeparator();
            }
        }
    }
}
