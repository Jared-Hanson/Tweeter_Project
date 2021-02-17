package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.Tweet;

public class StoryRequest {
    private final String userAlias;
    private final int limit;
    private final Tweet lastTweet;

    public StoryRequest(String userAlias, int limit, Tweet lastTweet) {
        this.userAlias = userAlias;
        this.limit = limit;
        this.lastTweet = lastTweet;
    }

    public String getUserAlias() {
        return userAlias;
    }

    public int getLimit() {
        return limit;
    }

    public Tweet getLastTweet() {
        return lastTweet;
    }
}
