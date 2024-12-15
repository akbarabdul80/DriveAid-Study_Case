package id.zero.driveaid.domain.model;

public class DataStatus {
    private boolean status;
    private String message;

    // Constructor
    public DataStatus(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    // Getters
    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    // Setters
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    // Optional: Override toString for easy printing
    @Override
    public String toString() {
        return "Response{" +
                "status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
