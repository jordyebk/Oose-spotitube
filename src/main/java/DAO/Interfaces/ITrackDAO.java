package DAO.Interfaces;

import DTO.TracksDTO;
import Exceptions.TrackException;

import java.util.List;

public interface ITrackDAO {

    TracksDTO getAllTracksInPlaylist(int playlistId) throws TrackException;
    TracksDTO getAllTracksNotInPlaylist(int playlistId) throws TrackException;
}
