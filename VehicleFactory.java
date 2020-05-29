
import java.util.ArrayList;
import java.util.List;

public class VehicleFactory {
    OrdersParser ordersParser = new OrdersParser();

    List<Order> orders = new ArrayList<>();

    public void setOrders(String orderArgs) {
        try {
            orders = ordersParser.parseOrder(orderArgs);
        } catch (Exception e) {
            orders.clear();
        }
    }

    public void produce() {
        if (orders.size() == 0) {
            System.out.println("There is no production orders to do!");
            return;
        }

        List<VehicleProductionThread> threads = new ArrayList<>();
        int sumOfCosts = 0;

        for (Order order : orders) {
            VehicleType type = order.type;
            int cost = getProductionCost(type);

            VehicleProductionThread vehicleProductionThread = new VehicleProductionThread.Builder()
                    .vehicleType(type)
                    .productionTime(getProductionTime(type))
                    .productionCost(cost)
                    .build();

            threads.add(vehicleProductionThread);
            vehicleProductionThread.thread.start();

            sumOfCosts += cost;
        }

        try {
            for (VehicleProductionThread vehicleProductionThread : threads) {
                vehicleProductionThread.thread.join();
            }
        } catch (Exception e) {
            // Exception procedure
        }

        System.out.println("Sum of production costs = " + sumOfCosts);
        orders.clear();
    }

    private int getProductionTime(VehicleType vehicleType) {
        int productionTime;
        switch (vehicleType) {
            case CAR:
                productionTime = 10;
                break;
            case MOTORCYCLE:
                productionTime = 5;
                break;
            case TRUCK:
                productionTime = 15;
                break;
            default:
                productionTime = 0;
        }
        return productionTime;
    }

    private int getProductionCost(VehicleType vehicleType) {
        int productionCost;
        switch (vehicleType) {
            case CAR:
                productionCost = 1000;
                break;
            case MOTORCYCLE:
                productionCost = 600;
                break;
            case TRUCK:
                productionCost = 2000;
                break;
            default:
                productionCost = 0;
        }
        return productionCost;
    }
}
