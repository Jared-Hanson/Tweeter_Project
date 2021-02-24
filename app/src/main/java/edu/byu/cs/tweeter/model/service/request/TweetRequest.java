package edu.byu.cs.tweeter.model.service.request;

import java.time.LocalDate;
import java.util.Objects;

import edu.byu.cs.tweeter.model.domain.User;

public class TweetRequest {
    private final User user;
    private final String tweetBody;
    private final LocalDate date;


    public TweetRequest(User user, String tweetBody, LocalDate date) {
        this.user = user;
        this.tweetBody = tweetBody;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public String getTweetBody() {
        return tweetBody;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TweetRequest)) return false;
        TweetRequest that = (TweetRequest) o;
        return Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getTweetBody(), that.getTweetBody()) &&
                Objects.equals(getDate(), that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUser(), getTweetBody(), getDate());
    }
}
