package com.rba.architecturecomponentsjava.user;

import android.arch.lifecycle.LiveData;
import android.util.Log;
import android.widget.Toast;

import com.rba.architecturecomponentsjava.api.UserWebService;
import com.rba.architecturecomponentsjava.app.GithubApplication;
import com.rba.architecturecomponentsjava.database.dao.UserDao;
import com.rba.architecturecomponentsjava.database.entity.User;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executor;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class UserRepository {

    private static final int FRESH_TIMEOUT_IN_MINUTES = 1;

    private final UserWebService userWebService;
    private final UserDao userDao;
    private final Executor executor;

    @Inject
    public UserRepository(UserWebService userWebService, UserDao userDao, Executor executor) {
        this.userWebService = userWebService;
        this.userDao = userDao;
        this.executor = executor;
    }

    public LiveData<User> getUser(String userLogin) {
        refreshUser(userLogin);
        return userDao.load(userLogin);
    }

    private void refreshUser(final String userLogin) {
        executor.execute(() -> {
            boolean userExists = (userDao.hasUser(userLogin, getMaxRefreshTime(new Date())) != null);
            if (!userExists) {
                userWebService.getUser(userLogin).enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.e("TAG", "DATA REFRESHED FROM NETWORK");
                        Toast.makeText(GithubApplication.context, "Data refreshed from network !",
                                Toast.LENGTH_LONG).show();
                        executor.execute(() -> {
                            User user = response.body();
                            user.setLastRefresh(new Date());
                            userDao.insert(user);
                        });
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                    }
                });
            }
        });
    }

    // ---

    private Date getMaxRefreshTime(Date currentDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);
        cal.add(Calendar.MINUTE, -FRESH_TIMEOUT_IN_MINUTES);
        return cal.getTime();
    }
}
