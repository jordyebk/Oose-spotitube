package dao.classes;

import dao.databaseconnection.IDatabaseConnection;
import dao.interfaces.IPlaylistDAO;
import dto.PlaylistDTO;
import dto.PlaylistsDTO;
import exceptions.DeletionException;
import exceptions.InsertionException;
import exceptions.PlaylistException;
import exceptions.UpdateException;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

@Default
public class PlaylistDAO implements IPlaylistDAO {

    private IDatabaseConnection database;

    @Inject
    public PlaylistDAO(IDatabaseConnection database) {
        this.database = database;
    }

    public PlaylistDAO() {
    }

    @Override
    public PlaylistsDTO getAllPlaylists(String currentUser) throws PlaylistException {
        return null;
    }

    @Override
    public void deletePlaylist(int playlistId) throws DeletionException {

    }

    @Override
    public void insertPlaylist(PlaylistDTO dto, String currentUser) throws InsertionException {

    }

    @Override
    public void editPlaylist(PlaylistDTO dto) throws UpdateException {

    }
}
