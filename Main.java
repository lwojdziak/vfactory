
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        VehicleFactory vehicleFactory = new VehicleFactory();

        String initializingOrder = args[0];
        String nextOrder;

        System.out.println("Initializing order:" + initializingOrder);

        if (initializingOrder.length() > 0) {
            vehicleFactory.setOrders(initializingOrder);
            vehicleFactory.produce();
        }

        while (true) {
            System.out.println("You can enter next order: \n");
            nextOrder = in.nextLine();
            System.out.println("Next order:" + nextOrder);

            if (nextOrder.length() > 0) {
                vehicleFactory.setOrders(nextOrder);
                vehicleFactory.produce();
            }
        }
    }
}
