package cyclenest;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * MongoDB-backed repository for Items
 * Includes in-memory caching to improve QoS under high load
 */
public class ItemRepository {

    private static MongoCollection<Document> items;

    // -------------------------------
    // Simple in-memory cache (QoS)
    // -------------------------------
    private static List<Item> cachedItems = null;
    private static long lastCacheTime = 0;
    private static final long CACHE_TTL_MS = 30_000; // 30 seconds

    // Lazy initialisation to avoid startup failure
    private static MongoCollection<Document> getCollection() {
        if (items == null) {
            MongoDatabase db = MongoDBUtil.getDatabase();
            items = db.getCollection("items");
        }
        return items;
    }

    // -------------------------------
    // ADD ITEM (invalidate cache)
    // -------------------------------
    public static void addItem(Item item) {

        Document doc = new Document()
                .append("itemId", item.getItemId())
                .append("ownerId", item.getOwnerId())
                .append("name", item.getName())
                .append("category", item.getCategory())
                .append("latitude", item.getLatitude())
                .append("longitude", item.getLongitude())
                .append("location", item.getLocation())
                .append("dailyRate", item.getDailyRate())
                .append("available", item.isAvailable())
                .append("condition", item.getCondition())
                .append("description", item.getDescription());

        getCollection().insertOne(doc);

        // Invalidate cache after write
        cachedItems = null;
    }

    // -------------------------------
    // GET ALL ITEMS (cached)
    // -------------------------------
    public static List<Item> getAllItems() {

        if (cachedItems != null && !isCacheExpired()) {
            return cachedItems;
        }

        List<Item> result = new ArrayList<>();

        for (Document d : getCollection().find()) {
            result.add(documentToItem(d));
        }

        cachedItems = result;
        lastCacheTime = System.currentTimeMillis();

        return result;
    }

    // -------------------------------
    // GET AVAILABLE ITEMS (no cache)
    // -------------------------------
    public static List<Item> getAvailableItems() {

        List<Item> result = new ArrayList<>();

        for (Document d : getCollection().find(Filters.eq("available", true))) {
            result.add(documentToItem(d));
        }
        return result;
    }

    // -------------------------------
    // FILTER BY PRICE (no cache)
    // -------------------------------
    public static List<Item> filterByMaxPrice(double maxPrice) {

        List<Item> result = new ArrayList<>();

        for (Document d : getCollection().find(
                Filters.and(
                        Filters.eq("available", true),
                        Filters.lte("dailyRate", maxPrice)
                ))) {
            result.add(documentToItem(d));
        }
        return result;
    }

    // -------------------------------
    // CACHE HELPER
    // -------------------------------
    private static boolean isCacheExpired() {
        return System.currentTimeMillis() - lastCacheTime > CACHE_TTL_MS;
    }

    // -------------------------------
    // DOCUMENT → ITEM
    // -------------------------------
    private static Item documentToItem(Document d) {

        return new Item(
                d.getString("itemId"),
                d.getString("ownerId"),
                d.getString("name"),
                d.getString("category"),
                d.getDouble("latitude"),
                d.getDouble("longitude"),
                d.getString("location"),
                d.getDouble("dailyRate"),
                d.getBoolean("available"),
                d.getString("condition"),
                d.getString("description")
        );
    }
}
