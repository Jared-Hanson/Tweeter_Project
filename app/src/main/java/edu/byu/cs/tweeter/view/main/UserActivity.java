package edu.byu.cs.tweeter.view.main;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.menu.MenuView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.Date;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.service.TweetService;
import edu.byu.cs.tweeter.model.service.request.FollowDataRequest;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.FollowDataResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;
import edu.byu.cs.tweeter.presenter.FollowerDataPresenter;
import edu.byu.cs.tweeter.presenter.LogoutPresenter;
import edu.byu.cs.tweeter.presenter.TweetPresenter;
import edu.byu.cs.tweeter.view.Login.LoginActivity;
import edu.byu.cs.tweeter.view.Login.LoginFragment;
import edu.byu.cs.tweeter.view.asyncTasks.GetFollowerDataTask;
import edu.byu.cs.tweeter.view.asyncTasks.LoginTask;
import edu.byu.cs.tweeter.view.asyncTasks.LogoutTask;
import edu.byu.cs.tweeter.view.util.ImageUtils;

import static edu.byu.cs.tweeter.R.layout.activity_main;
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


        follow_unfollow_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("Button Pressed");
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user_menu, menu);


        return true;
    }

    private class getFollowerData implements GetFollowerDataTask.Observer {

        void GetDataFunction(FollowDataRequest request) {
            //FollowerDataPresenter presenter = new FollowerDataPresenter(dataPresenter);
            GetFollowerDataTask data = new GetFollowerDataTask(dataPresenter, this);
            data.execute(request);
        }


        @Override
        public void getData(FollowDataResponse res) {
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