package Service;

import DAO.Interfaces.IPlaylistDAO;
import DAO.Interfaces.ITrackDAO;
import DAO.Interfaces.IUserDAO;
import DTO.PlaylistsDTO;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/playlists")
public class PlaylistService {

    private IPlaylistDAO playlistDAO;
    private IUserDAO userDAO;
    private ITrackDAO trackDAO;

    public PlaylistService() {
    }

    @GET
    @Produces("application/json")
    public Response getAllPlaylists(@QueryParam("token") String token) {
        try {
            if(!userDAO.authorization(token)){
                return Response.status(403).build();
            }
            PlaylistsDTO result = playlistDAO.getAllPlaylists(userDAO.getUserByToken(token));
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(401).build();
        }
    }

    @GET
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{id}/tracks")
    public Response getAllTracksInPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        try {
            if(!userDAO.authorization(token)){
                return Response.status(403).build();
            }
            return Response.ok().entity(trackDAO.getAllTracksInPlaylist(playlistId)).build();
        } catch (Exception e){
            e.printStackTrace();
            return Response.status(401).build();
        }
    }

    @Inject
    void setPlaylistDAO(IPlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Inject
    void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Inject void setTrackDAO(ITrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
