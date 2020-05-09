package ru.falchio.pixabayclient.model;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.falchio.pixabayclient.json.PixaAnswer;
import ru.falchio.pixabayclient.json.PixaImageUrl;
import ru.falchio.pixabayclient.json.PixaRequest;

public class Model {
    private List<PixaImageUrl> pixaImages;

    private final String TAG = this.getClass().getSimpleName();
    private PixaRequest request;
    private PixaAnswer pixaAnswer;
    private final String API = "16246042-74f45b97e0abdd7225fc1506d";
    private final String SEARCH_KEY_WORDS = "yellow+flowers";
    private final String IMAGE_TYPE = "photo";


    public Model() {
        initRetrofit();
    }

    public List<PixaImageUrl> getPixaImages() {
        return pixaImages;
    }

    private void initRetrofit() {
        Retrofit retrofit;
        retrofit = new Retrofit.Builder()
                .baseUrl("https://pixabay.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        request = retrofit.create(PixaRequest.class);

        request.loadImage(API, SEARCH_KEY_WORDS, IMAGE_TYPE).enqueue(new Callback<PixaAnswer>() {
            @Override
            public void onResponse(Call<PixaAnswer> call, Response<PixaAnswer> response) {
                 pixaAnswer = response.body();
                 pixaImages =Arrays.asList(pixaAnswer.getPixaImageUrls());

//                Log.d(TAG, "onResponse: " + pixaAnswer.toString());
            }

            @Override
            public void onFailure(Call<PixaAnswer> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.toString());
            }
        });

    }
}
