package edu.byu.cs.tweeter.presenter;

import android.util.Log;
import android.view.View;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.LogoutService;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;

public class LogoutPresenter {


    private LogoutPresenter.View view = null;

    public interface View{
        // this is where we need to add functions to update the view if there are more tweets added or more followers added.
    }
    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public LogoutPresenter(View view) {
        this.view = view;
    }

    public LogoutPresenter() {

    }

    /**
     * Makes a logout request.
     *
     * @param logoutRequest the request.
     */
    public LogoutResponse logout(LogoutRequest logoutRequest) throws IOException {
        LogoutService logoutService = new LogoutService();
        return logoutService.logout(logoutRequest);
    }
}
