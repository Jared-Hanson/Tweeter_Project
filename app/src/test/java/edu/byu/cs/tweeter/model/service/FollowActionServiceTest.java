package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.FollowActionRequest;
import edu.byu.cs.tweeter.model.service.response.FollowActionResponse;

public class FollowActionServiceTest {

    private FollowActionRequest validRequest;
    private FollowActionRequest invalidRequest;

    private FollowActionResponse successResponse;
    private FollowActionResponse failureResponse;

    private FollowActionService followingServiceSpy;

    @BeforeEach
    public void setup() {
        User currentUser = new User("FirstName", "LastName", null);
        User otherUser = new User("FirstName1", "LastName1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        // Setup request objects to use in the tests
        validRequest = new FollowActionRequest(currentUser, otherUser);
        invalidRequest = new FollowActionRequest(null, null);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new FollowActionResponse(true);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.followUser(validRequest)).thenReturn(successResponse);
        Mockito.when(mockServerFacade.unFollowUser(validRequest)).thenReturn(successResponse);
        Mockito.when(mockServerFacade.isFollowing(validRequest)).thenReturn(successResponse);

        failureResponse = new FollowActionResponse(false, "An exception occurred");
        Mockito.when(mockServerFacade.followUser(invalidRequest)).thenReturn(failureResponse);
        Mockito.when(mockServerFacade.unFollowUser(invalidRequest)).thenReturn(failureResponse);
        Mockito.when(mockServerFacade.isFollowing(invalidRequest)).thenReturn(failureResponse);

        followingServiceSpy = Mockito.spy(new FollowActionService());
        Mockito.when(followingServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testFollowUser_validRequest_correctResponse() throws IOException {
        FollowActionResponse response = followingServiceSpy.followUser(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testUnFollowUser_validRequest_correctResponse() throws IOException {
        FollowActionResponse response = followingServiceSpy.unFollowUser(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testIsFollowing_validRequest_correctResponse() throws IOException {
        FollowActionResponse response = followingServiceSpy.isFollowing(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testFollowUser_invalidRequest_returnsNoSuccess() throws IOException {
        FollowActionResponse response = followingServiceSpy.followUser(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }

    @Test
    public void testUnFollowUser_invalidRequest_returnsNoSuccess() throws IOException {
        FollowActionResponse response = followingServiceSpy.unFollowUser(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }

    @Test
    public void testIsFollowing_invalidRequest_returnsNoSuccess() throws IOException {
        FollowActionResponse response = followingServiceSpy.isFollowing(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }
}
