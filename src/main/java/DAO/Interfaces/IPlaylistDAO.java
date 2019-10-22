package DAO.Interfaces;

import DTO.PlaylistsDTO;
import Exceptions.PlaylistException;

public interface IPlaylistDAO {

    PlaylistsDTO getAllPlaylists(String currentUser) throws PlaylistException;
}
