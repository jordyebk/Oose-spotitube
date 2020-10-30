package dto;

import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class PlaylistPOJO {
    private ObjectId id;
    private int playlistId;
    private String name;
    private String owner;
    private List<TrackPOJO> tracks = new ArrayList<>();

    public PlaylistPOJO() {
    }

    public PlaylistPOJO(ObjectId id, int playlistId, String name, String owner, List<TrackPOJO> tracks) {
        this.id = id;
        this.playlistId = playlistId;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public PlaylistPOJO(PlaylistDTO dto, int playlistId, String currentUser) {
        this.playlistId = playlistId;
        this.name = dto.getName();
        this.owner = currentUser;
        for (TrackDTO trackDTO : dto.getTracks()) {
            this.tracks.add(new TrackPOJO(trackDTO));
        }
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<TrackPOJO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackPOJO> tracks) {
        this.tracks = tracks;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }
}
