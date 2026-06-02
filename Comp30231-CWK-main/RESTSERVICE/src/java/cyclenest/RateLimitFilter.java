package cyclenest;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Provider
public class RateLimitFilter implements ContainerRequestFilter {

    private static final int MAX_REQUESTS = 50;
    private static final ConcurrentHashMap<String, AtomicInteger> requests = new ConcurrentHashMap<>();

    @Override
    public void filter(ContainerRequestContext requestContext) {

        String ip = requestContext.getHeaders()
                .getFirst("X-Forwarded-For");

        if (ip == null) {
            ip = "local";
        }

        requests.putIfAbsent(ip, new AtomicInteger(0));

        int count = requests.get(ip).incrementAndGet();

        if (count > MAX_REQUESTS) {
            requestContext.abortWith(
                Response.status(429)
                        .entity("Too many requests – rate limit exceeded")
                        .build()
            );
        }
    }
}
