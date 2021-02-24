package edu.byu.cs.tweeter.model.service.request;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.User;

public class FollowDataRequest {
    private User user;

    public FollowDataRequest(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowDataRequest)) return false;
        FollowDataRequest that = (FollowDataRequest) o;
        return Objects.equals(getUser(), that.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser());
    }

    public User getUser() {
        return user;
    }
}
