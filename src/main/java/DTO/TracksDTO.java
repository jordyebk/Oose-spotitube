package DTO;

import java.util.List;

public class TracksDTO {
    private List<TrackDTO> tracks;

    public TracksDTO(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }

    public TracksDTO() {
    }

    public List<TrackDTO> getTracks() {
        return tracks;
    }

    public void setTracks(List<TrackDTO> tracks) {
        this.tracks = tracks;
    }
}
