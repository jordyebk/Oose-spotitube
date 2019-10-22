package DAO.Classes;

import DAO.DatabaseConnection.IDatabaseConnection;
import DAO.Interfaces.ITrackDAO;
import DTO.TrackDTO;
import DTO.TracksDTO;
import Exceptions.TrackException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Default
public class TrackDAO implements ITrackDAO {

    private IDatabaseConnection databaseConnection;

    @Inject
    public TrackDAO(IDatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public TracksDTO getAllTracksInPlaylist(int playlistId) throws TrackException {
        String query = "select * from track \n" +
                "where id in (\n" +
                "select trackid \n" +
                "from trackinplaylist \n" +
                "where playlistid = ?)";
        List<TrackDTO> tracks = new ArrayList<>();
        try (
                Connection conn = databaseConnection.getConnection();
                PreparedStatement statement = conn.prepareStatement(query)
        ) {
            statement.setInt(1, playlistId);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                tracks.add(new TrackDTO(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("performer"),
                        rs.getInt("duration"),
                        rs.getString("album"),
                        rs.getInt("playcount"),
                        rs.getString("publicationDate"),
                        rs.getString("description"),
                        rs.getBoolean("offlineAvailable")
                        ));
            }

            return new TracksDTO(tracks);
        } catch (Exception e) {
            e.printStackTrace();

            throw new TrackException();
        }
    }

    public TracksDTO getAllTracksNotInPlaylist(int playlistId) {
        return null; //TODO: Refactoren naar een excecute query functie :)
    }
}
