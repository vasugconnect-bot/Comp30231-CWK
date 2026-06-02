package cyclenest;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        new DataLoader().loadData();

        // Register REST resources
        register(ItemResource.class);
        register(RequestsResource.class);
        register(DistanceResource.class);
        register(ApiKeyFilter.class);
        


        // Forcing Jackson and disable MOXY
        register(JacksonFeature.class);
    }
}
