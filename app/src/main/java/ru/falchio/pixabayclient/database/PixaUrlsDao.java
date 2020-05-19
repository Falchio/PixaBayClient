package ru.falchio.pixabayclient.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


import io.reactivex.Single;
import ru.falchio.pixabayclient.json.PixaImageUrl;

@Dao
public interface PixaUrlsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPixaImageUrl(PixaImageUrl pixaImageUrl);

    @Update
    void updatePixaImageUrl(PixaImageUrl pixaImageUrl);

    @Delete
    void deletePixaImageUrl(PixaImageUrl pixaImageUrl);

    @Query("DELETE FROM PixaUrls WHERE wordsForSearch=:wordsForSearch")
    void deletePixaImageUrlsById(long wordsForSearch);

    @Query("SELECT * FROM PixaUrls WHERE wordsForSearch=:wordsForSearch")
    Single<List<PixaImageUrl>> getListPixaImageUrs(String wordsForSearch);

    @Query("SELECT * FROM PixaUrls WHERE wordsForSearch=:wordsForSearch AND imageType =:imageType")
    Single<List<PixaImageUrl>> getListPixaImageUrs(String wordsForSearch, String imageType);

    @Query("SELECT COUNT() FROM PixaUrls")
    long getCountPixaImageUrls();

    @Query("DELETE FROM PixaUrls")
    void deletePixaImageUrls();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void  insertPixaImageUrlsList(List<PixaImageUrl> pixaImageUrlList);


}
