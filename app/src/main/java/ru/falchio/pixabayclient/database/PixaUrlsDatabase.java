package ru.falchio.pixabayclient.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import ru.falchio.pixabayclient.json.PixaImageUrl;

@Database(entities = {PixaImageUrl.class}, version = 1, exportSchema = false)
public abstract class PixaUrlsDatabase extends RoomDatabase {
    public abstract PixaUrlsDao getPixaUrlsDao();
}
