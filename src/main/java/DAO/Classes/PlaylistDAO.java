package DAO.Classes;

import DAO.DatabaseConnection.IDatabaseConnection;
import DAO.Interfaces.IPlaylistDAO;
import DTO.PlaylistDTO;
import DTO.PlaylistsDTO;
import Exceptions.DeletionException;
import Exceptions.InsertionException;
import Exceptions.PlaylistException;
import Exceptions.UpdateException;

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

    public void deletePlaylist(int playlistId) throws DeletionException {
        String query = "delete from playlist where id = ?";

        try (
                Connection conn = databaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(query);
        ) {
            statement.setInt(1, playlistId);
            statement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            throw new DeletionException("Playlist with id: " + playlistId);
        }
    }

    public void insertPlaylist(PlaylistDTO dto, String currentUser) throws InsertionException {
        String query = "insert into playlist (name, owner) values (?, ?)";

        try (
                Connection conn = databaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(query);
        ) {
            statement.setString(1, dto.getName());
            statement.setString(2, currentUser);
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
            throw new InsertionException("Playlist");
        }
    }

    public void editPlaylist(PlaylistDTO dto) throws UpdateException {
        String query = "update playlist\n" +
                "set name = ?" +
                "where id = ?";

        try (
                Connection conn = databaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(query);
        ) {
            statement.setString(1, dto.getName());
            statement.setInt(2, dto.getId());
            statement.execute();
        } catch (Exception e){
            e.printStackTrace();
            throw new UpdateException("Playlist");
        }
    }
}
