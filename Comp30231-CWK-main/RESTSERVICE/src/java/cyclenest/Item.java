package cyclenest;

public class Item {

    private String itemId;
    private String ownerId;
    private String name;
    private String category;
    private String location;
    private double dailyRate;
    private boolean available;
    private String condition;
    private String description;
    private double latitude;
    private double longitude;


    // No-args constructor (REQUIRED for Jackson)
    public Item() {
    }

    // constructor
    public Item(String itemId, String ownerId, String name, String category,
            
            double latitude, double longitude,    String location, double dailyRate, boolean available,
                String condition, String description) {
        this.itemId = itemId;
        this.ownerId = ownerId;
        this.name = name;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.location = location;
        this.dailyRate = dailyRate;
        this.available = available;
        this.condition = condition;
        this.description = description;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(double dailyRate) {
        this.dailyRate = dailyRate;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}
