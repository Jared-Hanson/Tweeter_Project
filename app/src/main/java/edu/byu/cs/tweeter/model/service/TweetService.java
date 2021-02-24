package edu.byu.cs.tweeter.model.service;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;

import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

public class TweetService {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public TweetResponse postTweet(TweetRequest request) throws IOException {
        TweetResponse response = getServerFacade().postTweet(request);
        return response;

    }

    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
