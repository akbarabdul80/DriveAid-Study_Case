package id.zero.driveaid.presentation.viewmodel.state;

import java.util.List;

import id.zero.driveaid.domain.model.DataReportVehicle;
import id.zero.driveaid.domain.model.DataStatus;

public class StateCreateReportVehicle {
    private final Status status;
    private final DataStatus result;
    private final String errorMesssage;

    public StateCreateReportVehicle(Status status, DataStatus result, String errorMesssage) {
        this.status = status;
        this.result = result;
        this.errorMesssage = null;
    }

    public Status getStatus() {
        return status;
    }

    public DataStatus getReport() {
        return result;
    }

    public String getErrorMesssage() {
        return errorMesssage;
    }
}
