package edu.byu.cs.tweeter.view;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.view.main.PlaceholderFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to one of the sections/tabs/pages
 * of the Main Activity.
 */
class LoginPagerAdapter extends FragmentPagerAdapter {

    private static final int LOGIN_FRAGMENT_POSITION = 0;

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.login, R.string.register};
    private final Context mContext;

    public LoginPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == LOGIN_FRAGMENT_POSITION) {
            return LoginFragment.newInstance();
        }
        else {
            return RegisterFragment.newInstance();
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mContext.getResources().getString(TAB_TITLES[position]);
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}
