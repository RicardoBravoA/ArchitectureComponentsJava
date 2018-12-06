package com.rba.architecturecomponentsjava.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.rba.architecturecomponentsjava.database.entity.User;

import javax.inject.Inject;

public class UserViewModel extends ViewModel {

    private LiveData<User> user;
    private UserRepository userRepository;

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void init(String userId) {
        if (this.user != null) {
            return;
        }
        user = userRepository.getUser(userId);
    }

    public LiveData<User> getUser() {
        return this.user;
    }
}
