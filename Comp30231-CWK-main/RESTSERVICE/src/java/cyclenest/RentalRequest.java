package cyclenest;

public class RentalRequest {

    private String requestId;
    private String itemId;
    private String userId;
    private String status;        // pending, cancelled
    private String requestDate;

    // No-args constructor (required for Jackson)
    public RentalRequest() {
    }

    // constructor
    public RentalRequest(String requestId, String itemId, String userId,
                         String status, String requestDate) {
        this.requestId = requestId;
        this.itemId = itemId;
        this.userId = userId;
        this.status = status;
        this.requestDate = requestDate;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }
}
