package ServiceTests;

import DAO.Classes.PlaylistDAO;
import DAO.Classes.TrackDAO;
import DAO.Classes.UserDAO;
import DAO.Interfaces.IPlaylistDAO;
import DAO.Interfaces.ITrackDAO;
import DAO.Interfaces.IUserDAO;
import DTO.PlaylistDTO;
import DTO.TrackDTO;
import Exceptions.UserNotAuthorizedException;
import Service.PlaylistService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class PlaylistServiceTest {

    private static final int PLAYLISTID = 123;
    private static final int TRACKID = 456;
    private static final String TOKEN = "7285cc1b-a50a-47ab-8697-fa301235dff5";
    private static final int STATUSOK = 200;
    private static final int STATUSFORBIDDEN = 403;
    private static final int STATUSBADREQUEST = 400;
    private static PlaylistDTO playlistDTO;
    private static TrackDTO trackDTO;

    private static PlaylistService playlistService;
    private static IPlaylistDAO playlistDAO;
    private static IUserDAO userDAO;
    private static ITrackDAO trackDAO;

    @BeforeEach
    private void setupBeforeEach() {
        playlistDAO = Mockito.mock(PlaylistDAO.class);
        userDAO = Mockito.mock(UserDAO.class);
        trackDAO = Mockito.mock(TrackDAO.class);

        playlistService = new PlaylistService();
        playlistService.setPlaylistDAO(playlistDAO);
        playlistService.setUserDAO(userDAO);
        playlistService.setTrackDAO(trackDAO);

        playlistDTO = new PlaylistDTO(PLAYLISTID, "Name", true, null);
        trackDTO = new TrackDTO();
    }

    @Test
    public void getAllPlaylists() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(true);

        Response response = playlistService.getAllPlaylists(TOKEN);

        Assertions.assertEquals(STATUSOK, response.getStatus());
    }

    @Test
    public void getAllPlaylistsUnauthorized() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(false);

        Response response = playlistService.getAllPlaylists(TOKEN);

        Assertions.assertEquals(STATUSFORBIDDEN, response.getStatus());
    }

    @Test
    public void getAllTracksInPlaylist() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(true);

        Response response = playlistService.getAllTracksInPlaylist(PLAYLISTID, TOKEN);

        Assertions.assertEquals(STATUSOK, response.getStatus());
    }

    @Test
    public void getAllTracksInPlaylistUnauthorized() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(false);

        Response response = playlistService.getAllTracksInPlaylist(PLAYLISTID, TOKEN);

        Assertions.assertEquals(STATUSFORBIDDEN, response.getStatus());
    }

    @Test
    public void deletePlaylist() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(true);

        Response response = playlistService.deletePlaylist(PLAYLISTID, TOKEN);

        Assertions.assertEquals(STATUSOK, response.getStatus());
    }

    @Test
    public void deletePlaylistUnauthorized() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(false);

        Response response = playlistService.deletePlaylist(PLAYLISTID, TOKEN);

        Assertions.assertEquals(STATUSFORBIDDEN, response.getStatus());
    }

    @Test
    public void addPlaylist() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(true);

        Response response = playlistService.addPlaylist(playlistDTO, TOKEN);

        Assertions.assertEquals(STATUSOK, response.getStatus());
    }

    @Test
    public void addPlaylistUnauthorized() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(false);

        Response response = playlistService.addPlaylist(playlistDTO, TOKEN);

        Assertions.assertEquals(STATUSFORBIDDEN, response.getStatus());
    }

    @Test
    public void editPlaylist() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(true);

        Response response = playlistService.editPlaylist(playlistDTO, TOKEN, PLAYLISTID);

        Assertions.assertEquals(STATUSOK, response.getStatus());
    }

    @Test
    public void editPlaylistUnauthorized() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(false);

        Response response = playlistService.editPlaylist(playlistDTO, TOKEN, PLAYLISTID);

        Assertions.assertEquals(STATUSFORBIDDEN, response.getStatus());
    }

    @Test
    public void removeTrackFromPlaylist() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(true);

        Response response = playlistService.removeTrackFromPlaylist(TOKEN, PLAYLISTID, TRACKID);

        Assertions.assertEquals(STATUSOK, response.getStatus());
    }

    @Test
    public void removeTrackFromPlaylistUnauthorized() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(false);

        Response response = playlistService.removeTrackFromPlaylist(TOKEN, PLAYLISTID, TRACKID);

        Assertions.assertEquals(STATUSFORBIDDEN, response.getStatus());
    }

    @Test
    public void addTrackToPlaylist() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(true);

        Response response = playlistService.addTrackToPlaylist(TOKEN, PLAYLISTID, trackDTO);

        Assertions.assertEquals(STATUSOK, response.getStatus());
    }

    @Test
    public void addTrackToPlaylistUnauthorized() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(false);

        Response response = playlistService.addTrackToPlaylist(TOKEN, PLAYLISTID, trackDTO);

        Assertions.assertEquals(STATUSFORBIDDEN, response.getStatus());
    }
}
