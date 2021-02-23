package edu.byu.cs.tweeter.model.domain;

import java.util.List;
import java.util.Objects;

public class Story {
    List<Tweet> tweets;
    User user;

    public Story(User user) {
        this.user = user;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public User getUser() {
        return user;
    }
    public void addTweet(Tweet tweet){
        tweets.add(tweet);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Story)) return false;
        Story story = (Story) o;
        return Objects.equals(getTweets(), story.getTweets()) &&
                getUser().equals(story.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTweets(), getUser());
    }
}
