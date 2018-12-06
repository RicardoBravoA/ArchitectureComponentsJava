package com.rba.architecturecomponentsjava.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;

import com.rba.architecturecomponentsjava.database.converter.DateConverter;
import com.rba.architecturecomponentsjava.database.dao.UserDao;
import com.rba.architecturecomponentsjava.database.entity.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
@TypeConverters(DateConverter.class)
public abstract class GithubDatabase extends RoomDatabase {

    private static volatile GithubDatabase INSTANCE;

    public abstract UserDao userDao();
}
