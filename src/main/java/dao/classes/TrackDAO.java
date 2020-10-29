package dao.classes;

import dao.databaseconnection.IDatabaseConnection;
import dao.interfaces.ITrackDAO;
import dto.TrackDTO;
import dto.TracksDTO;
import exceptions.DeletionException;
import exceptions.InsertionException;
import exceptions.TrackException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class TrackDAO implements ITrackDAO {

    private IDatabaseConnection database;

    @Inject
    public TrackDAO(IDatabaseConnection database) {
        this.database = database;
    }

    @Override
    public TracksDTO getAllTracksInPlaylist(int playlistId) throws TrackException {
        return null;
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
