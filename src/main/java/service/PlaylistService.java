package service;

import dao.interfaces.IPlaylistDAO;
import dao.interfaces.ITrackDAO;
import dao.interfaces.IUserDAO;
import dto.PlaylistDTO;
import dto.PlaylistsDTO;
import dto.TrackDTO;
import dto.TracksDTO;

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
            return Response.status(400).build();
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
            return Response.status(400).build();
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
            return Response.status(400).build();
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
            return Response.status(400).build();
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
            return Response.status(400).build();
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
            return Response.status(400).build();
        }
    }

    @Inject
    public void setPlaylistDAO(IPlaylistDAO playlistDAO) {
        this.playlistDAO = playlistDAO;
    }

    @Inject
    public void setUserDAO(IUserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Inject
    public void setTrackDAO(ITrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }
}
