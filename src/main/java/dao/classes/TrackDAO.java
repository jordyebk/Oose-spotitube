package dao.classes;

import com.mongodb.client.MongoCollection;
import dao.databaseconnection.IDatabaseConnection;
import dao.interfaces.ITrackDAO;
import dto.*;
import exceptions.DeletionException;
import exceptions.InsertionException;
import exceptions.TrackException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Filters.*;

@Default
public class TrackDAO implements ITrackDAO {

    private IDatabaseConnection database;

    @Inject
    public TrackDAO(IDatabaseConnection database) {
        this.database = database;
    }

    @Override
    public TracksDTO getAllTracksInPlaylist(int playlistId) throws TrackException {
        try {
            MongoCollection<PlaylistPOJO> playlistCollection = database.getDatabase().getCollection("playlist", PlaylistPOJO.class);
            PlaylistPOJO playlist = playlistCollection.find(eq("playlistId", playlistId)).first();

            if (playlist != null){
                List<TrackDTO> tracks = new ArrayList<>();
                playlist.getTracks().forEach( trackPOJO -> tracks.add(new TrackDTO(trackPOJO)));
                return new TracksDTO(tracks);
            }
            throw new TrackException();

        }catch (Exception e){
            e.printStackTrace();
            throw new TrackException();
        }
    }

    @Override
    public TracksDTO getAllTracksNotInPlaylist(int playlistId) throws TrackException {
        return null;
    }

    @Override
    public void removeTrackFromPlaylist(int playlistId, int trackId) throws DeletionException {
        
    }

    @Override
    public void addTrackToPlaylist(int playlistId, TrackDTO dto) throws InsertionException {

    }
}
