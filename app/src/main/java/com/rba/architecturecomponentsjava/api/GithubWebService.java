package com.rba.architecturecomponentsjava.api;

import com.rba.architecturecomponentsjava.database.entity.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubWebService {
    @GET("/users/{user}")
    Call<User> getUser(@Path("user") String userId);
}
