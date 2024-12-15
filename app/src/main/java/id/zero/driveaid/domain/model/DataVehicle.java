package id.zero.driveaid.domain.model;

public class DataVehicle {
    private String vehicleId;
    private String licenseNumber;
    private String type;

    public DataVehicle(String vehicleId, String licenseNumber, String type) {
        this.vehicleId = vehicleId;
        this.licenseNumber = licenseNumber;
        this.type = type;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public String getType() {
        return type;
    }
}
