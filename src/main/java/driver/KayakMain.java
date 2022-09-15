package driver;

import controller.Service;

public class KayakMain {
    public static void main(String[] args) {
        Service service = new Service();
        while (true) {
            try {
                service.acceptInput();
                service.defineFrequencyMapObject();
                service.printSolution();
            } catch (NumberFormatException e) {
                System.out.println(e.getMessage());
                service.printSeparator();
            }
        }
    }
}
