package dto;

import org.bson.types.ObjectId;

import java.util.List;

public class PlaylistPOJO {
    private ObjectId id;
    private int playlistId;
    private String name;
    private Boolean owner;
    private List<TrackDTO> tracks;

    public PlaylistPOJO() {
    }

    public PlaylistPOJO(ObjectId id, int playlistId, String name, Boolean owner, List<TrackDTO> tracks) {
        this.id = id;
        this.playlistId = playlistId;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
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

    public Boolean getOwner() {
        return owner;
    }

    public void setOwner(Boolean owner) {
        this.owner = owner;
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }
}
