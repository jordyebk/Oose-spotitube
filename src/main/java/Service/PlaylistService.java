package Service;

import DAO.Interfaces.IPlaylistDAO;
import DAO.Interfaces.IUserDAO;
import DTO.PlaylistDTO;
import DTO.PlaylistsDTO;
import Exceptions.UserNotFoundByTokenException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/playlists")
public class PlaylistService {

    private IPlaylistDAO playlistDAO;
    private IUserDAO userDAO;

    public PlaylistService() {
    }

    @GET
    @Produces("application/json")
    public Response getAllPlaylists(@QueryParam("token") String token) {
        try {
            PlaylistsDTO result = playlistDAO.getAllPlaylists(userDAO.getUserByToken(token));
            return Response.ok().entity(result).build();
        } catch (Exception e) {
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
}
