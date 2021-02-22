package edu.byu.cs.tweeter.model.service.request;

import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.User;

public class UnFollowActionRequest {
    private User user;
    private User userToUnFollow;

    public UnFollowActionRequest(User user, User userToUnFollow) {
        this.user = user;
        this.userToUnFollow = userToUnFollow;
    }

    public User getUser() {
        return user;
    }

    public User getUserToUnFollow() {
        return userToUnFollow;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FollowActionRequest)) return false;
        FollowActionRequest that = (FollowActionRequest) o;
        return Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getUserToUnFollow(), that.getUserToFollow());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getUserToUnFollow());
    }
}
