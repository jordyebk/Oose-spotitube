package Service;

import DAO.Interfaces.ITrackDAO;
import DAO.Interfaces.IUserDAO;
import DTO.TracksDTO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("/tracks")
public class TrackService {

    private ITrackDAO trackDAO;
    private IUserDAO userDAO;

    public TrackService() {
    }

    @GET
    @Produces("application/json")
    public Response getAllTracks(@QueryParam("forPlaylist") int playlistId, @QueryParam("token") String token) {
        try {
            if(!userDAO.authorization(token)){
                return Response.status(403).build();
            }
            TracksDTO result = trackDAO.getAllTracksNotInPlaylist(playlistId);

            return Response.ok().entity(result).build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.status(400).build();
        }
    }

    @Inject public void setTrackDAO(ITrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    @Inject
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }
}
