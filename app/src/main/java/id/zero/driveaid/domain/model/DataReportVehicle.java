package id.zero.driveaid.domain.model;

import com.google.gson.annotations.SerializedName;

public class DataReportVehicle {

    @SerializedName("reportId")
    private String reportId;

    @SerializedName("vehicleId")
    private String vehicleId;

    @SerializedName("vehicleLicenseNumber")
    private String vehicleLicenseNumber;

    @SerializedName("vehicleName")
    private String vehicleName;

    @SerializedName("note")
    private String note;

    @SerializedName("photo")
    private String photo;

    @SerializedName("createdAt")
    private String createdAt;

    @SerializedName("createdBy")
    private String createdBy;

    @SerializedName("reportStatus")
    private String reportStatus;

    // Getters and Setters
    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleLicenseNumber() {
        return vehicleLicenseNumber;
    }

    public void setVehicleLicenseNumber(String vehicleLicenseNumber) {
        this.vehicleLicenseNumber = vehicleLicenseNumber;
    }

    public String getVehicleName() {
        return vehicleName;
    }

    public void setVehicleName(String vehicleName) {
        this.vehicleName = vehicleName;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId='" + reportId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", vehicleLicenseNumber='" + vehicleLicenseNumber + '\'' +
                ", vehicleName='" + vehicleName + '\'' +
                ", note='" + note + '\'' +
                ", photo='" + photo + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", reportStatus='" + reportStatus + '\'' +
                '}';
    }
}
