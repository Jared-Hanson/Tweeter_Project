package edu.byu.cs.tweeter.view.main;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.request.FollowActionRequest;
import edu.byu.cs.tweeter.model.service.request.FollowDataRequest;
import edu.byu.cs.tweeter.model.service.response.FollowDataResponse;
import edu.byu.cs.tweeter.presenter.Follow_UnfollowActionPresenter;
import edu.byu.cs.tweeter.presenter.FollowerDataPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.CheckIfFollowingTask;
import edu.byu.cs.tweeter.view.asyncTasks.FollowTask;
import edu.byu.cs.tweeter.view.asyncTasks.GetFollowerDataTask;
import edu.byu.cs.tweeter.view.asyncTasks.UnFollowTask;
import edu.byu.cs.tweeter.view.util.ImageUtils;

import static edu.byu.cs.tweeter.R.layout.activity_user;

/**
 * The main activity for the application. Contains tabs for feed, story, following, and followers.
 */
public class UserActivity extends AppCompatActivity {

    public static final String CURRENT_USER_KEY = "CurrentUser";
    public static final String AUTH_TOKEN_KEY = "AuthTokenKey";
    public static final String LOGGED_IN_USER = "LoggedInUser";

    private static final String LOG_TAG = "UserAcivity";


    private User user;
    private User loggedInUser;
    private AuthToken authToken;

    private Button follow_unfollow_Button;
    private FollowerDataPresenter dataPresenter;
    private Follow_UnfollowActionPresenter followPresenter;
    private Boolean isFollowing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_user);

        user = (User) getIntent().getSerializableExtra(CURRENT_USER_KEY);
        if(user == null) {
            throw new RuntimeException("User not passed to activity");
        }

        authToken = (AuthToken) getIntent().getSerializableExtra(AUTH_TOKEN_KEY);
        loggedInUser = (User) getIntent().getSerializableExtra(LOGGED_IN_USER);
        dataPresenter = new FollowerDataPresenter();
        followPresenter = new Follow_UnfollowActionPresenter();

        UserSectionsPagerAdapter sectionsPagerAdapter = new UserSectionsPagerAdapter(this, getSupportFragmentManager(), user, authToken);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


        TextView userName = findViewById(R.id.userName);
        userName.setText(user.getName());

        TextView userAlias = findViewById(R.id.userAlias);
        userAlias.setText(user.getAlias());

        ImageView userImageView = findViewById(R.id.userImage);
        userImageView.setImageDrawable(ImageUtils.drawableFromByteArray(user.getImageBytes()));

        TextView followeeCount = findViewById(R.id.followeeCount);
        followeeCount.setText(getString(R.string.followeeCount, 42));

        TextView followerCount = findViewById(R.id.followerCount);
        followerCount.setText(getString(R.string.followerCount, 27));
        new getFollowerData().GetDataFunction(new FollowDataRequest(user));

        follow_unfollow_Button = findViewById(R.id.FollowButton);
        follow_unfollow_Button.setEnabled(true);
        new checkFollow().executeTask(new FollowActionRequest(loggedInUser, user));


        follow_unfollow_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isFollowing){
                    // call async task/observer to do the follow/unfollow action
                    new unFollowUser().executeTask(new FollowActionRequest(loggedInUser, user));
                }
                else{

                    new followUser().executeTask(new FollowActionRequest(loggedInUser, user));
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu, menu);


        return true;
    }


    private class checkFollow extends FollowingObserverSuper {

        @Override
        void goodResponse() {
            follow_unfollow_Button.setBackgroundColor(Color.GRAY);
            follow_unfollow_Button.setText("Unfollow");
            isFollowing = true;
        }

        @Override
        void badResponse() {
            follow_unfollow_Button.setBackgroundColor(Color.RED);
            follow_unfollow_Button.setText("Follow");
            isFollowing = false;
        }

        @Override
        void setupPresenter(FollowActionRequest request) {
            CheckIfFollowingTask data = new CheckIfFollowingTask(followPresenter, this);
            data.execute(request);
        }
        @Override
        protected Context getApplicationContextFunction() {
            return getApplicationContext();
        }

    }

    private class followUser extends FollowingObserverSuper {

        @Override
        void goodResponse() {
            isFollowing = true;
            // update follower data
            new getFollowerData().GetDataFunction(new FollowDataRequest(user));
            Toast.makeText(getApplicationContext(), "User has been followed: We have received and updated follower/followee data", Toast.LENGTH_LONG).show();
        }

        @Override
        void badResponse() {
            Toast.makeText(getApplicationContext(), "Error in request to Follow user", Toast.LENGTH_LONG).show();
        }

        @Override
        void setupPresenter(FollowActionRequest request) {
            FollowTask data = new FollowTask(followPresenter, this);
            data.execute(request);
        }

        @Override
        protected Context getApplicationContextFunction() {
            return getApplicationContext();
        }
    }

    private class unFollowUser extends FollowingObserverSuper {

        @Override
        void goodResponse() {
            isFollowing = true;
            // update follower data
            new getFollowerData().GetDataFunction(new FollowDataRequest(user));
            Toast.makeText(getApplicationContext(), "User has been UN-followed: We have received and updated follower/followee data", Toast.LENGTH_LONG).show();

        }

        @Override
        void badResponse() {
            Toast.makeText(getApplicationContext(), "Error in request to UnFollow user", Toast.LENGTH_LONG).show();
        }

        @Override
        void setupPresenter(FollowActionRequest request) {
            UnFollowTask data = new UnFollowTask(followPresenter, this);
            data.execute(request);
        }
        @Override
        protected Context getApplicationContextFunction() {
            return getApplicationContext();
        }
    }



    private class getFollowerData implements GetFollowerDataTask.Observer {

        void GetDataFunction(FollowDataRequest request) {
            //FollowerDataPresenter presenter = new FollowerDataPresenter(dataPresenter);
            GetFollowerDataTask data = new GetFollowerDataTask(dataPresenter, this);
            data.execute(request);
        }


        @Override
        public void getData (FollowDataResponse res) {
            TextView followeeCount = findViewById(R.id.followeeCount);
            followeeCount.setText(getString(R.string.followeeCount, res.getFollowing()));

            TextView followerCount = findViewById(R.id.followerCount);
            followerCount.setText(getString(R.string.followerCount, res.getFollowers()));

        }

        @Override
        public void handleException(Exception exception) {
            Log.e(LOG_TAG, exception.getMessage(), exception);
            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();

        }
    }

}