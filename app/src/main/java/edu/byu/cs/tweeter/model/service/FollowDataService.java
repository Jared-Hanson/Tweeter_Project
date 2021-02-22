package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.FollowDataRequest;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowDataResponse;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;

public class FollowDataService {

    public FollowDataResponse getFollowerData(FollowDataRequest request) throws IOException {
        FollowDataResponse response = getServerFacade().getFollowerData(request);


        return response;
    }

    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
