package edu.byu.cs.tweeter.model.service.request;

import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;

/**
 * Contains all the information needed to make a logout request.
 */
public class LogoutRequest {
    private User user;
    private AuthToken authToken;

    /**
     * Creates an instance.
     *
     * @param user the user to be logged out.
     * @param authToken the authToken of the user to be logged out.
     */
    public LogoutRequest(User user, AuthToken authToken) {
        this.user = user;
        this.authToken = authToken;
    }

    /**
     * Returns the user of the user to be logged out by this request.
     *
     * @return the user.
     */
    public User getUser() {
        return user;
    }

    /**
     * Returns the authToken of the user to be logged out by this request.
     *
     * @return the authToken.
     */
    public AuthToken getAuthToken() {
        return authToken;
    }
}
