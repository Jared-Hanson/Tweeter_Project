package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.FollowActionService;
import edu.byu.cs.tweeter.model.service.FollowingService;
import edu.byu.cs.tweeter.model.service.request.FollowActionRequest;
import edu.byu.cs.tweeter.model.service.request.UnFollowActionRequest;
import edu.byu.cs.tweeter.model.service.response.FollowActionResponse;

public class Follow_UnfollowActionPresenter {
    private Follow_UnfollowActionPresenter.View view;

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
    public Follow_UnfollowActionPresenter(Follow_UnfollowActionPresenter.View view) {
        this.view = view;
    }

    public Follow_UnfollowActionPresenter() {
    }

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    public FollowActionResponse followUser(FollowActionRequest request) throws IOException {
        FollowActionService followActionService = getFollowService();
        return followActionService.followUser(request);
    }
    public FollowActionResponse unFollowUser(FollowActionRequest request) throws IOException {
        FollowActionService followActionService = getFollowService();
        return followActionService.unFollowUser(request);
    }
    public FollowActionResponse isFollowing(FollowActionRequest request) throws IOException {
        FollowActionService followActionService = getFollowService();
        return followActionService.isFollowing(request);
    }

    /**
     * Returns an instance of {@link FollowingService}. Allows mocking of the FollowingService class
     * for testing purposes. All usages of FollowingService should get their FollowingService
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    FollowActionService getFollowService() {
        return new FollowActionService();
    }

}
