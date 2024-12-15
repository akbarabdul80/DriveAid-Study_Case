package id.zero.driveaid.data.repository;

import java.io.File;
import java.util.List;

import id.zero.driveaid.data.api.ApiClient;
import id.zero.driveaid.data.api.ApiService;
import id.zero.driveaid.domain.model.DataReportVehicle;
import id.zero.driveaid.domain.model.DataStatus;
import id.zero.driveaid.domain.model.DataVehicle;
import io.reactivex.rxjava3.core.Single;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class VehicleRepository {
    private final ApiService apiService;

    public VehicleRepository() {
        apiService = ApiClient.getRetrofit().create(ApiService.class);
    }

    public Single<List<DataVehicle>> getVehicles() {
        return apiService.getVehicles();
    }

    public Single<List<DataReportVehicle>> getReportVehicle(String licenseNumber, String userId) {
        return apiService.getReportVehicle(licenseNumber, userId);
    }

    public Single<DataStatus> addReportVehicle(String vehicleId, String note, File photoFile, String userId) {

        MultipartBody.Part photo = MultipartBody.Part.createFormData(
                "photo",
                photoFile.getName(),
                RequestBody.create(MediaType.parse("image/*"), photoFile)
        );

        MultipartBody.Part vehicleIdMultipart = MultipartBody.Part.createFormData("vehicleId", vehicleId);
        MultipartBody.Part noteMultipart = MultipartBody.Part.createFormData("note", note);
        MultipartBody.Part userIdMultipart = MultipartBody.Part.createFormData("userId", userId);

        return apiService.addReportVehicle(vehicleIdMultipart, noteMultipart, photo, userIdMultipart);
    }
}
