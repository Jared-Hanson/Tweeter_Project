package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.FollowActionRequest;
import edu.byu.cs.tweeter.model.service.request.FollowDataRequest;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.request.UnFollowActionRequest;
import edu.byu.cs.tweeter.model.service.response.FollowActionResponse;
import edu.byu.cs.tweeter.model.service.response.FollowDataResponse;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.presenter.Follow_UnfollowActionPresenter;
import edu.byu.cs.tweeter.presenter.FollowerDataPresenter;
import edu.byu.cs.tweeter.presenter.FollowerPresenter;

public class CheckIfFollowingTask extends AsyncTask<FollowActionRequest, Void, FollowActionResponse> {

    private final Follow_UnfollowActionPresenter presenter;
    private final CheckIfFollowingTask.Observer observer;
    private Exception exception;



    public interface Observer {
        void follow(FollowActionResponse followerResponse);
        void handleException(Exception exception);
    }


    public CheckIfFollowingTask(Follow_UnfollowActionPresenter presenter, CheckIfFollowingTask.Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }


    @Override
    protected FollowActionResponse doInBackground(FollowActionRequest... followerRequests) {

        FollowActionResponse response = null;

        try {
            response = presenter.isFollowing(followerRequests[0]);
        } catch (IOException ex) {
            exception = ex;
        }

        return response;
    }


    @Override
    protected void onPostExecute(FollowActionResponse followerResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else {
            observer.follow(followerResponse);
        }
    }
}