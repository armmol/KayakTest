package driver;

import controller.Service;

/**
 * Main Driver class to run the command Line application to accept input and process the output.
 */

public class KayakMain {
    public static void main(String[] args) {
        Service service = new Service();
        //Infinite room to run the service indefinitely
        while (true) {
            try {
                service.validateInput(service.acceptInput(System.in));
                service.defineFrequencyMapObject();
                service.printSolution();
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                service.printSeparator();
            }
        }
    }
}
