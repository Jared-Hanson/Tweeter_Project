package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.FeedService;
import edu.byu.cs.tweeter.model.service.StoryService;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

public class FeedPresenter {
    private final FeedPresenter.View view;

    public interface View{
        // this is where we need to add functions to update the view if there are more tweets added or more followers added.

        //void updateFeed();
    }

    public FeedPresenter(FeedPresenter.View view) {
        this.view = view;
        //FeedService feed = getFeedService();
        //feed.addObserver(this);
    }

    public StoryResponse getFeed(StoryRequest request) throws IOException {
        FeedService feedService = getFeedService();
        return feedService.getFeed(request);
    }

    FeedService getFeedService() {return new FeedService();}
}
