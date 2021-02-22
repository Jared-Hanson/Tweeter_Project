package edu.byu.cs.tweeter.model.service;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.FollowActionRequest;
import edu.byu.cs.tweeter.model.service.request.UnFollowActionRequest;
import edu.byu.cs.tweeter.model.service.response.FollowActionResponse;

public class FollowActionService {
    public FollowActionResponse followUser(FollowActionRequest request){
        FollowActionResponse res = getServerFacade().followUser(request);
        return res;
    }
    public FollowActionResponse unFollowUser(UnFollowActionRequest request){
        FollowActionResponse res = getServerFacade().unFollowUser(request);
        return res;
    }
    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
