package edu.byu.cs.tweeter.model.domain;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.byu.cs.tweeter.model.net.ServerFacade;


public class Tweet implements Comparable<Tweet>{
    private final User author;
    private final String body;
    private final LocalDate date;

    public Tweet(@NotNull User author, @NotNull String body, @NotNull LocalDate date) {
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

    public LocalDate getDate() {
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
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public int compareTo(Tweet o) {
        if (this.date != o.getDate()) {
            return -1 * (this.date.compareTo(o.getDate()));
        }
        return this.getAuthor().getAlias().compareTo(o.getAuthor().getAlias());
    }

}
