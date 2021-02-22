package edu.byu.cs.tweeter.model.service.request;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.User;

public class FollowActionRequest {
    private User user;
    private User userToFollow;

    public FollowActionRequest(User user, User userToFollow) {
        this.user = user;
        this.userToFollow = userToFollow;
    }

    public User getUser() {
        return user;
    }

    public User getUserToFollow() {
        return userToFollow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowActionRequest)) return false;
        FollowActionRequest that = (FollowActionRequest) o;
        return Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getUserToFollow(), that.getUserToFollow());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getUserToFollow());
    }
}
