package cyclenest;

import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("requests")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RequestsResource {

    @POST
    public RentalRequest createRequest(RentalRequest request) {
        return RequestRepository.create(request);
    }

    @GET
    public List<RentalRequest> getRequests() {
        return RequestRepository.getAll();
    }

    @PUT
    @Path("{id}/cancel")
    public Response cancelRequest(@PathParam("id") String id) {

        RentalRequest r = RequestRepository.cancel(id);

        if (r == null) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"error\":\"Request not found\"}")
                    .build();
        }

        return Response.ok(r).build();
    }
}