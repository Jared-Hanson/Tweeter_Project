package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;
import edu.byu.cs.tweeter.presenter.FeedPresenter;
import edu.byu.cs.tweeter.presenter.TweetPresenter;

public class PostTweetTask extends AsyncTask<TweetRequest, Void, TweetResponse> {
    private final TweetPresenter presenter;
    private final PostTweetTask.Observer observer;
    private Exception exception;

    public interface Observer {
        void postTweetResponse(TweetResponse tweetResponse);
        void handleException(Exception exception);
    }

    public PostTweetTask(TweetPresenter presenter, PostTweetTask.Observer observer) {

        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }

    @Override
    protected TweetResponse doInBackground(TweetRequest... tweetRequests) {

        TweetResponse response = null;

        try {
            response = presenter.postTweet(tweetRequests[0]);
        } catch (IOException ex) {
            exception = ex;
        }

        return response;
    }

    @Override
    protected void onPostExecute(TweetResponse tweetResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else {
            observer.postTweetResponse(tweetResponse);
        }
    }

}
