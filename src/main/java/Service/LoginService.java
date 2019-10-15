package Service;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/login")
public class LoginService {
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response loginUser(){
        return Response.status(402).build();
    }

    @GET
    @Produces("application/json")
    public Response test(){

        return Response.status(401).build();
    }
}
