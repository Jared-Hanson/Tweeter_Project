package edu.byu.cs.tweeter.model.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

public class StoryServiceTest {

    private StoryRequest validRequest;
    private StoryRequest invalidRequest;

    private StoryResponse successResponse;
    private StoryResponse failureResponse;

    private StoryService storyServiceSpy;


    @BeforeEach
    public void setup() {
        User currentUser = new User("FirstName", "LastName", null);

        LocalDate date1 = LocalDate.of(2021, 1, 8);
        LocalDate date2 = LocalDate.of(2021, 2, 9);
        LocalDate date3 = LocalDate.of(2020, 2, 11);

        User resultUser1 = new User("FirstName1", "LastName1",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        User resultUser2 = new User("FirstName2", "LastName2",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");
        User resultUser3 = new User("FirstName3", "LastName3",
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png");

        Tweet resultTweet1 = new Tweet(resultUser1, "what a tweet eh", date1);
        Tweet resultTweet2 = new Tweet(resultUser2, "Second Tweet", date2);
        Tweet resultTweet3 = new Tweet(resultUser3, "What a tweet tweet", date3);

        // Setup request objects to use in the tests
        validRequest = new StoryRequest(currentUser.getAlias(), 3, null);
        invalidRequest = new StoryRequest(null, 0, null);

        // Setup a mock ServerFacade that will return known responses
        successResponse = new StoryResponse(true, Arrays.asList(resultTweet1, resultTweet2, resultTweet3), false);
        ServerFacade mockServerFacade = Mockito.mock(ServerFacade.class);
        Mockito.when(mockServerFacade.getStory(validRequest)).thenReturn(successResponse);

        failureResponse = new StoryResponse("An exception occurred");
        Mockito.when(mockServerFacade.getStory(invalidRequest)).thenReturn(failureResponse);

        // Create a FollowingService instance and wrap it with a spy that will use the mock service
        storyServiceSpy = Mockito.spy(new StoryService());
        Mockito.when(storyServiceSpy.getServerFacade()).thenReturn(mockServerFacade);
    }

    @Test
    public void testGetStory_validRequest_correctResponse() throws IOException {
        StoryResponse response = storyServiceSpy.getStory(validRequest);
        Assertions.assertEquals(successResponse, response);
    }

    @Test
    public void testGetStory_validRequest_loadsProfileImages() throws IOException {
        StoryResponse response = storyServiceSpy.getStory(validRequest);

        for(Tweet tweet : response.getTweets()) {
            Assertions.assertNotNull(tweet.getAuthor().getImageBytes());
        }
    }

    @Test
    public void testGetStory_invalidRequest_returnsNoTweets() throws IOException {
        StoryResponse response = storyServiceSpy.getStory(invalidRequest);
        Assertions.assertEquals(failureResponse, response);
    }
}
