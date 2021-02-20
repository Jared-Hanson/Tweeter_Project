package edu.byu.cs.tweeter.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

public class TweetService {
    public TweetResponse postTweet(TweetRequest request) throws IOException {
        TweetResponse response = getServerFacade().postTweet(request);
        return response;

    }
    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
