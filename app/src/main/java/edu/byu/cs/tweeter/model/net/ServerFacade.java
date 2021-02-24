package edu.byu.cs.tweeter.model.net;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import edu.byu.cs.tweeter.BuildConfig;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowActionRequest;
import edu.byu.cs.tweeter.model.service.request.FollowDataRequest;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.request.StoryRequest;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.FollowActionResponse;
import edu.byu.cs.tweeter.model.service.response.FollowDataResponse;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.GetUserDataResponse;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;

import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.StoryResponse;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

/**
 * Acts as a Facade to the Tweeter server. All network requests to the server should go through
 * this class.
 */
@RequiresApi(api = Build.VERSION_CODES.O)
public class ServerFacade {
    // This is the hard coded followee data returned by the 'getFollowees()' method
    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    private static final String FEMALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png";

    //private final User testUser = new User("Test", "User", "@dummyUserName", MALE_IMAGE_URL);

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
    private final User testUser = new User("Test", "User", "@dummyUserName", MALE_IMAGE_URL, loginFollowers.size(), loginFollowees.size());


    private final List<User> registerFollowees = Collections.emptyList();
    private final List<User> registerFollowers = Collections.emptyList();

    private final LocalDate date1 = LocalDate.of(2021, 1, 8);
    private final LocalDate date2 = LocalDate.of(2021, 2, 9);
    private final LocalDate date3 = LocalDate.of(2020, 2, 11);
    private final LocalDate date4 = LocalDate.of(2021, 3, 15);
    private final LocalDate date5 = LocalDate.of(2020, 1, 18);
    private final LocalDate date6 = LocalDate.of(2020, 1, 20);
    private final LocalDate date7 = LocalDate.of(2021, 2, 21);
    private final LocalDate date8 = LocalDate.of(2019, 5, 8);
    private final LocalDate date9 = LocalDate.of(2021, 6, 29);
    private final LocalDate date10 = LocalDate.of(2021, 1, 8);
    private final LocalDate date11 = LocalDate.of(2021, 7, 3);

    private final Tweet uTweet1 = new Tweet(testUser, "what a tweet eh", date1);
    private final Tweet uTweet2 = new Tweet(testUser, "Second Tweet", date2);
    private final Tweet uTweet3 = new Tweet(testUser, "What https://nba.com a tweet https://byu.edu", date3);

    private final Tweet fTweet1 = new Tweet(user3, "I hate dummy data", date4);
    private final Tweet fTweet2 = new Tweet(user5, "Who did that?", date5);
    private final Tweet fTweet3 = new Tweet(user17, "Woah bruh", date6);
    private final Tweet fTweet4 = new Tweet(user1, "Go to https://www.byu.edu/", date7);
    private final Tweet fTweet5 = new Tweet(user8, "Visit my page at " + user8.getAlias(), date8);

    private final List<Tweet> loginFeed = new ArrayList<Tweet>(Arrays.asList(fTweet2, fTweet3, fTweet4,
            fTweet5, fTweet1));
    private List<Tweet> loginStory = new ArrayList<Tweet>(Arrays.asList(uTweet2, uTweet3, uTweet1));

    private final List<Tweet> registerFeed = Collections.emptyList();
    private List<Tweet> registerStory = Collections.emptyList();


    /**
     * Performs a login and if successful, returns the logged in user and an auth token. The current
     * implementation is hard-coded to return a dummy user and doesn't actually make a network
     * request.
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
     */
    public LoginResponse login(LoginRequest request) {


        Log.d("info", "login: " + request.getUsername());
        Log.d("info", "login: " + request.getPassword());
        if (request.getUsername().equals("dummyUserName") && request.getPassword().equals("dummyPassword")) {
            Log.d("info", "logged in?");
            return new LoginResponse(testUser, new AuthToken());
        }
        // this is here becuase i got tired of typing that long user name in each time
        else if (request.getUsername().equals("d") && request.getPassword().equals("dddddddd")) {
            Log.d("info", "logged in?");
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
        return new LoginResponse(user, new AuthToken());
    }

    public LogoutResponse logout(LogoutRequest logoutRequest) {
        LogoutResponse logoutResponse = null;
        if(logoutRequest.getUser() != null && logoutRequest.getAuthToken() != null) {
            logoutResponse = new LogoutResponse(true, logoutRequest.getUser() + " succesfully logged out");
        }

        return logoutResponse;
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

        List<Tweet> allTweets = getDummyTweetsStory(request.getUserAlias());
        List<Tweet> responseTweets = new ArrayList<>(request.getLimit());

        Collections.sort(allTweets);

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

        List<Tweet> allTweets = getDummyTweetsFeed(request.getUserAlias());
        List<Tweet> responseTweets = new ArrayList<>(request.getLimit());

        Collections.sort(allTweets);

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
    List<Tweet> getDummyTweetsStory(String alias) {
        if (alias.equals(testUser.getAlias())) {
            return loginStory;
        }
        else if (alias.equals(user1.getAlias())||
                alias.equals(user2.getAlias())||
                alias.equals(user3.getAlias())||
                alias.equals(user4.getAlias())||
                alias.equals(user5.getAlias())||
                alias.equals(user6.getAlias())||
                alias.equals(user7.getAlias())||
                alias.equals(user8.getAlias())||
                alias.equals(user9.getAlias())||
                alias.equals(user10.getAlias())||
                alias.equals(user11.getAlias())||
                alias.equals(user12.getAlias())||
                alias.equals(user13.getAlias())||
                alias.equals(user14.getAlias())||
                alias.equals(user15.getAlias())||
                alias.equals(user16.getAlias())||
                alias.equals(user17.getAlias())||
                alias.equals(user18.getAlias())||
                alias.equals(user19.getAlias())||
                alias.equals(user20.getAlias())) {
            List<Tweet> returnList = Collections.emptyList();
            for (int i = 0; i < loginFeed.size(); i++) {
                if (loginFeed.get(i).getAuthor().equals(alias)) {
                    returnList.add(loginFeed.get(i));
                }
            }
            return returnList;
        }
        else {
            return registerStory;
        }
    }
    List<Tweet> getDummyTweetsFeed(String alias) {
        if (alias.equals(testUser.getAlias())) {
            return loginFeed;
        }
        else if (alias.equals(user1.getAlias())||
                alias.equals(user2.getAlias())||
                alias.equals(user3.getAlias())||
                alias.equals(user4.getAlias())||
                alias.equals(user5.getAlias())||
                alias.equals(user6.getAlias())||
                alias.equals(user7.getAlias())||
                alias.equals(user8.getAlias())||
                alias.equals(user9.getAlias())||
                alias.equals(user10.getAlias())||
                alias.equals(user11.getAlias())||
                alias.equals(user12.getAlias())||
                alias.equals(user13.getAlias())||
                alias.equals(user14.getAlias())||
                alias.equals(user15.getAlias())||
                alias.equals(user16.getAlias())||
                alias.equals(user17.getAlias())||
                alias.equals(user18.getAlias())||
                alias.equals(user19.getAlias())||
                alias.equals(user20.getAlias())) {
            return loginStory;
        }
        else {
            return registerFeed;
        }
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
        else if (alias.equals(user1.getAlias())||
                alias.equals(user2.getAlias())||
                alias.equals(user3.getAlias())||
                alias.equals(user4.getAlias())||
                alias.equals(user5.getAlias())||
                alias.equals(user6.getAlias())||
                alias.equals(user7.getAlias())||
                alias.equals(user8.getAlias())||
                alias.equals(user9.getAlias())||
                alias.equals(user10.getAlias())||
                alias.equals(user11.getAlias())||
                alias.equals(user12.getAlias())||
                alias.equals(user13.getAlias())||
                alias.equals(user14.getAlias())||
                alias.equals(user15.getAlias())||
                alias.equals(user16.getAlias())||
                alias.equals(user17.getAlias())||
                alias.equals(user18.getAlias())||
                alias.equals(user19.getAlias())||
                alias.equals(user20.getAlias())) {
            List<User> returnList = Collections.emptyList();
            for (int i = 0; i < loginFollowers.size(); i++) {
                if (loginFollowers.get(i).getAlias().equals(alias)) {
                    returnList = Collections.singletonList(testUser);
                }
            }
            return returnList;
        }
        else {
            return registerFollowees;
        }
    }

    List<User> getDummyFollowers(String alias) {
        if (alias.equals(testUser.getAlias())) {
            return loginFollowers;
        }
        else if (alias.equals(user1.getAlias())||
                alias.equals(user2.getAlias())||
                alias.equals(user3.getAlias())||
                alias.equals(user4.getAlias())||
                alias.equals(user5.getAlias())||
                alias.equals(user6.getAlias())||
                alias.equals(user7.getAlias())||
                alias.equals(user8.getAlias())||
                alias.equals(user9.getAlias())||
                alias.equals(user10.getAlias())||
                alias.equals(user11.getAlias())||
                alias.equals(user12.getAlias())||
                alias.equals(user13.getAlias())||
                alias.equals(user14.getAlias())||
                alias.equals(user15.getAlias())||
                alias.equals(user16.getAlias())||
                alias.equals(user17.getAlias())||
                alias.equals(user18.getAlias())||
                alias.equals(user19.getAlias())||
                alias.equals(user20.getAlias())) {
            List<User> returnList = Collections.emptyList();
            for (int i = 0; i < loginFollowees.size(); i++) {
                if (loginFollowees.get(i).getAlias().equals(alias)) {
                    returnList = Collections.singletonList(testUser);
                }
            }
            return returnList;
        }
        else {
            return registerFollowers;
        }
    }
    public TweetResponse postTweet(TweetRequest request){
        // need to fix this so that it acctually uses the current user



        boolean responseBool = true;
        TweetResponse response = new TweetResponse(responseBool);
        return response;

    }
    public FollowDataResponse getFollowerData(FollowDataRequest request){
        FollowDataResponse response = new FollowDataResponse(testUser.getFollowers(), testUser.getFollowing());
        //FollowDataResponse response = new FollowDataResponse(120, 45);
        return response;
    }

    public FollowActionResponse followUser(FollowActionRequest request){
        FollowActionResponse response = new FollowActionResponse(true, "User " + request.getUser().getAlias() + " is now following " + request.getUserToFollow().getAlias());
        return response;
    }
    public FollowActionResponse unFollowUser(FollowActionRequest request){
        FollowActionResponse response = new FollowActionResponse(true, "User @" + request.getUser().getAlias() + " is no longer following @" + request.getUserToFollow().getAlias());
        return response;
    }
    public FollowActionResponse isFollowing(FollowActionRequest request){
        FollowActionResponse response;
        if(loginFollowees.contains(request.getUserToFollow())) {
             response = new FollowActionResponse(true);
        }
        else{
             response = new FollowActionResponse(false);
        }
        return response;
    }

    public GetUserDataResponse getUserFromAlias(String alias) {
        for(User user : loginFollowees) {
            if(user.getAlias().equals(alias)) {
                return new GetUserDataResponse(user);
            }
        }
        return null;
    }

}
