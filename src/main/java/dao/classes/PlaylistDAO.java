package dao.classes;

import com.mongodb.client.MongoCollection;
import dao.databaseconnection.IDatabaseConnection;
import dao.interfaces.IPlaylistDAO;
import dto.PlaylistDTO;
import dto.PlaylistPOJO;
import dto.PlaylistsDTO;
import dto.TrackDTO;
import exceptions.DeletionException;
import exceptions.InsertionException;
import exceptions.PlaylistException;
import exceptions.UpdateException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

@Default
public class PlaylistDAO implements IPlaylistDAO {

    private IDatabaseConnection database;

    @Inject
    public PlaylistDAO(IDatabaseConnection database) {
        this.database = database;
    }

    public PlaylistDAO() {
    }

    @Override
    public PlaylistsDTO getAllPlaylists(String currentUser) throws PlaylistException {
        try {
            MongoCollection<PlaylistPOJO> playlistCollection = database.getDatabase().getCollection("playlist", PlaylistPOJO.class);

            int totalLength = 0;
            List<PlaylistPOJO> playlistsPojo = playlistCollection.find().into(new ArrayList<>());
            List<PlaylistDTO> playlistsDTO = new ArrayList<>();
            for (PlaylistPOJO playlist : playlistsPojo) {
                playlistsDTO.add(new PlaylistDTO(playlist));
                List<TrackDTO> tracks = playlist.getTracks();
                totalLength += tracks.stream().mapToInt(TrackDTO::getDuration).sum();
            }

            return new PlaylistsDTO(playlistsDTO, totalLength);

        }catch (Exception e) {
            e.printStackTrace();
            throw new PlaylistException();
        }
    }

    @Override
    public void deletePlaylist(int playlistId) throws DeletionException {

    }

    @Override
    public void insertPlaylist(PlaylistDTO dto, String currentUser) throws InsertionException {

    }

    @Override
    public void editPlaylist(PlaylistDTO dto) throws UpdateException {

    }
}
