package dto;

import java.util.ArrayList;
import java.util.List;

public class PlaylistDTO {
    private int id;
    private String name;
    private Boolean owner;
    private List<TrackDTO> tracks = new ArrayList<>();

    public PlaylistDTO() {
    }

    public PlaylistDTO(int id, String name, Boolean owner, List<TrackDTO> tracks) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public PlaylistDTO(PlaylistPOJO playlist) {
        this.id = playlist.getPlaylistId();
        this.name = playlist.getName();
        this.owner = playlist.getOwner();
        for (TrackPOJO trackPOJO : playlist.getTracks()) {
            this.tracks.add(new TrackDTO(trackPOJO));
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
}
