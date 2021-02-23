package edu.byu.cs.tweeter.view.main;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import edu.byu.cs.tweeter.model.service.request.FollowActionRequest;
import edu.byu.cs.tweeter.model.service.response.FollowActionResponse;
import edu.byu.cs.tweeter.view.asyncTasks.CheckIfFollowingTask;
import edu.byu.cs.tweeter.view.asyncTasks.FollowTask;
import edu.byu.cs.tweeter.view.asyncTasks.UnFollowTask;

public abstract class FollowingObserverSuper implements UnFollowTask.Observer, CheckIfFollowingTask.Observer, FollowTask.Observer{
    private static final String LOG_TAG = "UserAcivity";

    abstract void goodResponse();
    abstract void badResponse();
    abstract void setupPresenter(FollowActionRequest request);
    abstract Context getApplicationContextFunction();

    public void executeTask(FollowActionRequest request){
        setupPresenter(request);
    }

    @Override
    public void run(FollowActionResponse followerResponse) {
        if (followerResponse.isSuccess()){
            goodResponse();
        }
        else{
            badResponse();
        }

    }

    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(getApplicationContextFunction(), exception.getMessage(), Toast.LENGTH_LONG).show();
    }


}