package edu.byu.cs.tweeter.model.service.response;

public class FollowActionResponse extends Response{

    public FollowActionResponse(boolean success) {
        super(success);
    }

    public FollowActionResponse(boolean success, String message) {
        super(success, message);
    }
}
