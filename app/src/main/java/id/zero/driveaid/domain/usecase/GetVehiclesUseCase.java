package id.zero.driveaid.domain.usecase;

import java.io.File;
import java.util.List;

import id.zero.driveaid.domain.model.DataReportVehicle;
import id.zero.driveaid.domain.model.DataStatus;
import id.zero.driveaid.domain.model.DataVehicle;
import id.zero.driveaid.data.repository.VehicleRepository;
import io.reactivex.rxjava3.core.Single;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;


public class GetVehiclesUseCase {
    private final VehicleRepository repository;

    public GetVehiclesUseCase(VehicleRepository repository) {
        this.repository = repository;
    }

    public Single<List<DataVehicle>> getVehicle() {
        return repository.getVehicles();
    }

    public Single<List<DataReportVehicle>> getReportVehicle(
            String licenseNumber,
            String userId
    ) {
        return repository.getReportVehicle(
                licenseNumber,
                userId
        );
    }

    public Single<DataStatus> addReportVehicle(
            String vehicleId,
            String note,
            File photoFile,
            String userId
    ) {
        return repository.addReportVehicle(
                vehicleId,
                note,
                photoFile,
                userId
        );
    }
}
