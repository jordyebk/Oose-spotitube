package Service;

import DAO.Interfaces.IPlaylistDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/playlists")
@Produces("application/json")
public class PlaylistService {

    private IPlaylistDAO playlistDAO;

    @Inject
    public PlaylistService(IPlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @GET
    public Response getAllPlaylists() {
        return null;
    }
}
