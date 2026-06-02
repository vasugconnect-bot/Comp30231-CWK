package cyclenest;

import javax.annotation.PostConstruct;

/**
 * Loads sample data when application starts
 */
public class DataLoader {

    @PostConstruct
    public void loadData() {

        try {
            // Prevent duplicate inserts on restart
            if (!ItemRepository.getAllItems().isEmpty()) {
                System.out.println("Items already exist. Skipping data load.");
                return;
            }

            ItemRepository.addItem(new Item(
                    "item001", "owner001", "Mountain Bike", "bike",
                    12.9716, 77.5946,
                    "NG1", 12.5, true, "good",
                    "Well maintained mountain bike"));

            ItemRepository.addItem(new Item(
                    "item002", "owner002", "Road Bike", "bike",
                    12.9762, 77.6033,
                    "NG7", 10.0, true, "excellent",
                    "Lightweight road bike"));

            ItemRepository.addItem(new Item(
                    "item003", "owner003", "DSLR Camera", "camera",
                    12.9665, 77.6068,
                    "NG2", 15.0, false, "good",
                    "Canon DSLR with lens"));

            ItemRepository.addItem(new Item(
                    "item004", "owner004", "Tent (2-person)", "camping",
                    12.9200, 77.5800,
                    "NG5", 8.0, true, "fair",
                    "Compact camping tent"));

            ItemRepository.addItem(new Item(
                    "item005", "owner005", "Electric Scooter", "scooter",
                    12.9718, 77.5937,
                    "NG1", 18.0, true, "excellent",
                    "Battery lasts 25km"));

            System.out.println("CycleNest sample items loaded into MongoDB");

        } catch (Exception e) {
            // Never crash application startup
            System.err.println("DataLoader failed – application will still start");
            e.printStackTrace();
        }
    }
}
