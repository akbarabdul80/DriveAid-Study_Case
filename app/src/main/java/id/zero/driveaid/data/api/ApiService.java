package id.zero.driveaid.data.api;

import java.util.List;

import id.zero.driveaid.domain.model.DataReportVehicle;
import id.zero.driveaid.domain.model.DataStatus;
import id.zero.driveaid.domain.model.DataVehicle;
import io.reactivex.rxjava3.core.Single;
import okhttp3.MultipartBody;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {
    @GET("list_vehicle")
    Single<List<DataVehicle>> getVehicles();

    @GET("read_all_laporan")
    Single<List<DataReportVehicle>> getReportVehicle(
            @Query("licenseNumber") String licenseNumber,
            @Query("userId") String userId
    );

    @Multipart
    @POST("add_laporan")
    Single<DataStatus> addReportVehicle(
            @Part MultipartBody.Part vehicleId,
            @Part MultipartBody.Part note,
            @Part MultipartBody.Part photo,
            @Part MultipartBody.Part userId
    );
}
