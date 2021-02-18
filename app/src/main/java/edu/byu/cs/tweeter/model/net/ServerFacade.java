package edu.byu.cs.tweeter.model.net;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import edu.byu.cs.tweeter.BuildConfig;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

import edu.byu.cs.tweeter.model.service.response.StoryResponse;

/**
 * Acts as a Facade to the Tweeter server. All network requests to the server should go through
 * this class.
 */
public class ServerFacade {
    // This is the hard coded followee data returned by the 'getFollowees()' method
    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    private static final String FEMALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png";

    private final User testUser = new User("Test", "User", MALE_IMAGE_URL);
    private User currentUser = null;

    private final User user1 = new User("Allen", "Anderson", MALE_IMAGE_URL);
    private final User user2 = new User("Amy", "Ames", FEMALE_IMAGE_URL);
    private final User user3 = new User("Bob", "Bobson", MALE_IMAGE_URL);
    private final User user4 = new User("Bonnie", "Beatty", FEMALE_IMAGE_URL);
    private final User user5 = new User("Chris", "Colston", MALE_IMAGE_URL);
    private final User user6 = new User("Cindy", "Coats", FEMALE_IMAGE_URL);
    private final User user7 = new User("Dan", "Donaldson", MALE_IMAGE_URL);
    private final User user8 = new User("Dee", "Dempsey", FEMALE_IMAGE_URL);
    private final User user9 = new User("Elliott", "Enderson", MALE_IMAGE_URL);
    private final User user10 = new User("Elizabeth", "Engle", FEMALE_IMAGE_URL);
    private final User user11 = new User("Frank", "Frandson", MALE_IMAGE_URL);
    private final User user12 = new User("Fran", "Franklin", FEMALE_IMAGE_URL);
    private final User user13 = new User("Gary", "Gilbert", MALE_IMAGE_URL);
    private final User user14 = new User("Giovanna", "Giles", FEMALE_IMAGE_URL);
    private final User user15 = new User("Henry", "Henderson", MALE_IMAGE_URL);
    private final User user16 = new User("Helen", "Hopwell", FEMALE_IMAGE_URL);
    private final User user17 = new User("Igor", "Isaacson", MALE_IMAGE_URL);
    private final User user18 = new User("Isabel", "Isaacson", FEMALE_IMAGE_URL);
    private final User user19 = new User("Justin", "Jones", MALE_IMAGE_URL);
    private final User user20 = new User("Jill", "Johnson", FEMALE_IMAGE_URL);

    private List<User> loginFollowees = Arrays.asList(user1, user2, user3, user4, user5, user6, user7,
            user8, user9, user10, user11, user12, user13, user14, user15, user16, user17, user18,
            user19, user20);

    private final List<User> loginFollowers = Arrays.asList(user3, user4, user5, user6, user7, user15,
            user16, user17, user18, user19, user20);

    private List<User> registerFollowees = Collections.emptyList();
    private final List<User> registerFollowers = Collections.emptyList();

    private final Date date1 = new Date(2010, 12, 6);
    private final Date date2 = new Date(2021, 8, 5);
    private final Date date3 = new Date(2020, 6, 2);
    private final Date date4 = new Date(2010, 12, 1);
    private final Date date5 = new Date(2021, 8, 2);
    private final Date date6 = new Date(2020, 6, 6);

    private final Tweet tweet1 = new Tweet(testUser, "what a tweet eh", date1);
    private final Tweet tweet2 = new Tweet(testUser, "Second Tweet", date2);
    private final Tweet tweet3 = new Tweet(testUser, "What a tweet tweet", date3);

    private final Tweet tweet4 = new Tweet(user3, "I hate dummy data", date4);
    private final Tweet tweet5 = new Tweet(user5, "Who did that?", date5);
    private final Tweet tweet6 = new Tweet(user17, "Woah bruh", date6);


    /**
     * Performs a login and if successful, returns the logged in user and an auth token. The current
     * implementation is hard-coded to return a dummy user and doesn't actually make a network
     * request.
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
     */
    public LoginResponse login(LoginRequest request) {
        if (request.getUsername().equals("dummyUserName") && request.getPassword().equals("dummyPassword")) {
            currentUser = testUser;
            return new LoginResponse(testUser, new AuthToken());
        }
        else {
            return new LoginResponse("Invalid credentials");
        }
    }

    public LoginResponse register(RegisterRequest request) {
        if (request.getUsername().equals(testUser.getAlias()) ||
                request.getUsername().equals(user1.getAlias())||
                request.getUsername().equals(user2.getAlias())||
                request.getUsername().equals(user3.getAlias())||
                request.getUsername().equals(user4.getAlias())||
                request.getUsername().equals(user5.getAlias())||
                request.getUsername().equals(user6.getAlias())||
                request.getUsername().equals(user7.getAlias())||
                request.getUsername().equals(user8.getAlias())||
                request.getUsername().equals(user9.getAlias())||
                request.getUsername().equals(user10.getAlias())||
                request.getUsername().equals(user11.getAlias())||
                request.getUsername().equals(user12.getAlias())||
                request.getUsername().equals(user13.getAlias())||
                request.getUsername().equals(user14.getAlias())||
                request.getUsername().equals(user15.getAlias())||
                request.getUsername().equals(user16.getAlias())||
                request.getUsername().equals(user17.getAlias())||
                request.getUsername().equals(user18.getAlias())||
                request.getUsername().equals(user19.getAlias())||
                request.getUsername().equals(user20.getAlias())) {
            return new LoginResponse("Username already taken");
        }
        User user = new User(request.getFirstName(), request.getLastName(), request.getUsername(),
                null);
        user.setImageBytes(request.getImageBytes());
        currentUser = user;
        return new LoginResponse(user, new AuthToken());
    }

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request. The current implementation
     * returns generated data and doesn't actually make a network request.
     *
     * @param request contains information about the user whose followees are to be returned and any
     *                other information required to satisfy the request.
     * @return the following response.
     */
    public FollowingResponse getFollowees(FollowingRequest request) {

        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(request.getLimit() < 0) {
                throw new AssertionError();
            }

            if(request.getFollowerAlias() == null) {
                throw new AssertionError();
            }
        }

        List<User> allFollowees = getDummyFollowees(request.getFollowerAlias());
        List<User> responseFollowees = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if(request.getLimit() > 0) {
            int followeesIndex = getFolloweesStartingIndex(request.getLastFolloweeAlias(), allFollowees);

            for(int limitCounter = 0; followeesIndex < allFollowees.size() && limitCounter < request.getLimit(); followeesIndex++, limitCounter++) {
                responseFollowees.add(allFollowees.get(followeesIndex));
            }

            hasMorePages = followeesIndex < allFollowees.size();
        }

        return new FollowingResponse(responseFollowees, hasMorePages);
    }
    public StoryResponse getStory(StoryRequest request) {

        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(request.getLimit() < 0) {
                throw new AssertionError();
            }

            if(request.getUserAlias() == null) {
                throw new AssertionError();
            }
        }

        List<Tweet> allTweets = getDummyTweets();
        List<Tweet> responseTweets = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if(request.getLimit() > 0) {
            int tweetIndex = getTweetStartingIndex(request.getLastTweet(), allTweets);

            for(int limitCounter = 0; tweetIndex < allTweets.size() && limitCounter < request.getLimit(); tweetIndex++, limitCounter++) {
                responseTweets.add(allTweets.get(tweetIndex));
            }

            hasMorePages = tweetIndex < allTweets.size();
        }

        return new StoryResponse(responseTweets, hasMorePages);
    }
    public StoryResponse getFeed(StoryRequest request) {

        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(request.getLimit() < 0) {
                throw new AssertionError();
            }

            if(request.getUserAlias() == null) {
                throw new AssertionError();
            }
        }

        List<Tweet> allTweets = getDummyTweetsFeed();
        List<Tweet> responseTweets = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if(request.getLimit() > 0) {
            int tweetIndex = getTweetStartingIndex(request.getLastTweet(), allTweets);

            for(int limitCounter = 0; tweetIndex < allTweets.size() && limitCounter < request.getLimit(); tweetIndex++, limitCounter++) {
                responseTweets.add(allTweets.get(tweetIndex));
            }

            hasMorePages = tweetIndex < allTweets.size();
        }

        return new StoryResponse(responseTweets, hasMorePages);
    }

    private int getTweetStartingIndex(Tweet lastTweet, List<Tweet> allTweets) {

        int tweetIndex = 0;

        if(lastTweet != null) {
            // This is a paged request for something after the first page. Find the first item
            // we should return
            for (int i = 0; i < allTweets.size(); i++) {
                if(lastTweet.equals(allTweets.get(i))) {
                    // We found the index of the last item returned last time. Increment to get
                    // to the first one we should return
                    tweetIndex = i + 1;
                    break;
                }
            }
        }

        return tweetIndex;
    }
    List<Tweet> getDummyTweets() {
        return Arrays.asList(tweet1, tweet2, tweet3);
    }
    List<Tweet> getDummyTweetsFeed() {
        return Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6);
    }


    public FollowerResponse getFollowers(FollowerRequest request) {

        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(request.getLimit() < 0) {
                throw new AssertionError();
            }

            if(request.getFollowerAlias() == null) {
                throw new AssertionError();
            }
        }

        List<User> allFollowees = getDummyFollowers(request.getFollowerAlias());
        List<User> responseFollowees = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if(request.getLimit() > 0) {
            int followeesIndex = getFolloweesStartingIndex(request.getLastFolloweeAlias(), allFollowees);

            for(int limitCounter = 0; followeesIndex < allFollowees.size() && limitCounter < request.getLimit(); followeesIndex++, limitCounter++) {
                responseFollowees.add(allFollowees.get(followeesIndex));
            }

            hasMorePages = followeesIndex < allFollowees.size();
        }

        return new FollowerResponse(responseFollowees, hasMorePages);
    }

    /**
     * Determines the index for the first followee in the specified 'allFollowees' list that should
     * be returned in the current request. This will be the index of the next followee after the
     * specified 'lastFollowee'.
     *
     * @param lastFolloweeAlias the alias of the last followee that was returned in the previous
     *                          request or null if there was no previous request.
     * @param allFollowees the generated list of followees from which we are returning paged results.
     * @return the index of the first followee to be returned.
     */
    private int getFolloweesStartingIndex(String lastFolloweeAlias, List<User> allFollowees) {

        int followeesIndex = 0;

        if(lastFolloweeAlias != null) {
            // This is a paged request for something after the first page. Find the first item
            // we should return
            for (int i = 0; i < allFollowees.size(); i++) {
                if(lastFolloweeAlias.equals(allFollowees.get(i).getAlias())) {
                    // We found the index of the last item returned last time. Increment to get
                    // to the first one we should return
                    followeesIndex = i + 1;
                    break;
                }
            }
        }

        return followeesIndex;
    }

    /**
     * Returns the list of dummy followee data. This is written as a separate method to allow
     * mocking of the followees.
     *
     * @return the followees.
     */
    List<User> getDummyFollowees(String alias) {
        if (alias.equals(testUser.getAlias())) {
            return loginFollowees;
        }
        else if (alias.equals(currentUser.getAlias())) {
            return registerFollowees;
        }
        else if (currentUser.getAlias().equals(testUser.getAlias())) {
            List<User> returnList = Collections.emptyList();
            for (int i = 0; i < loginFollowers.size(); i++) {
                if (loginFollowers.get(i).getAlias().equals(alias)) {
                    returnList = Collections.singletonList(testUser);
                }
            }
            return returnList;
        }
        else {
            List<User> returnList = Collections.emptyList();
            for (int i = 0; i < registerFollowers.size(); i++) {
                if (registerFollowers.get(i).getAlias().equals(alias)) {
                    returnList = Collections.singletonList(currentUser);
                }
            }
            return returnList;
        }
    }

    List<User> getDummyFollowers(String alias) {
        if (alias.equals(testUser.getAlias())) {
            return loginFollowers;
        }
        else if (alias.equals(currentUser.getAlias())) {
            return registerFollowers;
        }
        else if (currentUser.getAlias().equals(testUser.getAlias())) {
            List<User> returnList = Collections.emptyList();
            for (int i = 0; i < loginFollowees.size(); i++) {
                if (loginFollowees.get(i).getAlias().equals(alias)) {
                    returnList = Collections.singletonList(testUser);
                }
            }
            return returnList;
        }
        else {
            List<User> returnList = Collections.emptyList();
            for (int i = 0; i < registerFollowees.size(); i++) {
                if (registerFollowees.get(i).getAlias().equals(alias)) {
                    returnList = Collections.singletonList(currentUser);
                }
            }
            return returnList;
        }
    }
}
