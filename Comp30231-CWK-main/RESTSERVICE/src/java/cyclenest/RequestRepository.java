package cyclenest;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RequestRepository {

    private static final MongoDatabase db = MongoDBUtil.getDatabase();
    private static final MongoCollection<Document> requests =
            db.getCollection("requests");

    public static RentalRequest create(RentalRequest req) {

        String id = "req-" + UUID.randomUUID();

        req.setRequestId(id);
        req.setStatus("pending");

        Document doc = new Document()
                .append("requestId", id)
                .append("itemId", req.getItemId())
                .append("userId", req.getUserId())
                .append("status", req.getStatus())
                .append("requestDate", req.getRequestDate());

        requests.insertOne(doc);
        return req;
    }

    public static List<RentalRequest> getAll() {
        List<RentalRequest> result = new ArrayList<>();
        for (Document d : requests.find()) {
            result.add(documentToRequest(d));
        }
        return result;
    }

    public static RentalRequest cancel(String id) {

        Document d = requests.find(Filters.eq("requestId", id)).first();

        if (d == null) return null;

        requests.updateOne(
                Filters.eq("requestId", id),
                new Document("$set", new Document("status", "cancelled"))
        );

        d.put("status", "cancelled");
        return documentToRequest(d);
    }

    private static RentalRequest documentToRequest(Document d) {
        return new RentalRequest(
                d.getString("requestId"),
                d.getString("itemId"),
                d.getString("userId"),
                d.getString("status"),
                d.getString("requestDate")
        );
    }
}
