package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.LoginService;
import edu.byu.cs.tweeter.model.service.TweetService;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

public class TweetPresenter {
    private final TweetPresenter.View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public TweetPresenter(TweetPresenter.View view) {
        this.view = view;
    }

    /**
     * Makes a login request.
     *
     * @param request the request.
     */
    public TweetResponse login(TweetRequest request) throws IOException {
        TweetService tweetService = new TweetService();
        return tweetService.postTweet(request);
    }
}
