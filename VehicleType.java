
public enum VehicleType {
    CAR("car"),
    MOTORCYCLE("motorcycle"),
    TRUCK("truck");

    private String text;

    VehicleType(String text) {
        this.text = text;
    }

    public String getText() {
        return this.text;
    }

    public static VehicleType fromString(String text) {
        for (VehicleType b : VehicleType.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
