package edu.byu.cs.tweeter.model.service.response;

/**
 * A response for a {@link edu.byu.cs.tweeter.model.service.request.LogoutRequest}.
 */
public class LogoutResponse extends Response {


    /**
     * Creates a response indicating that the corresponding request was or was not successful.
     *
     * @param message a message describing why the request was unsuccessful.
     */
    public LogoutResponse(Boolean b, String message) {
        super(b, message);
    }

}
