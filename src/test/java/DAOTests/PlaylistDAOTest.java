package DAOTests;

import DAO.Classes.PlaylistDAO;
import DAO.DatabaseConnection.IDatabaseConnection;
import DAO.Interfaces.IPlaylistDAO;
import DTO.PlaylistDTO;
import DTO.PlaylistsDTO;
import Exceptions.PlaylistException;
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

public class PlaylistDAOTest {

    private static IDatabaseConnection databaseConnection;
    private static IPlaylistDAO playlistDAO;

    private PreparedStatement statement;
    private ResultSet rs;
    private final static String TOKEN = "7285cc1b-a50a-47ab-8697-fa301235dff5";
    private final static String USER = "Test_User";

    @BeforeAll
    public static void setupBeforeAll() {
        databaseConnection = Mockito.mock(IDatabaseConnection.class);
        playlistDAO = new PlaylistDAO(databaseConnection);
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
    public void getAllPlaylists() throws SQLException, PlaylistException {
        List<PlaylistDTO> playlists = new ArrayList<>();

        Mockito.when(statement.executeQuery()).thenReturn(rs);

        Assertions.assertEquals(playlistDAO.getAllPlaylists(USER).getPlaylists(), playlists);
    }

}
