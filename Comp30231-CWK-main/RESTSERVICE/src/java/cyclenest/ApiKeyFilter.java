package cyclenest;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class ApiKeyFilter implements ContainerRequestFilter {

    private static final String API_KEY = "cyclenest-secret-key";

    @Override
    public void filter(ContainerRequestContext requestContext) {

        String key = requestContext.getHeaderString("X-API-KEY");

        if (key == null || !key.equals(API_KEY)) {
            requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .entity("Missing or invalid API key")
                        .build()
            );
        }
    }
}
