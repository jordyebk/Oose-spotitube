package DAOTests;

import DAO.Classes.TrackDAO;
import DAO.DatabaseConnection.IDatabaseConnection;
import DAO.Interfaces.ITrackDAO;
import DTO.TrackDTO;
import Exceptions.DeletionException;
import Exceptions.InsertionException;
import Exceptions.TrackException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDAOTest {

    private static IDatabaseConnection databaseConnection;
    private static ITrackDAO trackDAO;
    private static TrackDTO trackDTO;

    private PreparedStatement statement;
    private ResultSet rs;
    private static final int TRACKID = 123;
    private static final int PLAYLISTID = 456;

    @BeforeAll
    public static void setupBeforeAll() {
        databaseConnection = Mockito.mock(IDatabaseConnection.class);
        trackDAO = new TrackDAO(databaseConnection);
        trackDTO = new TrackDTO();
    }

    @BeforeEach
    public void setupBeforeEach() throws Exception {
        Connection conn = Mockito.mock(Connection.class);
        Mockito.when(databaseConnection.getConnection()).thenReturn(conn);

        statement = Mockito.mock(PreparedStatement.class);
        Mockito.when(conn.prepareStatement(Mockito.anyString())).thenReturn(statement);

        rs = Mockito.mock(ResultSet.class);
    }

    @Test
    public void getAllTracksInPlaylist() throws SQLException, TrackException {
        List<TrackDTO> tracks = new ArrayList<>();

        Mockito.when(statement.executeQuery()).thenReturn(rs);

        Assertions.assertEquals(trackDAO.getAllTracksInPlaylist(TRACKID).getTracks(), tracks);
    }

    @Test
    public void getAllTracksNotInPlaylist() throws SQLException, TrackException {
        List<TrackDTO> tracks = new ArrayList<>();

        Mockito.when(statement.executeQuery()).thenReturn(rs);

        Assertions.assertEquals(trackDAO.getAllTracksNotInPlaylist(TRACKID).getTracks(), tracks);
    }

    @Test
    public void executeGetTracksQueryThrowsException() throws SQLException {
        Mockito.when(statement.executeQuery()).thenThrow(new SQLException());
        Assertions.assertThrows(TrackException.class, () -> trackDAO.getAllTracksNotInPlaylist(TRACKID));
    }

    @Test
    public void removeTrackFromPlaylistThrowsException() throws SQLException {
        Mockito.when(statement.execute()).thenThrow(new SQLException());
        Assertions.assertThrows(DeletionException.class, () -> trackDAO.removeTrackFromPlaylist(PLAYLISTID, TRACKID));
    }

    @Test
    public void addTrackToPlaylistThrowsException() throws SQLException {
        Mockito.when(statement.execute()).thenThrow(new SQLException());
        Assertions.assertThrows(InsertionException.class, () -> trackDAO.addTrackToPlaylist(PLAYLISTID, trackDTO));
    }
}
