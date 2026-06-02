package cyclenest;

import java.awt.PageAttributes.MediaType;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("distance")
public class DistanceResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getDistance(
            @QueryParam("itemId") String itemId,
            @QueryParam("userLat") double userLat,
            @QueryParam("userLon") double userLon) {

        // 1️⃣ Get item coordinates (HARDCODED to match your ItemsResource)
        double itemLat;
        double itemLon;

        switch (itemId) {
            case "item001":
                itemLat = 12.9716;
                itemLon = 77.5946;
                break;
            case "item002":
                itemLat = 12.9750;
                itemLon = 77.5990;
                break;
            case "item003":
                itemLat = 12.9680;
                itemLon = 77.6010;
                break;
            case "item004":
                itemLat = 12.9725;
                itemLon = 77.5900;
                break;
            case "item005":
                itemLat = 12.9780;
                itemLon = 77.6030;
                break;
            default:
                return "{ \"error\": \"Item not found\" }";
        }

        // 2️⃣ Call ORS
        try {
            double distance = ORSClient.getDistance(
                    itemLon, itemLat,
                    userLon, userLat
            );

            return """
            {
              "itemId": "%s",
              "distance_meters": %.2f
            }
            """.formatted(itemId, distance);

        } catch (Exception e) {
            return "{ \"error\": \"Distance service unavailable\" }";
        }
    }
}
