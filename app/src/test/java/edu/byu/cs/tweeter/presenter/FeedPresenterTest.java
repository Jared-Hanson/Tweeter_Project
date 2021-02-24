package edu.byu.cs.tweeter.presenter;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.time.LocalDate;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.service.FeedService;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;

public class FeedPresenterTest {

    private StoryResponse response;
    private StoryRequest request;
    private FeedService mockFeedService;
    private FeedPresenter feedPresenter;

    @BeforeEach
    public void setup() throws IOException {
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

        request = new StoryRequest(currentUser.getAlias(), 3, null);
        response = new StoryResponse(Arrays.asList(resultTweet1, resultTweet2, resultTweet3), false);

        mockFeedService = Mockito.mock(FeedService.class);
        Mockito.when(mockFeedService.getFeed(request)).thenReturn(response);

        presenter = Mockito.spy(new FeedPresenter(new FeedPresenter.View() {}));
        Mockito.when(presenter.getFeedService()).thenReturn(mockFeedService);
    }

    @Test
    public void testGetFeed_returnsServiceResult() throws IOException {
        Mockito.when(mockFeedService.getFeed(request)).thenReturn(response);

        // Assert that the presenter returns the same response as the service (it doesn't do
        // anything else, so there's nothing else to test).
        Assertions.assertEquals(response, presenter.getFeed(request));
    }

    @Test
    public void testGetFeed_serviceThrowsIOException_presenterThrowsIOException() throws IOException {
        Mockito.when(mockFeedService.getFeed(request)).thenThrow(new IOException());

        Assertions.assertThrows(IOException.class, () -> {
            presenter.getFeed(request);
        });
    }
}
