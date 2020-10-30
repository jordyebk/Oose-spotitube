package dao.classes;

import com.mongodb.client.MongoCollection;
import dao.databaseconnection.IDatabaseConnection;
import dao.interfaces.ITrackDAO;
import dto.*;
import exceptions.DeletionException;
import exceptions.InsertionException;
import exceptions.TrackException;
import org.bson.Document;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

import java.util.ArrayList;
import java.util.Iterator;
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
        try {
            TracksDTO tracksInPlaylist = getAllTracksInPlaylist(playlistId);
            ArrayList<Integer> ids = new ArrayList<Integer>();
            tracksInPlaylist.getTracks().forEach( trackDTO -> ids.add(trackDTO.getId()));

            MongoCollection<TrackPOJO> trackCollection = database.getDatabase().getCollection("track", TrackPOJO.class);
            List<TrackPOJO> trackPOJOS = trackCollection.find(nin("trackId", ids)).into(new ArrayList<>());

            List<TrackDTO> tracks = new ArrayList<>();
            trackPOJOS.forEach( trackPOJO -> tracks.add(new TrackDTO(trackPOJO)));
            return new TracksDTO(tracks);
        }catch (Exception e){
            e.printStackTrace();
            throw new TrackException();
        }
    }

    @Override
    public void removeTrackFromPlaylist(int playlistId, int trackId) throws DeletionException {
        try{
            MongoCollection<PlaylistPOJO> playlistCollection = database.getDatabase().getCollection("playlist", PlaylistPOJO.class);
            PlaylistPOJO playlist = playlistCollection.find(eq("playlistId", playlistId)).first();

            if (playlist == null){
                throw new Exception();
            }
            playlist.getTracks().removeIf(trackPOJO -> trackPOJO.getTrackId() == trackId);
            Document filterByPlaylistId = new Document("_id", playlist.getId());
            playlistCollection.findOneAndReplace(filterByPlaylistId, playlist);
        }catch (Exception e){
            e.printStackTrace();
            throw new DeletionException("PlaylistID: " + playlistId + " TrackID: " + trackId);
        }
    }

    @Override
    public void addTrackToPlaylist(int playlistId, TrackDTO dto) throws InsertionException {
        try {
            MongoCollection<PlaylistPOJO> playlistCollection = database.getDatabase().getCollection("playlist", PlaylistPOJO.class);
            PlaylistPOJO playlist = playlistCollection.find(eq("playlistId", playlistId)).first();

            MongoCollection<TrackPOJO> trackCollection = database.getDatabase().getCollection("track", TrackPOJO.class);
            TrackPOJO track = trackCollection.find(eq("trackId", dto.getId())).first();

            if (playlist == null || track == null){
                throw new Exception();
            }
            playlist.getTracks().add(track);
            Document filterByPlaylistId = new Document("_id", playlist.getId());
            playlistCollection.findOneAndReplace(filterByPlaylistId, playlist);
        }catch (Exception e) {
            e.printStackTrace();
            throw new InsertionException("Track with ID: " + dto.getId() + " In playlist: " + playlistId);
        }
    }
}
