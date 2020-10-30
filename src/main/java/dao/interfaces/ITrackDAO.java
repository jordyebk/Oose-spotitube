package dao.interfaces;

import dto.TrackDTO;
import dto.TracksDTO;
import exceptions.DeletionException;
import exceptions.InsertionException;
import exceptions.TrackException;
import org.bson.types.ObjectId;

public interface ITrackDAO {

    TracksDTO getAllTracksInPlaylist(int playlistId) throws TrackException;
    TracksDTO getAllTracksNotInPlaylist(int playlistId) throws TrackException;
    void removeTrackFromPlaylist(int playlistId, int trackId) throws DeletionException;
    void addTrackToPlaylist(int playlistId, TrackDTO dto) throws InsertionException;
}
