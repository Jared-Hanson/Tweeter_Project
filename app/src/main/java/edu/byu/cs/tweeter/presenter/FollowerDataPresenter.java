package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.FollowDataService;
import edu.byu.cs.tweeter.model.service.FollowerService;
import edu.byu.cs.tweeter.model.service.FollowingService;
import edu.byu.cs.tweeter.model.service.request.FollowDataRequest;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.response.FollowDataResponse;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;

public class FollowerDataPresenter {
    private FollowerDataPresenter.View view = null;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    public FollowerDataPresenter() {
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public FollowerDataPresenter(FollowerDataPresenter.View view) {
        this.view = view;
    }

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    public FollowDataResponse getFollowingData(FollowDataRequest request) throws IOException {
        FollowDataService followerService = getFollowerService();
        return followerService.getFollowerData(request);
    }

    /**
     * Returns an instance of {@link FollowingService}. Allows mocking of the FollowingService class
     * for testing purposes. All usages of FollowingService should get their FollowingService
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    FollowDataService getFollowerService() {
        return new FollowDataService();
    }
}
