package edu.byu.cs.tweeter.view.Login;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

import edu.byu.cs.tweeter.presenter.LoginObserver;

public abstract class LoginSubject extends Fragment {
    public List<LoginObserver> observerList = new ArrayList<>();

    void Attach(LoginObserver observer) {
        observerList.add(observer);
    }

    void Detach(LoginObserver observer) {
        observerList.remove(observer);
    }

    void Notify() {
        for(LoginObserver observer : observerList) {
            observer.Update(this);
        }
    }
}
