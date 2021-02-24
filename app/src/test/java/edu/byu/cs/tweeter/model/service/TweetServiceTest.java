package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

public class TweetServiceTest {

    private TweetRequest validRequest;
    private TweetRequest invalidRequest;

    private TweetResponse successResponse;
    private TweetResponse failureResponse;

    private TweetService tweetServiceSpy;

    @BeforeEach
    public void setup() {
        User currentUser = new User("FirstName", "LastName", null);

        LocalDate time = LocalDate.of(2021, 1, 8);

        // Setup request objects to use in the tests
        validRequest = new TweetRequest(currentUser, "tweet", time);
        invalidRequest = new TweetRequest(null, null, null);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new TweetResponse(true);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.postTweet(validRequest)).thenReturn(successResponse);

        failureResponse = new TweetResponse(false);
        Mockito.when(mockServerFacade.postTweet(invalidRequest)).thenReturn(failureResponse);

        tweetServiceSpy = Mockito.spy(new TweetService());
        Mockito.when(tweetServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testPostTweet_validRequest_correctResponse() throws IOException {
        TweetResponse response = tweetServiceSpy.postTweet(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testPostTweet_invalidRequest_returnsNoSuccess() throws IOException {
        TweetResponse response = tweetServiceSpy.postTweet(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }
}
