package edu.byu.cs.tweeter.presenter;

import android.view.View;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.FollowerService;
import edu.byu.cs.tweeter.model.service.FollowingService;
import edu.byu.cs.tweeter.model.service.StoryService;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.view.main.following.FollowerFragment;


public class StoryPresenter {
    private final View view;

    public interface View{
        // this is where we need to add functions to update the view if there are more tweets added or more followers added.
    }

    public StoryPresenter(View view) {this.view = view;}

    public StoryResponse getStory(StoryRequest request) throws IOException {
        StoryService storyService = getStoryService();
        return storyService.getStory(request);
    }

    StoryService getStoryService() {return new StoryService();}
}
