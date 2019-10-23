package DAO.Interfaces;

import DTO.TrackDTO;
import DTO.TracksDTO;
import Exceptions.DeletionException;
import Exceptions.InsertionException;
import Exceptions.TrackException;

public interface ITrackDAO {

    TracksDTO getAllTracksInPlaylist(int playlistId) throws TrackException;
    TracksDTO getAllTracksNotInPlaylist(int playlistId) throws TrackException;
    void removeTrackFromPlaylist(int playlistId, int trackId) throws DeletionException;
    void addTrackToPlaylist(int playlistId, TrackDTO dto) throws InsertionException;
}
