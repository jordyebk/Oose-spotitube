package Service;

import DAO.Interfaces.ITrackDAO;

import javax.inject.Inject;


public class TrackService {

    private ITrackDAO trackDAO;

    @Inject
    public TrackService(ITrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public TrackService() {
    }


}
