package DAO.Interfaces;

import DTO.PlaylistsDTO;
import Exceptions.DeletionException;
import Exceptions.PlaylistException;

public interface IPlaylistDAO {

    PlaylistsDTO getAllPlaylists(String currentUser) throws PlaylistException;
    void deletePlaylist(int playlistId) throws DeletionException;

}
