package edu.byu.cs.tweeter.presenter.Observers;

import edu.byu.cs.tweeter.view.main.Login.RegisterSubject;

public interface RegisterObserver {
    void Update(RegisterSubject subject, String first, String last, String username, String password);
}
