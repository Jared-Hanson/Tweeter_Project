package edu.byu.cs.tweeter.model.domain;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Objects;

public class Tweet implements Comparable<Tweet>{
    private final User author;
    private final String body;
    private final Date date;

    public Tweet(@NotNull User author, @NotNull String body, @NotNull Date date) {
        this.author = author;
        this.body = body;
        this.date = date;
    }

    public User getAuthor() {
        return author;
    }

    public String getBody() {
        return body;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tweet)) return false;
        Tweet tweet = (Tweet) o;
        return getAuthor().equals(tweet.getAuthor()) &&
                getBody().equals(tweet.getBody()) &&
                getDate().equals(tweet.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAuthor(), getBody(), getDate());
    }

    @Override
    public String toString() {
        return "Tweet{" +
                "author=" + author +
                ", body='" + body + '\'' +
                ", date=" + date +
                '}';
    }
    @Override
    public int compareTo(Tweet o) {
        if (this.date != o.getDate()) {
            return this.date.compareTo(o.getDate());
        }
        return this.getAuthor().getAlias().compareTo(o.getAuthor().getAlias());
    }

}
