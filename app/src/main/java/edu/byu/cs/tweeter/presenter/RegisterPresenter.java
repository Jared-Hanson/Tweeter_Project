package edu.byu.cs.tweeter.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.model.service.RegisterService;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.presenter.Observers.RegisterObserver;
import edu.byu.cs.tweeter.view.Login.RegisterFragment;
import edu.byu.cs.tweeter.view.Login.RegisterSubject;

/**
 * The presenter for the register functionality of the application.
 */
public class RegisterPresenter implements RegisterObserver {

    private final View view;

    @Override
    public void Update(RegisterSubject subject, String first, String last, String username, String password) {
        if(subject instanceof RegisterFragment) {
            RegisterFragment registerFragment = (RegisterFragment) subject;
            if(first.length() > 0 && last.length() > 0 && username.length() > 0 && password.length() >= 6) {
                registerFragment.setButton(true);
            } else {
                registerFragment.setButton(false);
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
    public RegisterPresenter(View view) {
        this.view = view;
    }

    /**
     * Makes a register request.
     *
     * @param registerRequest the request.
     */
    public LoginResponse register(RegisterRequest registerRequest) throws IOException {
        RegisterService registerService = new RegisterService();
        return registerService.register(registerRequest);
    }
}
