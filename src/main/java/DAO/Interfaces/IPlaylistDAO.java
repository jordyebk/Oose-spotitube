package DAO.Interfaces;

import DTO.PlaylistDTO;
import DTO.PlaylistsDTO;
import Exceptions.DeletionException;
import Exceptions.InsertionException;
import Exceptions.PlaylistException;
import Exceptions.UpdateException;

public interface IPlaylistDAO {

    PlaylistsDTO getAllPlaylists(String currentUser) throws PlaylistException;
    void deletePlaylist(int playlistId) throws DeletionException;
    void insertPlaylist(PlaylistDTO dto, String currentUser) throws InsertionException;
    void editPlaylist(PlaylistDTO dto) throws UpdateException;
}
