package dao.interfaces;

import dto.PlaylistDTO;
import dto.PlaylistsDTO;
import exceptions.DeletionException;
import exceptions.InsertionException;
import exceptions.PlaylistException;
import exceptions.UpdateException;

public interface IPlaylistDAO {

    PlaylistsDTO getAllPlaylists(String currentUser) throws PlaylistException;
    void deletePlaylist(int playlistId) throws DeletionException;
    void insertPlaylist(PlaylistDTO dto, String currentUser) throws InsertionException;
    void editPlaylist(PlaylistDTO dto) throws UpdateException;
}
