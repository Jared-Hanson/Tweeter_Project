package edu.byu.cs.tweeter.model.domain;

import java.util.List;
import java.util.Objects;

public class Feed {
    List<Story> storiesFromAllUsers;
    User user;

    public Feed(User user) {
        this.user = user;
    }

    public List<Story> getStoriesFromAllUsers() {
        return storiesFromAllUsers;
    }

    public User getUser() {
        return user;
    }
    public void addStories(Story story){
        storiesFromAllUsers.add(story);
    }
    public void resetAllStories(){
        storiesFromAllUsers.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Feed)) return false;
        Feed feed = (Feed) o;
        return Objects.equals(getStoriesFromAllUsers(), feed.getStoriesFromAllUsers()) &&
                getUser().equals(feed.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStoriesFromAllUsers(), getUser());
    }
}
