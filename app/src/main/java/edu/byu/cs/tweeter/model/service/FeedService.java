package edu.byu.cs.tweeter.model.service;


import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.util.ByteArrayUtils;


public class FeedService {

    public StoryResponse getFeed(StoryRequest request) throws IOException {
        StoryResponse response = getServerFacade().getFeed(request);

        if(response.isSuccess()) {
            loadImages(response);
        }

        return response;
    }
    private void loadImages(StoryResponse response) throws IOException {
        for(Tweet tweet : response.getTweets()) {
            byte [] bytes = ByteArrayUtils.bytesFromUrl(tweet.getAuthor().getImageUrl());
            tweet.getAuthor().setImageBytes(bytes);
        }
    }
    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
    //public void addObserver
}
