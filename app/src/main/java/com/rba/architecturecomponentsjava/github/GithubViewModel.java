package com.rba.architecturecomponentsjava.github;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.rba.architecturecomponentsjava.database.entity.User;
import com.rba.architecturecomponentsjava.repository.GithubRepository;

import javax.inject.Inject;

public class GithubViewModel extends ViewModel {

    private LiveData<User> user;
    private GithubRepository githubRepository;

    @Inject
    public GithubViewModel(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
    }

    public void init(String userId) {
        if (this.user != null) {
            return;
        }
        user = githubRepository.getUser(userId);
    }

    public LiveData<User> getUser() {
        return this.user;
    }
}
