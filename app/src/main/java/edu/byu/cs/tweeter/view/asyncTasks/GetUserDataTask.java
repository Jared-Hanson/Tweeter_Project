package edu.byu.cs.tweeter.view.asyncTasks;

import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.GetUserDataService;
import edu.byu.cs.tweeter.model.service.request.GetUserDataRequest;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.GetUserDataResponse;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.util.ByteArrayUtils;

public class GetUserDataTask extends AsyncTask<GetUserDataRequest, Void, GetUserDataResponse> {
    private final GetUserDataTask.Observer observer;
    private GetUserDataService service;

    /**
     * An observer interface to be implemented by observers who want to be notified when this task
     * completes.
     */
    public interface Observer {
        void getUserDataSuccessful(GetUserDataResponse getUserDataResponse);
        void getUserDataUnsuccessful(GetUserDataResponse getUserDataResponse);
    }

    /**
     * Creates an instance.
     *
     * @param observer the observer who wants to be notified when this task completes.
     */
    public GetUserDataTask(GetUserDataService service, Observer observer) {
        this.service = service;
        if(observer == null) {
            throw new NullPointerException();
        }

        this.observer = observer;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected GetUserDataResponse doInBackground(GetUserDataRequest... getUserDataRequests) {

        GetUserDataResponse getUserDataResponse = null;
        try {
            getUserDataResponse = service.getUserData(getUserDataRequests[0]);
            if(getUserDataResponse.isSuccess()) {
                loadImage(getUserDataResponse.getUser());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getUserDataResponse;
    }

    private void loadImage(User user) {
        try {
            byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
            user.setImageBytes(bytes);
        } catch (IOException e) {
            Log.e(this.getClass().getName(), e.toString(), e);
        }
    }

    /**
     * Notifies the observer (on the thread of the invoker of the
     * {@link #(GetUserDataResponse...)} method) when the task completes.
     *
     * @param getUserDataResponse the response that was received by the task.
     */
    @Override
    protected void onPostExecute(GetUserDataResponse getUserDataResponse) {

        if(getUserDataResponse.isSuccess()) {
            observer.getUserDataSuccessful(getUserDataResponse);
        } else {
            observer.getUserDataUnsuccessful(getUserDataResponse);
        }
    }
}
