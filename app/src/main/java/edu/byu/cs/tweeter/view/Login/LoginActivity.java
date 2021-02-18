package edu.byu.cs.tweeter.view.Login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.presenter.LoginPresenter;

/**
 * Contains the minimum UI required to allow the user to login with a hard-coded user. Most or all
 * of this should be replaced when the back-end is implemented.
 */
public class LoginActivity extends AppCompatActivity implements LoginPresenter.View {

    private static final String LOG_TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginPagerAdapter loginPagerAdapter = new LoginPagerAdapter(this, getSupportFragmentManager());
        ViewPager loginViewPager = findViewById(R.id.login_view_pager);
        loginViewPager.setAdapter(loginPagerAdapter);
        TabLayout tabs = findViewById(R.id.login_tabs);
        tabs.setupWithViewPager(loginViewPager);
    }
}
