package edu.byu.cs.tweeter.model.service.response;

import java.util.Objects;

public class FollowDataResponse {
    private int followers;
    private int following;

    public FollowDataResponse(int followers, int following) {
        this.followers = followers;
        this.following = following;
    }

    public int getFollowers() {
        return followers;
    }

    public int getFollowing() {
        return following;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowDataResponse)) return false;
        FollowDataResponse that = (FollowDataResponse) o;
        return getFollowers() == that.getFollowers() &&
                getFollowing() == that.getFollowing();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFollowers(), getFollowing());
    }
}
