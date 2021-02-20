package edu.byu.cs.tweeter.presenter;

import edu.byu.cs.tweeter.view.Login.RegisterSubject;

public interface RegisterObserver {
    void Update(RegisterSubject subject, String first, String last, String username, String password);
}
