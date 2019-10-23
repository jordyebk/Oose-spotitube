package Service;

import DAO.Interfaces.IPlaylistDAO;
import DAO.Interfaces.ITrackDAO;
import DAO.Interfaces.IUserDAO;
import DTO.PlaylistDTO;
import DTO.PlaylistsDTO;
import DTO.TrackDTO;
import DTO.TracksDTO;

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
            if (!userDAO.authorization(token)) {
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
    @Produces("application/json")
    @Path("/{id}/tracks")
    public Response getAllTracksInPlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        try {
            if (!userDAO.authorization(token)) {
                return Response.status(403).build();
            }
            TracksDTO result = trackDAO.getAllTracksInPlaylist(playlistId);
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(401).build();
        }
    }

    @DELETE
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{id}")
    public Response deletePlaylist(@PathParam("id") int playlistId, @QueryParam("token") String token) {
        try {
            if (!userDAO.authorization(token)) {
                return Response.status(403).build();
            }
            playlistDAO.deletePlaylist(playlistId);

            PlaylistsDTO result = playlistDAO.getAllPlaylists(userDAO.getUserByToken(token));
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(401).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addPlaylist(PlaylistDTO dto, @QueryParam("token") String token) {
        try {
            if (!userDAO.authorization(token)) {
                return Response.status(403).build();
            }
            playlistDAO.insertPlaylist(dto, userDAO.getUserByToken(token));

            PlaylistsDTO result = playlistDAO.getAllPlaylists(userDAO.getUserByToken(token));
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(401).build();
        }
    }

    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{id}")
    public Response editPlaylist(PlaylistDTO dto, @QueryParam("token") String token, @PathParam("id") int playlistId) {
        try {
            if (!userDAO.authorization(token)) {
                return Response.status(403).build();
            }
            playlistDAO.editPlaylist(dto);

            PlaylistsDTO result = playlistDAO.getAllPlaylists(userDAO.getUserByToken(token));
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(401).build();
        }
    }

    @DELETE
    @Produces("application/json")
    @Path("/{playlistId}/tracks/{trackId}")
    public Response removeTrackFromPlaylist(@QueryParam("token") String token, @PathParam("playlistId") int playlistId, @PathParam("trackId") int trackId) {
        try {
            if (!userDAO.authorization(token)) {
                return Response.status(403).build();
            }
            trackDAO.removeTrackFromPlaylist(playlistId, trackId);

            TracksDTO result = trackDAO.getAllTracksInPlaylist(playlistId);
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(401).build();
        }
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("/{id}/tracks")
    public Response addTrackToPlaylist(@QueryParam("token") String token, @PathParam("id") int playlistId, TrackDTO dto){
        try {
            if (!userDAO.authorization(token)) {
                return Response.status(403).build();
            }
            trackDAO.addTrackToPlaylist(playlistId, dto);

            TracksDTO result = trackDAO.getAllTracksInPlaylist(playlistId);
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

    @Inject
    void setTrackDAO(ITrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
