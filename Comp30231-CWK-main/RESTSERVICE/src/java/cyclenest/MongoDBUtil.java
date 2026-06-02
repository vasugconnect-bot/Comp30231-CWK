package cyclenest;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBUtil {

    private static final String CONNECTION_STRING =
        "mongodb+srv://vasugoyal:ASDwer345@cluster0.vodbqt7.mongodb.net/?appName=Cluster0";

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase getDatabase() {
        if (database == null) {
            try {
                mongoClient = MongoClients.create(CONNECTION_STRING);
                database = mongoClient.getDatabase("cyclenestdb");
                System.out.println("MongoDB connection established");
            } catch (Exception e) {
                System.err.println("MongoDB connection failed");
                e.printStackTrace();
                throw e; // rethrow so we see the real error
            }
        }
        return database;
    }
}
