package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.request.FollowDataRequest;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowActionResponse;
import edu.byu.cs.tweeter.model.service.response.FollowDataResponse;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.presenter.FollowerDataPresenter;
import edu.byu.cs.tweeter.presenter.FollowerPresenter;

public class GetFollowerDataTask extends AsyncTask<FollowDataRequest, Void, FollowDataResponse> {

    private final FollowerDataPresenter presenter;
    private final GetFollowerDataTask.Observer observer;
    private Exception exception;



    public interface Observer {
        void getData(FollowDataResponse followerResponse);
        void handleException(Exception exception);
    }


    public GetFollowerDataTask(FollowerDataPresenter presenter, GetFollowerDataTask.Observer observer) {
        if(observer == null) {
            throw new NullPointerException();
        }

        this.presenter = presenter;
        this.observer = observer;
    }


    @Override
    protected FollowDataResponse doInBackground(FollowDataRequest... followerRequests) {

        FollowDataResponse response = null;

        try {
            response = presenter.getFollowingData(followerRequests[0]);
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
    protected void onPostExecute(FollowDataResponse followerResponse) {
        if(exception != null) {
            observer.handleException(exception);
        } else {
            observer.getData(followerResponse);
        }
    }
}
