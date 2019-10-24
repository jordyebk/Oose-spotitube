package ServiceTests;

import DAO.Classes.TrackDAO;
import DAO.Classes.UserDAO;
import DAO.Interfaces.ITrackDAO;
import DAO.Interfaces.IUserDAO;
import Exceptions.TrackException;
import Exceptions.UserNotAuthorizedException;
import Service.TrackService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;

public class TrackServiceTest {

    private static final int PLAYLISTID = 123;
    private static final String TOKEN = "7285cc1b-a50a-47ab-8697-fa301235dff5";
    private static final int STATUSOK = 200;
    private static final int STATUSFORBIDDEN = 403;
    private static final int STATUSBADREQUEST = 400;

    private static TrackService trackService;
    private static ITrackDAO trackDAO;
    private static IUserDAO userDAO;

    @BeforeEach
    public void setupBeforeEach() {
        trackDAO = Mockito.mock(TrackDAO.class);
        userDAO = Mockito.mock(UserDAO.class);
        trackService = new TrackService();
        trackService.setTrackDAO(trackDAO);
        trackService.setUserDAO(userDAO);
    }

    @Test
    public void getAllTracks() throws UserNotAuthorizedException, TrackException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(true);

        Response response = trackService.getAllTracks(PLAYLISTID, TOKEN);

        Assertions.assertEquals(STATUSOK, response.getStatus());
    }

    @Test
    public void getAllTracksUnauthorized() throws UserNotAuthorizedException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(false);

        Response response = trackService.getAllTracks(PLAYLISTID, TOKEN);

        Assertions.assertEquals(STATUSFORBIDDEN, response.getStatus());
    }

    @Test
    public void getAllTracksBadRequest() throws UserNotAuthorizedException, TrackException {
        Mockito.when(userDAO.authorization(TOKEN)).thenReturn(true);
        Mockito.doThrow(new TrackException()).when(trackDAO).getAllTracksNotInPlaylist(PLAYLISTID);

        Response response = trackService.getAllTracks(PLAYLISTID, TOKEN);

        Assertions.assertEquals(STATUSBADREQUEST, response.getStatus());
    }
}
