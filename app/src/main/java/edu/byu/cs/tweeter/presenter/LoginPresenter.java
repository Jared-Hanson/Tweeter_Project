package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.LoginService;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.presenter.Observers.LoginObserver;
import edu.byu.cs.tweeter.view.Login.LoginFragment;
import edu.byu.cs.tweeter.view.Login.LoginSubject;

/**
 * The presenter for the login functionality of the application.
 */
public class LoginPresenter implements LoginObserver {

    private final View view;

    @Override
    public void Update(LoginSubject subject, String username, String password) {
        if(subject instanceof LoginFragment) {
            LoginFragment loginFragment = (LoginFragment) subject;
            if(username.length() > 0 && password.length() >= 6) {
                loginFragment.setButton(true);
            }
            else {
                loginFragment.setButton(false);
            }
        }
    }

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public LoginPresenter(View view) {
        this.view = view;
    }

    /**
     * Makes a login request.
     *
     * @param loginRequest the request.
     */
    public LoginResponse login(LoginRequest loginRequest) throws IOException {
        LoginService loginService = new LoginService();
        return loginService.login(loginRequest);
    }
}
