package DAO.Interfaces;

import DTO.PlaylistDTO;
import DTO.PlaylistsDTO;
import Exceptions.DeletionException;
import Exceptions.InsertionError;
import Exceptions.PlaylistException;
import Exceptions.UpdateException;

public interface IPlaylistDAO {

    PlaylistsDTO getAllPlaylists(String currentUser) throws PlaylistException;
    void deletePlaylist(int playlistId) throws DeletionException;
    void insertPlaylist(PlaylistDTO dto, String currentUser) throws InsertionError;
    void editPlaylist(PlaylistDTO dto) throws UpdateException;
}
