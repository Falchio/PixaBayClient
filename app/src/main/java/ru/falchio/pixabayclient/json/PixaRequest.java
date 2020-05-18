package ru.falchio.pixabayclient.json;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixaRequest {

    @GET("api")
    Single<PixaAnswer> loadImage(@Query("key") String apiKey, @Query("q") String searchKeyWord, @Query("image_type") String imageType, @Query("per_page") String amountResults);
}
