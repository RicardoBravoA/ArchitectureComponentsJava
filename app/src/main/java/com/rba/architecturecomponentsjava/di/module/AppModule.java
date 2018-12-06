package com.rba.architecturecomponentsjava.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rba.architecturecomponentsjava.database.GithubDatabase;
import com.rba.architecturecomponentsjava.database.dao.UserDao;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
public class AppModule {

    @Provides
    @Singleton
    GithubDatabase provideDatabase(Application application) {
        return Room.databaseBuilder(application,
                GithubDatabase.class, "GithubDatabase.db")
                .build();
    }

    @Provides
    @Singleton
    UserDao provideUserDao(GithubDatabase database) {
        return database.userDao();
    }

    @Provides
    Executor provideExecutor() {
        return Executors.newSingleThreadExecutor();
    }

    /*
    @Provides
    @Singleton
    UserRepository provideUserRepository(UserWebService webservice, UserDao userDao, Executor executor) {
        return new UserRepository(webservice, userDao, executor);
    }
    */

    private final static String BASE_URL = "https://api.github.com/";

    @Provides
    Gson provideGson() {
        return new GsonBuilder().create();
    }

    @Provides
    Retrofit provideRetrofit(Gson gson) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BASE_URL)
                .build();
    }

    /*
    @Provides
    @Singleton
    UserWebService provideApiWebservice(Retrofit restAdapter) {
        return restAdapter.create(UserWebService.class);
    }
    */

}
