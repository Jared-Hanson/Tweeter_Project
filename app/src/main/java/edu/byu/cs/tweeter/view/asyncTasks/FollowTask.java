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

public class FollowTask extends AsyncTask<FollowActionRequest, Void, FollowActionResponse> {

    private final Follow_UnfollowActionPresenter presenter;
    private final FollowTask.Observer observer;
    private Exception exception;



    public interface Observer {
        void handleException(Exception exception);

        void run(FollowActionResponse followerResponse);
    }


    public FollowTask(Follow_UnfollowActionPresenter presenter, FollowTask.Observer observer) {
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
            response = presenter.followUser(followerRequests[0]);
        } catch (IOException ex) {
            exception = ex;
        }

        return response;
    }

    /**
     * Notifies the observer (on the UI thread) when the task completes.
     *
     * @param followerResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(FollowActionResponse followerResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else {
            observer.run(followerResponse);
        }
    }
}