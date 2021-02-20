package edu.byu.cs.tweeter.view.Login;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import edu.byu.cs.tweeter.R;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.presenter.LoginPresenter;
import edu.byu.cs.tweeter.view.asyncTasks.LoginTask;
import edu.byu.cs.tweeter.view.main.MainActivity;

public class LoginFragment extends LoginSubject implements LoginPresenter.View, LoginTask.Observer {
    private static final String LOG_TAG = "LoginFragment";

    private LoginPresenter presenter;
    private Toast loginInToast;
    private Button loginButton;

    /**
     * Creates an instance of the fragment and places the user and auth token in an arguments
     * bundle assigned to the fragment.
     *
     * @return the fragment.
     */
    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        presenter = new LoginPresenter(this);

        Attach(presenter);

        loginButton = view.findViewById(R.id.LoginButton);
        loginButton.setEnabled(false);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginInToast = Toast.makeText(getActivity(), "logging in", Toast.LENGTH_LONG);
                loginInToast.show();

                LoginRequest loginRequest = new LoginRequest("dummyUserName", "dummyPassword");
                LoginTask loginTask = new LoginTask(presenter, LoginFragment.this);
                loginTask.execute(loginRequest);
            }
        });

        EditText usernameEditText = view.findViewById(R.id.username);
        EditText passwordEditText = view.findViewById(R.id.password);


        usernameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                ;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ;
            }

            @Override
            public void afterTextChanged(Editable s) {
                Notify(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        passwordEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                ;
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ;
            }

            @Override
            public void afterTextChanged(Editable s) {
                Notify(usernameEditText.getText().toString(), passwordEditText.getText().toString());
            }
        });

        return view;
    }

    /**
     * The method invoked from the observer to allow login button to be pressed
     *
     */
    public void setButton(Boolean bool) {
        loginButton.setEnabled(bool);
    }

    /**
     * The callback method that gets invoked for a successful login. Displays the MainActivity.
     *
     * @param loginResponse the response from the login request.
     */
    @Override
    public void loginSuccessful(LoginResponse loginResponse) {
        Intent intent = new Intent(getContext(), MainActivity.class);

        intent.putExtra(MainActivity.CURRENT_USER_KEY, loginResponse.getUser());
        intent.putExtra(MainActivity.AUTH_TOKEN_KEY, loginResponse.getAuthToken());

        loginInToast.cancel();
        startActivity(intent);
    }

    /**
     * The callback method that gets invoked for an unsuccessful login. Displays a toast with a
     * message indicating why the login failed.
     *
     * @param loginResponse the response from the login request.
     */
    @Override
    public void loginUnsuccessful(LoginResponse loginResponse) {
        Toast.makeText(getActivity(), "Failed to login. " + loginResponse.getMessage(), Toast.LENGTH_LONG).show();
    }

    /**
     * A callback indicating that an exception was thrown in an asynchronous method called on the
     * presenter.
     *
     * @param exception the exception.
     */
    @Override
    public void handleException(Exception exception) {
        Log.e(LOG_TAG, exception.getMessage(), exception);
        Toast.makeText(getActivity(), "Failed to login because of exception: " + exception.getMessage(), Toast.LENGTH_LONG).show();
    }
}