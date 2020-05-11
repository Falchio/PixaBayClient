package ru.falchio.pixabayclient;

import android.app.Application;


import androidx.room.Room;

import ru.falchio.pixabayclient.database.PixaUrlsDao;
import ru.falchio.pixabayclient.database.PixaUrlsDatabase;
import ru.falchio.pixabayclient.model.Model;

public class App extends Application {
    private final String NAME_DATABASE = "PixaUrls";
    private static App instance;
    private PixaUrlsDatabase database;
    private Model model = new Model();

    public static App getInstance() {
        return instance;
    }

    public PixaUrlsDao getPixaUrlsDao() {
        return database.getPixaUrlsDao();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(
                getApplicationContext(),
                PixaUrlsDatabase.class,
                NAME_DATABASE
        ).build();
    }
}
