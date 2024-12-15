package id.zero.driveaid.presentation.viewmodel.state;

import java.util.List;

import id.zero.driveaid.domain.model.DataReportVehicle;
import id.zero.driveaid.domain.model.DataVehicle;

public class StateGetReportVehicle {
    private final Status status;
    private final List<DataReportVehicle> report;
    private final String errorMesssage;

    public StateGetReportVehicle(Status status, List<DataReportVehicle> report, String errorMesssage) {
        this.status = status;
        this.report = report;
        this.errorMesssage = null;
    }

    public Status getStatus() {
        return status;
    }

    public List<DataReportVehicle> getReport() {
        return report;
    }

    public String getErrorMesssage() {
        return errorMesssage;
    }
}
