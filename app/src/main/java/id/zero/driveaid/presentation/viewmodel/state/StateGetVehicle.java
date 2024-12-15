package id.zero.driveaid.presentation.viewmodel.state;

import java.util.List;

import id.zero.driveaid.domain.model.DataVehicle;

public class StateGetVehicle {
    private final Status status;
    private final List<DataVehicle> vehicles;
    private final String errorMesssage;

    public StateGetVehicle(Status status, List<DataVehicle> vehicles, String errorMesssage) {
        this.status = status;
        this.vehicles = vehicles;
        this.errorMesssage = null;
    }

    public Status getStatus() {
        return status;
    }

    public List<DataVehicle> getVehicles() {
        return vehicles;
    }

    public String getErrorMesssage() {
        return errorMesssage;
    }
}
