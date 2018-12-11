package com.rba.architecturecomponentsjava.user;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.rba.architecturecomponentsjava.database.entity.User;

import javax.inject.Inject;

public class UserProfileViewModel extends ViewModel {

    private LiveData<User> user;
    private UserRepository userRepo;

    @Inject
    public UserProfileViewModel(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public void init(String userId) {
        if (this.user != null) {
            return;
        }
        user = userRepo.getUser(userId);
    }

    public LiveData<User> getUser() {
        return this.user;
    }
}