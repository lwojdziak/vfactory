
public class VehicleProductionThread implements Runnable {
    public Thread thread;
    VehicleType vehicleType;
    int productionTime;
    int productionCost;

    private VehicleProductionThread() {
        thread = new Thread(this);
    }

    @Override
    public void run() {
        try {
            System.out.println("Start production of " + vehicleType + " in " + productionTime + " sec. (cost: " + productionCost + ")");
            Thread.sleep(productionTime * 1000);
            System.out.println("Production of " + vehicleType + " finished");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static final class Builder {
        VehicleType vehicleType;
        int productionTime;
        int productionCost;

        public Builder vehicleType(VehicleType vehicleType) {
            this.vehicleType = vehicleType;
            return this;
        }

        public Builder productionTime(int productionTime) {
            this.productionTime = productionTime;
            return this;
        }

        public Builder productionCost(int productionCost) {
            this.productionCost = productionCost;
            return this;
        }

        public VehicleProductionThread build() {
            VehicleProductionThread vehicleProductionThread = new VehicleProductionThread();
            vehicleProductionThread.vehicleType = this.vehicleType;
            vehicleProductionThread.productionTime = this.productionTime;
            vehicleProductionThread.productionCost = this.productionCost;
            return vehicleProductionThread;
        }
    }
}
