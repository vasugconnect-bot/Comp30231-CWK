package cyclenest;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

@Path("items")
@Produces(MediaType.APPLICATION_JSON)
public class ItemResource {

    @GET
    public List<Item> getItems(
            @QueryParam("available") Boolean available,
            @QueryParam("maxPrice") Double maxPrice) {

        if (available != null && available && maxPrice != null) {
            return ItemRepository.filterByMaxPrice(maxPrice);
        }

        if (available != null && available) {
            return ItemRepository.getAvailableItems();
        }

        return ItemRepository.getAllItems();
    }
}
