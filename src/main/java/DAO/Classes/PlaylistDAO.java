package DAO.Classes;

import DAO.DatabaseConnection.IDatabaseConnection;
import DAO.Interfaces.IPlaylistDAO;
import DTO.PlaylistDTO;
import DTO.PlaylistsDTO;
import Exceptions.PlaylistException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDAO implements IPlaylistDAO {

    private IDatabaseConnection databaseConnection;

    @Inject
    public PlaylistDAO(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public PlaylistDAO() {
    }

    public PlaylistsDTO getAllPlaylists(String currentUser) throws PlaylistException {
        String query = "select  playlist.id, playlist.name, playlist.owner, sum(track.duration) as length\n" +
                "from `playlist`\n" +
                "left join trackinplaylist on playlist.id = trackinplaylist.playlistid\n" +
                "left join track on track.id = trackinplaylist.trackid\n" +
                "group by playlist.id";
        List<PlaylistDTO> playlists = new ArrayList<>();
        int totalPlaytime = 0;
        try (
                Connection conn = databaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(query);
        ) {
            ResultSet rs = statement.executeQuery();

            while(rs.next()){
                playlists.add(new PlaylistDTO(rs.getInt("id"), rs.getString("name"), rs.getString("owner").equals(currentUser), null));
                totalPlaytime += rs.getInt("length");
            }

            return new PlaylistsDTO(playlists, totalPlaytime);

        } catch (Exception e) {
            e.printStackTrace();

            throw new PlaylistException();
        }
    }
}
