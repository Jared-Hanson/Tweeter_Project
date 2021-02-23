package edu.byu.cs.tweeter.presenter.Observers;

import edu.byu.cs.tweeter.view.Login.LoginSubject;

public interface LoginObserver {
    void Update(LoginSubject subject, String username, String password);
}